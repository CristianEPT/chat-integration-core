package com.hunty.chatintegrationcore.chats.application.ports.out;


import com.hunty.chatintegrationcore.chats.domain.Message;
import java.util.List;

public interface MessagePort {

    void saveMessage(Message message);

    List<String> getAllChats();

    List<String> getAllMessages(String chatId);

}
