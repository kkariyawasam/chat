# WebSocket Chat Application

This project is a Spring Boot-based WebSocket chat application for real-time messaging using STOMP over WebSocket.

## Features

- Real-time messaging between users.
- Notifications when users join or leave the chat.
- Configurable WebSocket endpoints and message brokers.

## Key Components

1. **ChatController**: Handles chat messages and user events.

   - `/chat.sendMessage`: Broadcasts messages to all users.
   - `/chat.addUser`: Adds a user and stores their username.

2. **WebSocketConfig**: Configures WebSocket endpoints and messaging behavior.

   - WebSocket endpoint: `/ws`
   - Client-to-server prefix: `/app`
   - Server-to-client prefix: `/topic`

3. **WebSocketEventListener**: Listens for user disconnections and notifies others in the chat.

## Technologies Used

- Spring Boot
- WebSocket
- STOMP
- Lombok
