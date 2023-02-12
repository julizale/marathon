package com.marathon.scheduler;

import com.marathon.config.AdminConfig;
import com.marathon.domain.Mail;
import com.marathon.domain.Race;
import com.marathon.repository.PerformanceRepository;
import com.marathon.repository.RaceRepository;
import com.marathon.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private static final String SUBJECT = "Marathon daily email";
    private final SimpleEmailService simpleEmailService;
    private final PerformanceRepository performanceRepository;
    private final RaceRepository raceRepository;
    private AdminConfig adminConfig;

    @Scheduled(cron = "0 0 8 * * *")
    public void sendInformationEmail() {
        StringBuilder message = new StringBuilder("Currently in database, there are: \n");
        List<Race> raceList = raceRepository.findAll();
        for (Race race : raceList) {
            long runners = performanceRepository.findAll().stream()
                    .filter(p -> Objects.equals(p.getRace().getId(), race.getId()))
                    .count();
            message.append(runners).append(" runners taking part in ").append(race.getName()).append(".\n");
        }
        simpleEmailService.send(
                new Mail(
                        adminConfig.getAdminMail(),
                        SUBJECT,
                        message.toString()
                )
        );
    }
}
