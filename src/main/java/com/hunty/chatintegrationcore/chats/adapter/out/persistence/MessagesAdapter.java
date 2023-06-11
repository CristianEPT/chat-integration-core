package com.hunty.chatintegrationcore.chats.adapter.out.persistence;

import com.hunty.chatintegrationcore.chats.adapter.out.persistence.mongo.model.MessageModel;
import com.hunty.chatintegrationcore.chats.adapter.out.persistence.mongo.repository.MessageRepository;
import com.hunty.chatintegrationcore.chats.application.ports.out.MessagePort;
import com.hunty.chatintegrationcore.chats.domain.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MessagesAdapter implements MessagePort {

  private final MessageRepository messageRepository;

  @Override
  public void saveMessage(Message message) {
    var messageModel =
        MessageModel.builder()
            .chatId(message.chatId())
            .message(message.message())
            .date(message.date())
            .build();
    messageRepository.save(messageModel);
  }

  @Override
  public List<String> getAllChats() {
    return messageRepository.findAll().stream()
        .collect(Collectors.groupingBy(MessageModel::getChatId))
        .values()
        .stream()
        .flatMap(group -> group.stream().limit(1))
        .map(MessageModel::getChatId)
        .collect(Collectors.toList());
  }

  @Override
  public List<String> getAllMessages(String chatId) {
    Optional<List<MessageModel>> optionalMessages = messageRepository.findAllByChatId(chatId);
    return optionalMessages
        .map(messages -> messages.stream().map(MessageModel::getMessage).toList())
        .orElseGet(ArrayList::new);
  }
}
