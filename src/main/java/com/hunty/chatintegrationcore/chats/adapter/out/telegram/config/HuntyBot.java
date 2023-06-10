package com.hunty.chatintegrationcore.chats.adapter.out.telegram.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
@Slf4j
public class HuntyBot extends TelegramLongPollingBot {

  @Value("${telegram.bot.credentials.bot-username}")
  private String username;

  @Value("${telegram.bot.credentials.token}")
  private String token;

  @Bean
  public TelegramBotsApi telegramBotsApi() throws TelegramApiException {
    var telegramBots = new TelegramBotsApi(DefaultBotSession.class);
    telegramBots.registerBot(this);
    return telegramBots;
  }

  @Override
  public void onUpdateReceived(Update update) {
    log.info(
        "received --> chatID {}, text {}",
        update.getMessage().getChatId(),
        update.getMessage().getText());
  }

  @Override
  public void clearWebhook() throws TelegramApiRequestException {}

  @Override
  public String getBotUsername() {
    return username;
  }

  @Override
  public String getBotToken() {
    return token;
  }
}
