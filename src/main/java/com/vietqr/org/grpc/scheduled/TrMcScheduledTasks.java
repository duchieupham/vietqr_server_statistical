package com.vietqr.org.grpc.scheduled;

import com.example.grpc.TrMc;
import com.vietqr.org.entity.TrMcEntity;
import com.vietqr.org.grpc.reactive.TrMcServiceClient;
import com.vietqr.org.repository.TrMcRepository;
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
public class TrMcScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(TrMcScheduledTasks.class);

    @Autowired
    private TrMcServiceClient trMcServiceClient;

    @Autowired
    private TrMcRepository trMcRepository;

    @Scheduled(cron = "0 * * * * ?")
//        @Scheduled(cron = "0 0 21 * * ?")
    @Transactional
    public void syncData() {
        try {
            long startDate = getStartDate();
            long endDate = getEndDate();
            logger.info("Syncing data from {} to {}", startDate, endDate);
            List<TrMc> trMcList = trMcServiceClient.getTrMc(startDate, endDate);

            if (trMcList != null && !trMcList.isEmpty()) {
                for (TrMc trMc : trMcList) {
                    TrMcEntity entity = new TrMcEntity(
                            UUID.randomUUID().toString(),
                            LocalDateTime.now(ZoneOffset.UTC).getYear(),
                            LocalDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM")),
                            LocalDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE),
                            trMc.getMerchantName(),
                            trMc.getTotalNumberCredits(),
                            trMc.getTotalAmountCredits(),
                            trMc.getTotalAmountRecon(),
                            trMc.getTotalNumberRecon()
                    );
                    trMcRepository.save(entity);
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
