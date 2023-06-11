package com.hunty.chatintegrationcore.chats.application.ports.out;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface ChatPort {

  void sendMessage(String chatId, String message) throws TelegramApiException;
}
