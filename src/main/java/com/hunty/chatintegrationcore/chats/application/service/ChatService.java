package com.hunty.chatintegrationcore.chats.application.service;

import com.hunty.chatintegrationcore.chats.application.ports.in.ChatUseCase;
import com.hunty.chatintegrationcore.chats.application.ports.out.ChatPort;
import com.hunty.chatintegrationcore.chats.application.ports.out.MessagePort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService implements ChatUseCase {
  private final ChatPort chatPort;
  private final MessagePort messagePort;

  @Override
  public List<String> getAllChats() {
    return messagePort.getAllChats();
  }

  @Override
  public boolean sendMessage(String chatId, String message) {
    try {
      chatPort.sendMessage(chatId, message);
      messagePort.saveMessage(chatId, message);
      return true;
    } catch (Exception e) {
      log.error("cannot send message to {} ", chatId, e);
      return false;
    }
  }

  @Override
  public List<String> getAllMessages() {
    return messagePort.getAllMessages();
  }
}
