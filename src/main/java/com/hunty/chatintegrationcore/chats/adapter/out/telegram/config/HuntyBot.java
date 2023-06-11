package com.hunty.chatintegrationcore.chats.adapter.out.telegram.config;

import com.hunty.chatintegrationcore.chats.application.ports.out.MessagePort;
import com.hunty.chatintegrationcore.chats.domain.Message;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
@Slf4j
@RequiredArgsConstructor
public class HuntyBot extends TelegramLongPollingBot {

  private final MessagePort messagePort;

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
    var chatId = update.getMessage().getChatId();
    var text = update.getMessage().getText();
    log.info("received --> chatID {}, text {}", chatId, text);
    var message = new Message(chatId.toString(), text, Instant.now().toEpochMilli());
    messagePort.saveMessage(message);
  }

  @Override
  public void clearWebhook() {}

  @Override
  public String getBotUsername() {
    return username;
  }

  @Override
  public String getBotToken() {
    return token;
  }
}
