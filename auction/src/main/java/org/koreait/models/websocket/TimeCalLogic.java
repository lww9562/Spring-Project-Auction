package org.koreait.models.websocket;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class TimeCalLogic {


    //한국시간 기준 현재시간을 반환하는 메서드.
    public static LocalDateTime nowFromZone(){
        return ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDateTime();
    }

    //시간계산 테스트용
    public static LocalDateTime mockTime;


    public Long CountDown(){
        //구매자의 물품 정보를 받아오고,
        //물품의 "경매만료시간"을 받아와 남은 시간을 반환하는 메서드.

        try{

            LocalDateTime nowTime = nowFromZone();

            if(mockTime == null){
                mockTime = nowTime.plusHours(72l);
            }

            Duration duration = Duration.between(nowTime, mockTime);
            long betweenTime = duration.getSeconds();

            long hour = betweenTime / (60*60);
            long minute = betweenTime % (60*60) / 60;
            long second = betweenTime % (60*60) % 60;




            System.out.println(hour + " 시간 " + minute + " 분 " + second + " 초 남음" );


        } catch(Exception e){
            e.printStackTrace();
        }
        //임시로 null로 설정. 미완성 로직
    return null;
    }

}
