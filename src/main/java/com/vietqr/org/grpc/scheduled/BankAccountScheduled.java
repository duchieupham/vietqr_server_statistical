package com.vietqr.org.grpc.scheduled;

import com.vietqr.org.entity.AccountBankMonthEntity;
import com.vietqr.org.grpc.reactive.ReactiveBankAccountService;
import com.vietqr.org.repository.AccountBankMonthRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.UUID;

@Service
public class BankAccountScheduled {
    private static final Logger logger = LoggerFactory.getLogger(BankAccountScheduled.class);


    private final ReactiveBankAccountService reactiveBankAccountService;
    private final AccountBankMonthRepository accountBankMonthRepository;

    @Autowired
    public BankAccountScheduled(ReactiveBankAccountService reactiveBankAccountService, AccountBankMonthRepository accountBankMonthRepository) {
        this.reactiveBankAccountService = reactiveBankAccountService;
        this.accountBankMonthRepository = accountBankMonthRepository;
    }

    //@Scheduled(cron = "0 * * * * ?")
    @Scheduled(cron = "59 59 23 L * ?") // Chạy vào 23:59 ngày cuối cùng của mỗi tháng
    public void syncMonthlyBankAccountStatistics() throws InterruptedException {
        YearMonth currentMonth  = YearMonth.now();
        String lastMonthString = currentMonth.toString();

        reactiveBankAccountService.getBankAccountStatistics(lastMonthString)
                .subscribe(response -> {
                    AccountBankMonthEntity entity = new AccountBankMonthEntity();
                    entity.setId(UUID.randomUUID().toString());
                    entity.setBankShortName(response.getBankShortName());
                    entity.setTotalAccounts(response.getTotalAccounts());
                    entity.setLinkedAccounts(response.getLinkedAccounts());
                    entity.setUnlinkedAccounts(response.getUnlinkedAccounts());
                    entity.setTime(lastMonthString);
                    accountBankMonthRepository.save(entity);
                    logger.info("Successfully saved bank account statistics for: " + response.getBankShortName());
                }, throwable -> logger.error("Failed to fetch bank account statistics: " + throwable.getMessage()));
    }
}
