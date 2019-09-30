package com.сleaning_sсhedule.service.impl;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.сleaning_sсhedule.config.TelegramConfig;
import com.сleaning_sсhedule.service.BotService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class BotServiceImpl implements BotService {
    @Autowired
    private TelegramConfig telegramConfig;

    public void sendMessage(String text) throws UnirestException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM HH:mm:ss");
        Unirest.get(telegramConfig.getTelegramChatUrl())
                .queryString("text", text+dateFormat.format( new Date() ))
                .asBinary();
    }
}
