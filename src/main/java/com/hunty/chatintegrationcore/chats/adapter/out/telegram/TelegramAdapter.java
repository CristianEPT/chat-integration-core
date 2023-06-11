package com.hunty.chatintegrationcore.chats.adapter.out.telegram;

import com.hunty.chatintegrationcore.chats.adapter.out.telegram.config.HuntyBot;
import com.hunty.chatintegrationcore.chats.application.ports.out.ChatPort;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@RequiredArgsConstructor
@Component
@Slf4j
public class TelegramAdapter implements ChatPort {

  private final HuntyBot bot;

  @Override
  public void sendMessage(String chatId, String message) throws TelegramApiException {
    var messageToSend = new SendMessage();
    messageToSend.setChatId(chatId);
    messageToSend.setText(message);

    var sendDocument = new SendDocument();
    InputFile inputFile = new InputFile();
    sendDocument.setDocument(inputFile);

    bot.execute(messageToSend);
  }

  @Override
  public void sendDocument(String chatId) throws TelegramApiException, IOException {

    var sendDocument = new SendDocument();

    var inputFile = getInputFile();
    sendDocument.setDocument(inputFile);
    sendDocument.setChatId(chatId);

    bot.execute(sendDocument);
  }

  private InputFile getInputFile() throws IOException {
    var inputFile = new InputFile();
    inputFile.setMedia(getFileToSend(), "FileTest.pdf");
    return inputFile;
  }

  private InputStream getFileToSend() throws IOException {
    return Objects.requireNonNull(getClass().getClassLoader().getResource("CV_CP_ES.pdf"))
        .openStream();
  }
}
