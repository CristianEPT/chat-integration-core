package com.hunty.chatintegrationcore.chats.adapter.out.persistence.mongo.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Message")
@Data
@Builder
public class Message {

    @Id
    private String id;
    private String chatId;
    private String message;

}
