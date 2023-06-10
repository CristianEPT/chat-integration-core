package com.hunty.chatintegrationcore.chats.adapter.in.web;

import com.hunty.chatintegrationcore.chats.adapter.out.telegram.TelegramAdapter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@RestController
@RequestMapping("messages")
@RequiredArgsConstructor
public class ChatController {

  private final TelegramAdapter telegramAdapter;

  @GetMapping("/chats")
  public List<String> getChats() {
    return telegramAdapter.getAllChats();
  }

  @PostMapping
  public boolean sendMessage() throws TelegramApiException {
    telegramAdapter.sendMessage("712780578", "Hello");
    return true;
  }

  @GetMapping
  public List<String> getAllMessages() {
    return new ArrayList<>();
  }
}
