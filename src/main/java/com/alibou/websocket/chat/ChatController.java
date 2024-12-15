package com.alibou.websocket.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
	
	private static final String TOPIC_PUBLIC = "/topic/public";

	//handles messages sent in a chat application
    @MessageMapping("/chat.sendMessage")
    @SendTo(TOPIC_PUBLIC)
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo(TOPIC_PUBLIC)
    public ChatMessage addUser(@Payload ChatMessage chatMessage,SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
