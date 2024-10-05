package com.websocket.chat.service;

import com.websocket.chat.entity.ChatRoom;

import com.websocket.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;


    public List<ChatRoom> findAllRoom() {

        return chatRoomRepository.findAll();
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRoomRepository.findByRoomId(roomId).orElse(null); // ID로 채팅방 조회
    }

    public ChatRoom createChatRoom(String name) {
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRoomRepository.save(chatRoom); // DB에 저장
        return chatRoom;
    }
    public void enterChatRoom(String roomId){

    }
}
