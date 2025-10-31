package com.bookhub.net.discovery;

import com.bookhub.util.exception.NetworkException;
import org.slf4j.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 * UDP 广播示例，用于局域网服务发现。
 */
public class UdpDiscoveryBroadcaster implements AutoCloseable, Runnable {

    private final Logger logger;
    private final DatagramSocket socket;
    private final String message;
    private final int port;
    private volatile boolean running = true;

    public UdpDiscoveryBroadcaster(Logger logger, int port, String message) {
        this.logger = logger;
        this.port = port;
        try {
            this.socket = new DatagramSocket();
        } catch (IOException e) {
            throw new NetworkException("无法创建 UDP 套接字", e);
        }
        this.message = message;
    }

    @Override
    public void run() {
        logger.info("开始广播服务发现消息");
        while (running) {
            try {
                byte[] data = message.getBytes(StandardCharsets.UTF_8);
                DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("255.255.255.255"), port);
                socket.send(packet);
                Thread.sleep(1000);
            } catch (IOException | InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("广播失败", e);
                break;
            }
        }
    }

    @Override
    public void close() {
        running = false;
        socket.close();
    }
}
