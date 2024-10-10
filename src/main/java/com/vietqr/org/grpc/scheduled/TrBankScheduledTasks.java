package com.vietqr.org.grpc.scheduled;

import com.example.grpc.TBank;
import com.vietqr.org.entity.TrBankEntity;
import com.vietqr.org.grpc.reactive.TrBankServiceClient;
import com.vietqr.org.repository.TrBankRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class TrBankScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(TrSysScheduledTasks.class);

    @Autowired
    private TrBankServiceClient trBankServiceClient;

    @Autowired
    private TrBankRepository trBankRepository;


   // @Scheduled(cron = "0 * * * * ?")
    @Scheduled(cron = "0 0 21 * * ?")
    @Transactional
    public void syncData() {
        try {
            long startDate = getStartDate();
            long endDate = getEndDate();
            logger.info("Syncing data from {} to {}", startDate, endDate);
            List<TBank> trBankList = trBankServiceClient.getTrBank(startDate, endDate);

            if (trBankList != null && !trBankList.isEmpty()) {
                for (TBank tBank : trBankList) {
                    TrBankEntity entity = new TrBankEntity(
                            UUID.randomUUID().toString(),
                            LocalDateTime.now(ZoneOffset.UTC).getYear(),
                            LocalDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM")),
                            LocalDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE),
                            tBank.getBankShortName(),
                            tBank.getTotalNumberCredits(),
                            tBank.getTotalAmountCredits(),
                            tBank.getTotalAmountRecon(),
                            tBank.getTotalNumberRecon()
                    );
                    trBankRepository.save(entity);
                }
                logger.info("Data synced successfully");
            } else {
                logger.warn("No data received from TrBankServiceClient");
            }
        } catch (Exception e) {
            logger.error("Error syncing data", e);
        }
    }

    private long getStartDate() {
        return LocalDateTime.now(ZoneOffset.UTC)
                .minusDays(1)
                .withHour(17)
                .withMinute(0)
                .withSecond(0)
                .withNano(0)
                .toEpochSecond(ZoneOffset.UTC);
    }

    private long getEndDate() {
        return LocalDateTime.now(ZoneOffset.UTC)
                .withHour(17)
                .withMinute(0)
                .withSecond(0)
                .withNano(0)
                .toEpochSecond(ZoneOffset.UTC);
    }
}
