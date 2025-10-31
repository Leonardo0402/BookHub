package com.bookhub.domain;

import java.nio.file.Path;
import java.time.Instant;
import java.util.Collection;

/**
 * 音频媒体示例，展示了多接口实现：既是 {@link MediaItem}，也是 {@link Playable}。
 */
public class Audio extends MediaItem implements Playable {

    private final Path filePath;
    private final long durationSeconds;
    private final String narrator;

    public Audio(String id, String title, String author, int publishYear, Path filePath,
                 long durationSeconds, String narrator, Instant createdAt, Collection<String> tags) {
        super(id, title, author, publishYear, createdAt, tags);
        this.filePath = filePath;
        this.durationSeconds = durationSeconds;
        this.narrator = narrator;
    }

    public Path getFilePath() {
        return filePath;
    }

    public String getNarrator() {
        return narrator;
    }

    @Override
    public long durationSeconds() {
        return durationSeconds;
    }

    @Override
    public String playDescription() {
        return "播放音频《" + getTitle() + "》，朗读者：" + (narrator == null ? "未知" : narrator);
    }

    @Override
    public String toJson() {
        return "{" +
                "\"type\":\"audio\"," +
                "\"id\":\"" + getId() + "\"," +
                "\"title\":\"" + getTitle() + "\"," +
                "\"duration\":" + durationSeconds +
                "}";
    }

    @Override
    public String defaultExportFilename() {
        return getTitle().replaceAll("\\s+", "_") + "-audio.json";
    }

    @Override
    public Path associatedFile() {
        return filePath;
    }
}
