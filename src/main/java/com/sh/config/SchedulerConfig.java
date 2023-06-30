package com.sh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
*
* 1. 초(0-59)
* 2. 분(0-59)
* 3. 시간(0-23)
* 4. 일(1-31)
* 5. 월(1-12)
* 6. 요일(0-7)
*
* * : 모든 값
* ? : 설정 없음(일과 요일만 사용 가능)
* , : 배열 ex) 1,5,8 : 1,5,8에만
* - : 앞부터 뒤까지 ex) 1-3 : 1부터 3까지
* / : 앞부터 뒤마다 ex) 1/3 : 1부터 매3마다 1,4,7,11...
*
* fixedDelay : 작업이 끝난 시점 기준, milliseconds 마다 동작
* fixedDelayString : fixedDelay와 동일, 속성값만 String으로 입력
* fixedRate : 작업이 시작한 시점 기준, milliseconds 마다 동작
* fixedRateString : fixedRate와 동일, 속성값만 String으로 입력
* initialDelay : 최초 수행 지연 시간, milliseconds 이후에 실행
* initialDelayString : initialDelay와 동일, 속성값만 String으로 입력
*
* ex)
* "0 0 * * * *" = the top of every hour of every day.(매일 매시 정각)
* "* /10 * * * * *" = 매 10초마다 실행한다.
* "0 0 8-10 * * *" = 매일 8, 9, 10시에 실행한다
* "0 0 6,19 * * *" = 매일 오전 6시, 오후 7시에 실행한다.
* "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30, 10:00 and 10:30 every day.
* "0 0 9-17 * * MON-FRI" = 오전 9시부터 오후 5시까지 주중(월~금)에 실행한다.
* "0 0 0 25 12 ?" = every Christmas Day at midnight(매년 크리스마스 자정)
*
* 스케쥴러의 메소드는 파라미터를 사용할 수 없으며, 리턴타입은 void여야 한다.
* TaskExecutor는 task를 주로 비동기적으로 처리할때 쓰고 TaskScheduler는 스케줄링할때 쓴다.
*
* */

@Configuration
public class SchedulerConfig implements SchedulingConfigurer {

   @Override
   public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
       ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();

       threadPoolTaskScheduler.setPoolSize(Runtime.getRuntime().availableProcessors() * 2);
       threadPoolTaskScheduler.setThreadNamePrefix("Scheduler-Thread-");
       threadPoolTaskScheduler.initialize();

       taskRegistrar.setTaskScheduler(threadPoolTaskScheduler);
   }

}
