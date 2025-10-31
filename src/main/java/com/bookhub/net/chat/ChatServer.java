package com.bookhub.net.chat;

import com.bookhub.util.exception.NetworkException;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 简易聊天室服务，演示 Socket 编程的基本模式。
 */
public class ChatServer implements AutoCloseable {

    private final Logger logger;
    private final ServerSocket serverSocket;
    private final Set<ClientHandler> clients = Collections.synchronizedSet(new HashSet<>());

    public ChatServer(Logger logger, int port) {
        try {
            this.logger = logger;
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new NetworkException("无法启动聊天服务器", e);
        }
    }

    public void start() {
        Thread acceptThread = new Thread(() -> {
            logger.info("聊天服务器已启动，端口 {}", serverSocket.getLocalPort());
            while (!serverSocket.isClosed()) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    ClientHandler handler = new ClientHandler(clientSocket);
                    clients.add(handler);
                    new Thread(handler).start();
                } catch (IOException e) {
                    logger.error("接受客户端连接失败", e);
                }
            }
        }, "chat-accept-thread");
        acceptThread.setDaemon(true);
        acceptThread.start();
    }

    @Override
    public void close() throws NetworkException {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new NetworkException("关闭服务器失败", e);
        }
    }

    private class ClientHandler implements Runnable {
        private final Socket socket;

        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
                writer.println("欢迎来到 BookHub 聊天室！");
                String line;
                while ((line = reader.readLine()) != null) {
                    broadcast("[" + socket.getInetAddress() + "] " + line);
                }
            } catch (IOException e) {
                logger.warn("客户端断开: {}", socket.getInetAddress());
            } finally {
                clients.remove(this);
            }
        }

        private void broadcast(String message) {
            synchronized (clients) {
                for (ClientHandler client : clients) {
                    client.send(message);
                }
            }
        }

        private void send(String message) {
            try {
                new PrintWriter(socket.getOutputStream(), true).println(message);
            } catch (IOException e) {
                logger.error("发送消息失败", e);
            }
        }
    }
}
