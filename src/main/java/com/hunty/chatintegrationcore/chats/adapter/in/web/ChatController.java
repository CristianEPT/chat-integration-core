package com.hunty.chatintegrationcore.chats.adapter.in.web;

import com.hunty.chatintegrationcore.chats.application.ports.in.ChatUseCase;
import com.hunty.chatintegrationcore.chats.domain.Message;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("chats")
@RequiredArgsConstructor
public class ChatController {

  private final ChatUseCase chatUseCase;

  @GetMapping
  public List<String> getChats() {
    return chatUseCase.getAllChats();
  }

  @PostMapping("/message")
  public boolean sendMessage(
      @RequestHeader("ChatId") String chatId, @RequestBody MessageRequest messageRequest) {

    var message = new Message(chatId, messageRequest.message(), messageRequest.date());
    return chatUseCase.sendMessage(message);
  }

  @GetMapping("/message")
  public List<String> getAllMessages(@RequestHeader("ChatId") String chatId) {

    return chatUseCase.getAllMessages(chatId);
  }
}
