package com.vietqr.org.grpc.scheduled;

import com.vietqr.org.entity.AccountBankEntity;
import com.vietqr.org.entity.AccountBankMonthEntity;
import com.vietqr.org.grpc.reactive.ReactiveBankAccountService;
import com.vietqr.org.repository.AccountBankMonthRepository;

import com.vietqr.org.repository.AccountBankRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Optional;
import java.util.UUID;

@Service
public class BankAccountScheduled {
    private static final Logger logger = LoggerFactory.getLogger(BankAccountScheduled.class);

    @Autowired
    private ReactiveBankAccountService reactiveBankAccountService;
    @Autowired
    private AccountBankMonthRepository accountBankMonthRepository;

    @Autowired
    private AccountBankRepository accountBankRepository;

    //@Scheduled(cron = "0 * * * * ?")
    @Scheduled(cron = "0 0 0 * * ?") // Chạy vào 00:00 mỗi ngày
    public void syncMonthlyBankAccountStatistics() throws InterruptedException {
        YearMonth currentMonth = YearMonth.now();
        String currentMonthString = currentMonth.toString();
        LocalDate yesterday = LocalDate.now().minusDays(1);
        String lastMonthString = yesterday.toString().substring(0, 7); // Lấy năm-tháng của ngày hôm trước

        reactiveBankAccountService.getBankAccountStatistics(lastMonthString)
                .subscribe(response -> {
                    Optional<AccountBankMonthEntity> optionalEntity = accountBankMonthRepository.findByBankShortNameAndTime(response.getBankShortName(), currentMonthString);
                    if (optionalEntity.isPresent()) {
                        AccountBankMonthEntity entity = optionalEntity.get();
                        entity.setTotalAccounts(response.getTotalAccounts());
                        entity.setLinkedAccounts(response.getLinkedAccounts());
                        entity.setUnlinkedAccounts(response.getUnlinkedAccounts());
                        accountBankMonthRepository.save(entity);
                        logger.info("Successfully updated bank account statistics for: " + response.getBankShortName());
                    } else {
                        AccountBankMonthEntity entity = new AccountBankMonthEntity();
                        entity.setId(UUID.randomUUID().toString());
                        entity.setBankShortName(response.getBankShortName());
                        entity.setTotalAccounts(response.getTotalAccounts());
                        entity.setLinkedAccounts(response.getLinkedAccounts());
                        entity.setUnlinkedAccounts(response.getUnlinkedAccounts());
                        entity.setTime(currentMonthString);
                        accountBankMonthRepository.save(entity);
                        logger.info("Successfully saved new bank account statistics for: " + response.getBankShortName());
                    }
                }, throwable -> logger.error("Failed to fetch bank account statistics: " + throwable.getMessage()));
    }

    //@Scheduled(cron = "0 * * * * ?")
    @Scheduled(cron = "0 0 0 * * ?")
    public void syncDailyBankAccountStatistics() throws InterruptedException {
        reactiveBankAccountService.getAllBankAccountStatistics()
                .subscribe(response -> {
                    Optional<AccountBankEntity> optionalEntity = accountBankRepository.findByBankShortName(response.getBankShortName());
                    if (optionalEntity.isPresent()) {
                        AccountBankEntity entity = optionalEntity.get();
                        entity.setTotalAccounts(response.getTotalAccounts());
                        entity.setLinkedAccounts(response.getLinkedAccounts());
                        entity.setUnlinkedAccounts(response.getUnlinkedAccounts());
                        accountBankRepository.save(entity);
                        logger.info("Successfully updated bank account statistics for: " + response.getBankShortName());
                    } else {
                        AccountBankEntity entity = new AccountBankEntity();
                        entity.setId(UUID.randomUUID().toString());
                        entity.setBankShortName(response.getBankShortName());
                        entity.setTotalAccounts(response.getTotalAccounts());
                        entity.setLinkedAccounts(response.getLinkedAccounts());
                        entity.setUnlinkedAccounts(response.getUnlinkedAccounts());
                        accountBankRepository.save(entity);
                        logger.info("Successfully saved new bank account statistics for: " + response.getBankShortName());
                    }
                }, throwable -> logger.error("Failed to fetch bank account statistics: " + throwable.getMessage()));
    }
}
