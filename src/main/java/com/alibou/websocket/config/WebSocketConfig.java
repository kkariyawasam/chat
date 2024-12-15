package com.alibou.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


/**
 * WebSocket configuration class to enable STOMP message handling.
 * Sets up endpoints and message routing prefixes for WebSocket communication.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
	 public static final String WS_ENDPOINT = "/ws";
	 public static final String CLIENT_TO_SERVER_PREFIX = "/app";
	 public static final String SERVER_TO_CLIENT_PREFIX = "/topic";

	
	//registers a web socket endpoint for clients to connect to
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(WS_ENDPOINT).withSockJS();
    }

    //configures the messaging behavior for a websocket application
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
    	//Client-to-Server
        registry.setApplicationDestinationPrefixes(CLIENT_TO_SERVER_PREFIX);
        //Server-to-Client
        registry.enableSimpleBroker(SERVER_TO_CLIENT_PREFIX);
    }
}
