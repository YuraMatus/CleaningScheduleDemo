package com.сleaning_sсhedule.service.impl;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.сleaning_sсhedule.entity.User;
import com.сleaning_sсhedule.service.BotService;
import com.сleaning_sсhedule.service.NotificationService;
import com.сleaning_sсhedule.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private UserService userService;

    @Autowired
    private BotService service;

    @Override
    @Scheduled(cron = "0 18 * * * *")
    @Transactional
    public void notifyUsers() {
        Collection<User> allUsers = userService.getAllUsers();
        LocalDate now = LocalDate.now();

        allUsers.stream()
                .map(User::getRecords)
                .filter(records -> records.stream()
                        .anyMatch(record -> Period.between(record.getDate(), now).getDays() >= 2)
                )
                .map(List::size)
                .min(Integer::compareTo)
                .flatMap(
                        min -> allUsers.stream()
                                .filter(user -> user.getRecords().size() == min)
                                .min(Comparator.comparing(User::getId)))
                .ifPresent(user -> {
                    try {
                        service.sendMessage(user.getUsername() + " твоя черга виносити сміття");
                    } catch (UnirestException e) {
                        e.printStackTrace();
                    }
                });
    }
}
