package com.hunty.chatintegrationcore.chats.adapter.out.persistence;

import com.hunty.chatintegrationcore.chats.adapter.out.persistence.mongo.model.Message;
import com.hunty.chatintegrationcore.chats.adapter.out.persistence.mongo.repository.MessageRepository;
import com.hunty.chatintegrationcore.chats.application.ports.out.MessagePort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MessagesAdapter implements MessagePort {

  private final MessageRepository messageRepository;

  @Override
  public void saveMessage(String chatId, String message) {
    var messageModel = Message.builder().chatId(chatId).message(message).build();
    messageRepository.save(messageModel);
  }

  @Override
  public List<String> getAllChats() {
    return messageRepository.findDistinctByChatId().stream().map(Message::getChatId).toList();
  }
}
