package com.bookhub.domain;

import java.nio.file.Path;
import java.time.Instant;
import java.util.Collection;

/**
 * 视频媒体，展示继承、多态与组合属性的用法。
 */
public class Video extends MediaItem implements Playable {

    private final Path filePath;
    private final long durationSeconds;
    private final String resolution;

    public Video(String id, String title, String author, int publishYear, Path filePath, long durationSeconds,
                 String resolution, Instant createdAt, Collection<String> tags) {
        super(id, title, author, publishYear, createdAt, tags);
        this.filePath = filePath;
        this.durationSeconds = durationSeconds;
        this.resolution = resolution;
    }

    public Path getFilePath() {
        return filePath;
    }

    public String getResolution() {
        return resolution;
    }

    @Override
    public long durationSeconds() {
        return durationSeconds;
    }

    @Override
    public String playDescription() {
        return "播放视频《" + getTitle() + "》，分辨率：" + (resolution == null ? "未知" : resolution);
    }

    @Override
    public String toJson() {
        return "{" +
                "\"type\":\"video\"," +
                "\"id\":\"" + getId() + "\"," +
                "\"title\":\"" + getTitle() + "\"," +
                "\"resolution\":\"" + (resolution == null ? "" : resolution) + "\"," +
                "\"duration\":" + durationSeconds +
                "}";
    }

    @Override
    public String defaultExportFilename() {
        return getTitle().replaceAll("\\s+", "_") + "-video.json";
    }

    @Override
    public Path associatedFile() {
        return filePath;
    }
}
