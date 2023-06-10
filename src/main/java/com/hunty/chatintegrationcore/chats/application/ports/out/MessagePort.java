package com.hunty.chatintegrationcore.chats.application.ports.out;

import java.util.List;

public interface MessagePort {

    void saveMessage(String chatId, String message);

    List<String> getAllChats();

}
