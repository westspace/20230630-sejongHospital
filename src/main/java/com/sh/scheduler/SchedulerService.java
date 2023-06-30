package com.sh.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sh.config.SchedulerConfig;

import lombok.RequiredArgsConstructor;

/**
 * 1. 초(0-59)
 * 2. 분(0-59)
 * 3. 시간(0-23)
 * 4. 일(1-31)
 * 5. 월(1-12)
 * 6. 요일(0-7)
 * <p>
 * * : 모든 값
 * ? : 설정 없음(일과 요일만 사용 가능)
 * , : 배열 ex) 1,5,8 : 1,5,8에만
 * - : 앞부터 뒤까지 ex) 1-3 : 1부터 3까지
 * / : 앞부터 뒤마다 ex) 1/3 : 1부터 매3마다 1,4,7,11...
 * <p>
 * fixedDelay : 작업이 끝난 시점 기준, milliseconds 마다 동작
 * fixedDelayString : fixedDelay와 동일, 속성값만 String으로 입력
 * fixedRate : 작업이 시작한 시점 기준, milliseconds 마다 동작
 * fixedRateString : fixedRate와 동일, 속성값만 String으로 입력
 * initialDelay : 최초 수행 지연 시간, milliseconds 이후에 실행
 * initialDelayString : initialDelay와 동일, 속성값만 String으로 입력
 * <p>
 * ex)
 * "0 0 * * * *" = the top of every hour of every day.(매일 매시 정각)
 * "* /10 * * * * *" = 매 10초마다 실행한다.
 * "0 0 8-10 * * *" = 매일 8, 9, 10시에 실행한다
 * "0 0 6,19 * * *" = 매일 오전 6시, 오후 7시에 실행한다.
 * "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30, 10:00 and 10:30 every day.
 * "0 0 9-17 * * MON-FRI" = 오전 9시부터 오후 5시까지 주중(월~금)에 실행한다.
 * "0 0 0 25 12 ?" = every Christmas Day at midnight(매년 크리스마스 자정)
 */

@Component
@RequiredArgsConstructor
public class SchedulerService extends SchedulerConfig {
   
	 /**
     * test
     */
    @Scheduled(fixedDelay = 3000)
    public void testttt() { 
    	System.out.println("zzzz : ");
	}

    /**
     * 1시간 마다 3시간(10800000) 경과한 유저 ID 체크 히스토리 목록 삭제
     * */
    @Scheduled(fixedDelay = 3600000)
    public void deleteUserIdHistory3Hour() {
        //userService.deleteUserIdHistory(10800000L);
    }

    /**
     * 매 시간 마다 구독 시간 체크
     */
    @Scheduled(cron = "0 0 * * * *")
    public void CheckExpirationDate() { 
    	//shareStorageService.CheckExpirationDate();
	}

//    public void now() throws IOException {
//        firebaseCloudMessageService.sendMessageTo("eJCL1-HlQnGzGy0tbkyaf7:APA91bEoPAANW0R2PRSyfrv4O10j1N3kNAwkr5LBfqM5edkghiAl_hJV3cyNToQZxySsF40Pf4RWLelRE7-6r170YzektOol23TXj2M_IQsJtZu0QMhZ-bNoNG_gbKb0yB4ZAWICJPgI", "콩아", "안뇽");
//    }


//    @Transactional
//    @Scheduled(fixedDelay = 10000)
//    public void insertMember() {
//        List<Member> members = new ArrayList<>();
//        IntStream.rangeClosed(1001, 10000).forEach(value -> {
//            try {
//                Member member = Member.builder()
//                        .accountId(AES256.encrypt("aaa" + value))
//                        .password(passwordEncoder.encode(AES256.encrypt("1111")))
//                        .phoneNumber(AES256.encrypt("0100000" + value))
//                        .token("tokenistoken")
//                        .build();
//                members.add(member);
//            } catch (GeneralSecurityException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//        memberRepository.saveAll(members);
//    }

    // 매 초마다 실행 테스트
//    @Scheduled(fixedDelay = 3000)
//    public void schedulerTest1() throws IOException, ClassNotFoundException {
//        System.out.println("CUR_KEY: " + AES256.KEY);
//        AES256.setAES256Key();
//        System.out.println("NEW_KEY: " + AES256.KEY);
//        System.out.println("---------------------------------------------");
//
//        AES256.setAES256Key();
//        Map<String, Object> keys = AES256.getAES256Key();
//        String currKey = (String) keys.get("currKey");
//        String prevKey = (String) keys.get("prevKey");
//        System.out.println("currKey: " + currKey);
//        System.out.println("prevKey: " + prevKey);
//    }

    // 매 초마다 실행
//    @Scheduled(fixedDelay = 1000)
//    public void schedulerTest2() throws Exception {
//        String threadName = Thread.currentThread().getName();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        System.out.println("ThreadName: " + threadName + ", 현재 시간 : " + simpleDateFormat.format(new Date()));
//    }

    // 매달 1일 정각에 실행
//    @Scheduled(cron = "0 0 0 1 * *")
//    @Scheduled(fixedDelay = 100000)
//    @Transactional
//    public void updateAES256Key() throws Exception {
//        // AES256 새로운 키 생성 및 모든 회원 정보 업데이트
//        System.out.println(LocalDateTime.now() + "  key update start");
//        long start = System.currentTimeMillis();
//        List<Member> members = memberRepository.findAll();
//        List<Member> list = new ArrayList<>();
//
//        for(Member member : members) {
//            member.setAccountId(AES256.decrypt(member.getAccountId()));
//            member.setPhoneNumber(AES256.decrypt(member.getPhoneNumber()));
//            list.add(member);
//        }
//
//        System.out.println(LocalDateTime.now() + "  CUR_KEY : " + AES256.KEY);
//        AES256.setAES256Key();
//        System.out.println(LocalDateTime.now() + "  NEW_KEY : " + AES256.KEY);
//
//        for(Member member : list) {
//            member.setAccountId(AES256.encrypt(member.getAccountId()));
//            member.setPhoneNumber(AES256.encrypt(member.getPhoneNumber()));
//        }
//        memberRepository.saveAll(list);
//        long end = System.currentTimeMillis();
//        System.out.println("작업 시간: " + (end - start) / 1000.0);
//    }

    // 매달 정각 정각에 실행
//    @Scheduled(cron = "0 0 0 1 * *")
//    public void SetStatistics() {
//        // 매 12시 정각마다 새로운 데이터 입력
//    }


    // 5초에만 실행
//    @Scheduled(cron = "5 * * * * *")
//    public void printDate1() {
//        System.out.println("5초에만 발생: " + new Date());
//    }
//
//    // 매 10초마다 실행
//    @Scheduled(cron = "*/10 * * * * *")
//    public void printDate2() {
//        System.out.println("매 10초마다: " + new Date());
//    }
//
//    // 1,2,3 실행
//    @Scheduled(cron = "1-3 * * * * *")
//    public void printDate3() {
//        System.out.println("1-3: " + new Date());
//    }
//
//    // 매 0분에 실행
//    @Scheduled(cron = "0 * * * * *")
//    public void printDate4() {
//        System.out.println("0: " + new Date());
//    }
}
