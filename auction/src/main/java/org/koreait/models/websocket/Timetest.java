package org.koreait.models.websocket;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


//더 이상 사용되지 않는 클래스입니다. 작업 완전히 완료시 삭제 예정입니다.
@Service
public class Timetest {
    //int test = 1;
    @Scheduled(fixedRateString = "1000")
    private void TimeAlertTest(){
        //밀리세컨드 단위입니다. 현재 이 메서드는 1초마다 작업을 수행합니다.
        //test += 1;
        //System.out.println("1초마다 작업 수행 테스트! " + test);
        TimeCalLogic calLogic = new TimeCalLogic();
        calLogic.CountDown();
    }

}
