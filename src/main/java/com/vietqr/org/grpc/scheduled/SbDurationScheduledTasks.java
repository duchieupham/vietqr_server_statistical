package com.vietqr.org.grpc.scheduled;

import com.example.grpc.SbDuration;
import com.vietqr.org.entity.SbDurationEntity;
import com.vietqr.org.grpc.reactive.SbDurationServiceClient;
import com.vietqr.org.repository.SbDurationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class SbDurationScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(SbDurationScheduledTasks.class);

    @Autowired
    private SbDurationServiceClient sbDurationServiceClient;

    @Autowired
    private SbDurationRepository sbDurationRepository;

    @Scheduled(cron = "0 * * * * ?")
    //    @Scheduled(cron = "0 0 21 * * ?")
    @Transactional
    public void synData() {
        try {
            long expired = getExpiredDate();
            long nearingExpiration = getNearingExpirationDate();
            logger.info("Syncing data from {} to {}", expired, nearingExpiration);
            SbDuration sbDuration = sbDurationServiceClient.getSbDuration(expired, nearingExpiration);

            if (sbDuration != null) {
                SbDurationEntity entity = new SbDurationEntity(
                    UUID.randomUUID().toString(),
                    LocalDateTime.now(ZoneOffset.UTC).getYear(),
                    LocalDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM")),
                    LocalDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE),
                        sbDuration.getNearingExpirationCount(),
                        sbDuration.getExpiredCount()
                );
                sbDurationRepository.save(entity);
                logger.info("Data synced successfully");
            } else {
                logger.warn("No data received from SbDurationServiceClient");
            }
        } catch (Exception e) {
            logger.error("Error syncing data", e);
        }
    }

    private long getExpiredDate() {
        return LocalDateTime.now(ZoneOffset.UTC)
                .minusDays(1)
                .withHour(17)
                .withMinute(0)
                .withSecond(0)
                .withNano(0)
                .toEpochSecond(ZoneOffset.UTC);
    }

    private long getNearingExpirationDate() {
        return LocalDateTime.ofEpochSecond(getExpiredDate(), 0, ZoneOffset.UTC)
                .plusDays(7)
                .toEpochSecond(ZoneOffset.UTC);
    }
}
