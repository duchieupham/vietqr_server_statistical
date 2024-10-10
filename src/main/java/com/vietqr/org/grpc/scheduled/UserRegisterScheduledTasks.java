package com.vietqr.org.grpc.scheduled;

import com.example.grpc.UserRegister;
import com.vietqr.org.entity.UsDateEntity;
import com.vietqr.org.entity.UsMonthEntity;
import com.vietqr.org.grpc.reactive.UserRegisterServiceClient;
import com.vietqr.org.repository.UsDateRepository;
import com.vietqr.org.repository.UsMonthRepository;
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
public class UserRegisterScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(UserRegisterScheduledTasks.class);

    @Autowired
    private UserRegisterServiceClient userRegisterServiceClient;

    @Autowired
    private UsDateRepository usDateRepository;

    @Autowired
    private UsMonthRepository usMonthRepository;

    //@Scheduled(cron = "0 * * * * ?")
    @Scheduled(cron = "0 0 21 * * ?")
    @Transactional
    public void syncData() {
        try {
            long startDate = getStartDate();
            long endDate = getEndDate();
            logger.info("Syncing data from {} to {}", startDate, endDate);
            UserRegister userRegister = userRegisterServiceClient.getUserRegister(startDate, endDate);

            if (userRegister != null) {
                UsDateEntity entity = new UsDateEntity(
                        UUID.randomUUID().toString(),
                        LocalDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE),
                        userRegister.getUserCount(),
                        userRegister.getIosPlatform(),
                        userRegister.getAndroidPlatform(),
                        userRegister.getWebPlatform()
                );
                usDateRepository.save(entity);
                logger.info("Data synced successfully");

                String currentMonth = LocalDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM"));
                UsMonthEntity monthEntity = usMonthRepository.findByMonth(currentMonth);

                if (monthEntity != null) {
                    monthEntity.setUserCount(String.valueOf(Integer.parseInt(monthEntity.getUserCount()) + Integer.parseInt(userRegister.getUserCount())));
                    monthEntity.setIosPlatform(monthEntity.getIosPlatform() + userRegister.getIosPlatform());
                    monthEntity.setAndroidPlatform(monthEntity.getAndroidPlatform() + userRegister.getAndroidPlatform());
                    monthEntity.setWebPlatform(monthEntity.getWebPlatform() + userRegister.getWebPlatform());
                } else {
                    monthEntity = new UsMonthEntity(
                            UUID.randomUUID().toString(),
                            currentMonth,
                            userRegister.getUserCount(),
                            userRegister.getIosPlatform(),
                            userRegister.getAndroidPlatform(),
                            userRegister.getWebPlatform()
                    );
                }
                usMonthRepository.save(monthEntity);
                logger.info("Data updated in UserRegisterMonth");
            } else {
                logger.warn("No data received from UserRegisterServiceClient");
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
