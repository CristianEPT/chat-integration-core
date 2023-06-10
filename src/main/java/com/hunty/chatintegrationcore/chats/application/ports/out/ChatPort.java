package com.hunty.chatintegrationcore.chats.application.ports.out;

import java.util.List;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface ChatPort {

  List<String> getAllMessage(String chatId);

  List<String> getAllChats();

  void sendMessage(String chatId, String message) throws TelegramApiException;
}
