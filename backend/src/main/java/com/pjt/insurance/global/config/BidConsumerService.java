//package com.pjt.insurance.global.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.pjt.insurance.user.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class BidConsumerService {
//
//    private final EventAlarmService eventAlarmService;
//    private final UserService userService;
//    private final ExArticleService exArticleService;
//    private final ObjectMapper objectMapper;
//
////    @KafkaListener(topics = "bidding-notifications", groupId = "your-group-id", containerFactory = "kafkaJsonContainerFactory")
////    public void listenToBidNotifications(BidNotification bidNotification) {
////
////        try {
////            // 수신한 BidNotification 객체를 처리하는 로직
////            System.out.println("Received notification for member: " + userService.getUserInfobyid(bidNotification.getUserId()));
////            System.out.println("ExArticle: " + exArticleService.getbyid(bidNotification.getArticleId()));
////            System.out.println("Redirect URL: " + bidNotification.getRedirectUrl());
////
////            // 추가적인 처리 로직을 여기에 구현
////        } catch (Exception e) {
////            // 예외 타입에 따라 다른 처리를 할 수 있습니다.
////            System.err.println("Error processing bid notification: " + e.getClass().getSimpleName() + " - " + e.getMessage());
////            e.printStackTrace();
////        }
////
////
////    }
//
//    @KafkaListener(topics = "test", groupId = "your-group-id", containerFactory = "kafkaJsonContainerFactory")
//    public void test(String string  ) {
//
//        try {
//            // 수신한 BidNotification 객체를 처리하는 로직
//            System.out.println("Received notification for member: " + string);
//            BidNotification bidNotification = objectMapper.readValue(string, BidNotification.class);
//
//            MemberProfile buyer = userService.getUserEntityById(bidNotification.getUserId());
//            ExArticle article =exArticleService.getbyid(bidNotification.getArticleId());
//
//            eventAlarmService.notifyChatRoomAuthor(buyer,article , bidNotification.getRedirectUrl() );
//            // 추가적인 처리 로직을 여기에 구현
//        } catch (Exception e) {
//            // 예외 타입에 따라 다른 처리를 할 수 있습니다.
//            System.err.println("Error processing bid notification: " + e.getClass().getSimpleName() + " - " + e.getMessage());
//            e.printStackTrace();
//        }
//
//
//    }
//
//}
