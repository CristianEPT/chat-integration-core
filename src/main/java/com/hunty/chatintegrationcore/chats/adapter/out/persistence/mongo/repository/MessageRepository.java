package com.hunty.chatintegrationcore.chats.adapter.out.persistence.mongo.repository;

import com.hunty.chatintegrationcore.chats.adapter.out.persistence.mongo.model.Message;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    Optional<List<Message>> findAllByChatId(String chatId);

    List<Message> findDistinctByChatId();
}
