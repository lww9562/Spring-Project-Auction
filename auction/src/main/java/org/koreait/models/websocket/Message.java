package org.koreait.models.websocket;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
@ToString
public class Message {
    //현재, 예제 문제를 따라서 해보고 있는 중이므로,
    //나중에 대폭 변경될 예정입니다.
    //필요없는 기능까지 쓰고 있는 중이므로..

    private String type;
    private String sender;
    private String receiver;
    private Object data;

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void newConnect(){
        this.type = "new";
    }

    public void closeConnect(){
        this.type = "close";
    }


}
