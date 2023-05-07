package org.koreait.models.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocket //웹소켓 서버를 사용하도록 정의
public class WebSocketConfiguration implements WebSocketConfigurer {

    //웹 소켓 요청은 반드시 GET 메서드를 사용해야 한다고 합니다.

    //bidderNo를 기반으로 누구인지 판별
    //물건의 경매만료까지 남은 시간이 제일 필요한 사람은 경매에 참여한 사람이기 때문에,
    //바이더를 기준으로 할 예정입니다.

    //https://brunch.co.kr/@springboot/695 참고
    //ping : 남은시간 - 현재시간 결과 요청 핑
    //pong : 남은시간 - 현재시간 결과


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(signalingSocketHandler(), "user/mypage") //웹소켓 서버의 엔드포인트를 url:port/user/mypage로 정의
                .setAllowedOrigins("*"); //클라이언트에서 웹소켓 서버에 요청시 모든 요청을 수용하게 함.
        //실제 서비스 서버에서는 서버환경에 맞게 변형해야 한다고 합니다.

    }

    @Bean
    public WebSocketHandler signalingSocketHandler(){
        return new WebSocketHandler();
        //따로 만든 WebSocketHandler 클래스를 웹소켓 핸들러로 정의하였습니다.
    }


}
