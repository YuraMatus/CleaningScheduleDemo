package com.сleaning_sсhedule.service;

import com.mashape.unirest.http.exceptions.UnirestException;

public interface BotService {
    void sendMessage(String message) throws UnirestException;
}
