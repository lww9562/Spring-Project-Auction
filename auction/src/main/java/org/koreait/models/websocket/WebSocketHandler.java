package org.koreait.models.websocket;




import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.PropertySource;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import org.springframework.beans.factory.BeanFactoryUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



@Slf4j //로깅 코드를 작성하는 애노테이션. lombok
public class WebSocketHandler extends TextWebSocketHandler {

    //웹소켓 최초 연결시 Map 자료구조에 세션 저장.
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    //웹소켓 연결
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        var sessionId = session.getId();
        sessions.put(sessionId, session); //1. 세션 저장

        Message message = Message.builder()
                .sender(sessionId)
                .receiver("all")
                .build();
        message.newConnect();

        sessions.values().forEach(s -> { //2. 모든 세션에 알림
            try{
                if(!s.getId().equals(sessionId)){
                    s.sendMessage(new TextMessage(message.toString()));
                }
            }
            catch(Exception e){

            }
        });

    }

    //양방향 데이터 통신
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {

        //리시버 값을 받아서, 전송을 해주어야 합니다.
        //전제 조건이 있습니다. 받는 사람의 웹 소켓이 열려 있어야 합니다.

        //TextMessage를 objectMapper를 이용해서  Message 형태로 형변환 해주는 과정입니다.
        ObjectMapper objectMapper = new ObjectMapper();
        String handleMessage = textMessage.getPayload();

        Message message = objectMapper.readValue(handleMessage, Message.class);
        message.setSender(session.getId());

        //1. 메시지를 전달할 타겟 상대방을 찾는다.
        WebSocketSession receiver = sessions.get(message.getReceiver());


        if(receiver != null && receiver.isOpen()){
            //2. 타겟이 존재하고 연결된 상태라면, 메세지를 전송한다.
            receiver.sendMessage(new TextMessage(message.toString()));
        }

        //pong을 수신받으면 로그를 띄우는 메서드입니다.
        //로그가 띄워진다는건, 연결이 정상이라는 뜻으로 봐도 되겠습니다.
        //WebSocketSession의 만료시간을 별도로 또 저장해야 한다고 합니다...
        //일정 시간동안 pong을 수신받지 못한다면 만료시간이 갱신되지 않아 세션을 삭제하는 방향으로..
        //주기적으로 모든 세션의 만료시간을 체크하는 로직도 구현해야 한다고 합니다..
        //if(textMessage.getPayload().equalsIgnoreCase("pong")){
        //    log.info("Received pong : {}", session.getId());
        //}

    }

    //소켓 통신 에러
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }

    //소켓 연결 종료 (정상 종료)
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        var sessionId = session.getId();
        //정상적으로 연결을 종료시, 세션에 저장된 유저 정보를 제거합니다.
        sessions.remove(sessionId);
    }

    //서버와 클라이언트의 통신을 주기적으로 체크하기 위한 메서드입니다.
    //ping과 pong 방식이며, 서버에서 ping을 주면 클라이언트에서는 pong을 보내는 형식입니다.
    //만약 둘 중 하나가 제대로 되지 않는다면 연결에 문제가 생긴걸로 간주한다고 보면 되겠습니다.
    //https://brunch.co.kr/@springboot/801
    @Scheduled(fixedRate = 1000)
    public void expire(){
        if(!sessions.isEmpty()) {
            sessions.values().forEach(webSocketSession -> {
                try {
                    webSocketSession.sendMessage(new PingMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
    //Ping을 받았을 시 실행되는 Pong 메서드 입니다.
    //통신 확인용으로만 사용
    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        System.out.println("pong!");
    }
}
