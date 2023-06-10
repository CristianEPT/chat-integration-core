package com.hunty.chatintegrationcore.chats.application.ports.in;

import java.util.List;

public interface ChatUseCase {


    List<String> getAllChats();

    boolean sendMessage(String chatId, String message);

    List<String> getAllMessages();

}
