package com.сleaning_sсhedule.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Configuration
@Component
public class TelegramConfig {
    private static final String URL_TEMPLATE = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s";

    @Value("${telegram.api_token}")
    private String apiToken;

    @Value("${telegram.chat_id}")
    private String chatId;

    private String telegramChatUrl;

    @PostConstruct
    private void postConstruct() {
        telegramChatUrl = String.format(URL_TEMPLATE, apiToken, chatId);
    }
}
