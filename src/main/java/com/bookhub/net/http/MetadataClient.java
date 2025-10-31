package com.bookhub.net.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bookhub.util.exception.NetworkException;
import org.slf4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * HTTP 客户端示例，使用 Java 11 的 HttpClient 查询在线元数据。
 */
public class MetadataClient {

    private final Logger logger;
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MetadataClient(Logger logger) {
        this.logger = logger;
    }

    public JsonNode fetchMetadata(String endpoint) {
        try {
            HttpRequest request = HttpRequest.newBuilder(URI.create(endpoint)).GET().build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info("HTTP {} -> {}", endpoint, response.statusCode());
            return objectMapper.readTree(response.body());
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new NetworkException("获取远程元数据失败", e);
        }
    }
}
