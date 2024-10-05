package com.websocket.chat.controller;

import com.websocket.chat.entity.ChatMessage;
import com.websocket.chat.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@Controller
public class ChatController {

    private final ChatMessageService chatMessageService;
    private final SimpMessagingTemplate messagingTemplate;

    // 클라이언트가 "/pub/chat/message"로 메시지를 보내면 이 메서드가 실행됨
    @MessageMapping("/chat/message")
    public void sendMessage(ChatMessage message) {
        // 메시지를 DB에 저장
        chatMessageService.saveMessage(message);

        // 특정 채팅방 구독자들에게 메시지 전송
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}

