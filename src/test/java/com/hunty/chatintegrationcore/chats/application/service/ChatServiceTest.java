package com.hunty.chatintegrationcore.chats.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.hunty.chatintegrationcore.chats.application.ports.out.ChatPort;
import com.hunty.chatintegrationcore.chats.application.ports.out.MessagePort;
import com.hunty.chatintegrationcore.chats.domain.Message;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@ExtendWith(MockitoExtension.class)
class ChatServiceTest {

  @Mock ChatPort chatPort;
  @Mock MessagePort messagePort;
  @InjectMocks ChatService chatService;

  @Test
  void getAllChats() {
    var chats = List.of("1234");
    given(messagePort.getAllChats()).willReturn(chats);
    var chatsResponse = chatService.getAllChats();
    assertThat(chatsResponse).isNotEmpty();
    assertThat(chatsResponse.get(0)).isEqualTo("1234");
  }

  @Test
  void sendMessage() throws TelegramApiException {
    var message = new Message("001", "test", 1L);
    var response = chatService.sendMessage(message);
    assertThat(response).isTrue();
    then(chatPort).should(times(1)).sendMessage("001", "test");
    then(messagePort).should(times(1)).saveMessage(message);
  }

  @Test
  void getAllMessages() {

    var messages = List.of("test");
    given(messagePort.getAllMessages("001")).willReturn(messages);
    var chatsResponse = chatService.getAllMessages("001");
    assertThat(chatsResponse).isNotEmpty();
    assertThat(chatsResponse.get(0)).isEqualTo("test");
  }
}
