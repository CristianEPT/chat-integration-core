package com.hunty.chatintegrationcore.chats.application.ports.in;

import com.hunty.chatintegrationcore.chats.domain.Message;
import java.util.List;

public interface ChatUseCase {


    List<String> getAllChats();

    boolean sendMessage(Message message);

    List<String> getAllMessages(String chatId);

}
