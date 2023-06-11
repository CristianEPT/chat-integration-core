package com.hunty.chatintegrationcore.chats.adapter.in.web.exception;

public class NoDataFoundException extends RuntimeException {

  public NoDataFoundException() {
    super("Rule not found");
  }
}
