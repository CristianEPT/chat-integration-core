package com.hunty.chatintegrationcore.chats.adapter.out.telegram;

import com.hunty.chatintegrationcore.chats.adapter.out.telegram.config.HuntyBot;
import com.hunty.chatintegrationcore.chats.application.ports.out.ChatPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@RequiredArgsConstructor
@Component
@Slf4j
public class TelegramAdapter implements ChatPort {

  private final HuntyBot bot;

  @Override
  public void sendMessage(String chatId, String message) throws TelegramApiException {
    var messageToSend = new SendMessage();
    messageToSend.setChatId(chatId);
    messageToSend.setText(message);
    bot.execute(messageToSend);
  }
}
