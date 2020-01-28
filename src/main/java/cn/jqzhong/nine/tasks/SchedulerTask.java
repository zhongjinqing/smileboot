package cn.jqzhong.nine.tasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author zjq
 * @date 2020/1/28 20:40
 */
@Component
public class SchedulerTask {

    /**
     * taskOne start at
     */
    @Scheduled(cron="* 44 20 * * ?")
    private void taskOne(){
        System.out.println("this is scheduler task runing  in 20:44:00 - 20:45:00");
    }
}
