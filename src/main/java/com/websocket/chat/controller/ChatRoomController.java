package com.websocket.chat.controller;

import com.websocket.chat.entity.ChatMessage;
import com.websocket.chat.entity.ChatRoom;
import com.websocket.chat.service.ChatMessageService;
import com.websocket.chat.service.ChatRoomService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller

@RequestMapping("")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;
    @GetMapping("/chat/rooms")
    @Operation(summary="채팅방 조회하기")
    public String showRooms(Model model) {
        List<ChatRoom> rooms = chatRoomService.findAllRoom();
        model.addAttribute("rooms", rooms);
        return "chat/room_list";
    }
    @GetMapping("/chat/room/{roomId}")
    public String roomDetail(@PathVariable String roomId, Model model) {
        ChatRoom room = chatRoomService.findRoomById(roomId);
        List<ChatMessage> messages = chatMessageService.getMessagesByRoomId(roomId);
        model.addAttribute("room", room);
        model.addAttribute("messages", messages);
        return "chat/room_detail";
    }

    // 모든 채팅방 목록 조회
    @GetMapping("/rooms")
    public List<ChatRoom> getAllRooms() {
        return chatRoomService.findAllRoom();
    }

    // 새로운 채팅방 생성
    @PostMapping("/chat/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestBody Map<String, String> requestBody) {
        String name = requestBody.get("name");
        return chatRoomService.createChatRoom(name);
    }
    // 채팅방 세부 정보 조회
    @GetMapping("/room/{roomId}")
    public ChatRoom roomDetail(@PathVariable String roomId) {
        return chatRoomService.findRoomById(roomId);
    }
}
