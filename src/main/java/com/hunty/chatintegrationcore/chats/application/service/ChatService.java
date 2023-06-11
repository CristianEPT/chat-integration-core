package com.hunty.chatintegrationcore.chats.application.service;

import com.hunty.chatintegrationcore.chats.application.ports.in.ChatUseCase;
import com.hunty.chatintegrationcore.chats.application.ports.out.ChatPort;
import com.hunty.chatintegrationcore.chats.application.ports.out.MessagePort;
import com.hunty.chatintegrationcore.chats.domain.Message;
import java.time.Instant;
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
  public boolean sendMessage(Message message) {
    try {
      chatPort.sendMessage(message.chatId(), message.message());
      messagePort.saveMessage(message);
      return true;
    } catch (Exception e) {
      log.error("cannot send message to {} ", message.chatId(), e);
      return false;
    }
  }

  @Override
  public boolean sendMessageWithDocument(String chatId) {
    try {
      chatPort.sendDocument(chatId);
      messagePort.saveMessage(new Message(chatId, "send document", Instant.now().toEpochMilli()));
      return true;
    } catch (Exception e) {
      log.error("cannot send message to {} ", chatId, e);
      return false;
    }
  }

  @Override
  public List<String> getAllMessages(String chatId) {
    return messagePort.getAllMessages(chatId);
  }
}
