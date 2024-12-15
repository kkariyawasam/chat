package com.alibou.websocket.config;

import com.alibou.websocket.chat.ChatMessage;
import com.alibou.websocket.chat.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListener {
	
	/**
	 * WebSocketEventListener listens for WebSocket events, such as user disconnects,
	 * and sends notifications to the public topic when a user leaves the chat.
	 */
	
	private static final String PUBLIC_TOPIC = "/topic/public";


    private final SimpMessageSendingOperations messagingTemplate;

    //Notify everyone when a user leaves the chat
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
            log.info("user disconnected: {}", username);
            var chatMessage = ChatMessage.builder()
                    .type(MessageType.LEAVE)
                    .sender(username)
                    .build();
            messagingTemplate.convertAndSend(PUBLIC_TOPIC, chatMessage);
        }
    }

}
