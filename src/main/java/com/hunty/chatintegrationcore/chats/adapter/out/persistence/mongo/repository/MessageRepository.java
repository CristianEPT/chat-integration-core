package com.hunty.chatintegrationcore.chats.adapter.out.persistence.mongo.repository;

import com.hunty.chatintegrationcore.chats.adapter.out.persistence.mongo.model.MessageModel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<MessageModel, String> {

  Optional<List<MessageModel>> findAllByChatId(String chatId);


}
