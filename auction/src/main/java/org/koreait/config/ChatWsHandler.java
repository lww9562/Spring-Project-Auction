package org.koreait.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.koreait.models.chat.ChatMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Log
public class ChatWsHandler extends TextWebSocketHandler {

    private static List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        //ObjectMapper
            //.readValue : JSON 문자열 -> 자바 객체
            //.writeValue : 자바 객체 -> JSON 문자열


        session.sendMessage(createMessage("시스템", "접속자", "반갑습니다."));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
        log.info(msg);
        sessions.stream().forEach(s -> {
            try {
                s.sendMessage(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        session.sendMessage(createMessage("시스템","접속자","안녕히 가세요."));
        sessions.remove(session);
    }

    private TextMessage createMessage(String sender, String receiver, String message) throws Exception{
        ChatMessage chatMessage = ChatMessage.builder()
                .sender(sender)
                .receiver(receiver)
                .message(message)
                .build();

        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(chatMessage);


        return new TextMessage(json);
    }

}
