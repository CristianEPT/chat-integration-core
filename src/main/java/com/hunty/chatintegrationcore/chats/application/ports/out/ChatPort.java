package com.hunty.chatintegrationcore.chats.application.ports.out;

import java.io.IOException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface ChatPort {

  void sendMessage(String chatId, String message) throws TelegramApiException;

  void sendDocument(String chatId) throws TelegramApiException, IOException;
}
