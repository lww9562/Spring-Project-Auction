package org.koreait.config;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log
@Component
public class DataWsHandler extends TextWebSocketHandler {

    private static List<WebSocketSession> sessions = new ArrayList<>();
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //웹소켓 연결 성립시
        log.info("연결 성립!");
        sessions.add(session);
        sendTime();
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String data = message.getPayload();
        if(data.equals("now")){
            sendTime();
        }

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //웹소켓 연결 종료시
        log.info("연결 종료!");
        sendTime();
        sessions.remove(session);
    }

    private void sendTime(){
        sessions.stream().forEach(s -> {
            TextMessage msg = new TextMessage(LocalDateTime.now().toString());
            try {
                s.sendMessage(msg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

}
