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
                    .filter(p -> p.getRace().getId() == race.getId())
                    .count();
            message.append(runners + " runners taking part in " + race.getName() + ".\n");
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
