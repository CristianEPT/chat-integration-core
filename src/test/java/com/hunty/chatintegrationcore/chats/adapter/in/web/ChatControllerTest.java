package com.hunty.chatintegrationcore.chats.adapter.in.web;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.hunty.chatintegrationcore.chats.application.ports.in.ChatUseCase;
import com.hunty.chatintegrationcore.chats.domain.Message;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@WebMvcTest(controllers = ChatController.class, properties = "spring.cloud.config.enabled=false")
class ChatControllerTest {

  private static String BASE_URL = "/chats";

  @Autowired MockMvc mockMvc;

  @MockBean ChatUseCase chatUseCase;

  @Test
  void getChats() throws Exception {

    when(chatUseCase.getAllChats()).thenReturn(new ArrayList<>());

    mockMvc
        .perform(get(BASE_URL).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    then(chatUseCase).should(times(1)).getAllChats();
  }

  @Test
  void sendMessage() throws Exception {

    var message = new Message("001", "test", 1L);
    when(chatUseCase.sendMessage(message)).thenReturn(true);
    var messageRequest = new MessageRequest("test", 1L);
    var gson = new Gson();
    mockMvc
        .perform(
            post(BASE_URL + "/message")
                .header("ChatId", "001")
                .content(gson.toJson(messageRequest))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    then(chatUseCase).should(times(1)).sendMessage(message);
  }

  @Test
  void getAllMessages() throws Exception {

    when(chatUseCase.getAllMessages("001")).thenReturn(new ArrayList<>());

    mockMvc
        .perform(
            get(BASE_URL + "/message")
                .header("ChatId", "001")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    then(chatUseCase).should(times(1)).getAllMessages("001");
  }
}
