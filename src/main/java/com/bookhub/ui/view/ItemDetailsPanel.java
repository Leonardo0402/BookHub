package com.bookhub.ui.view;

import com.bookhub.domain.*;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Collectors;

/**
 * 简单的详情面板：显示选中条目的关键信息。
 */
public class ItemDetailsPanel extends JPanel {

    private final JTextArea area = new JTextArea();

    public ItemDetailsPanel() {
        super(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("详情"));
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        add(new JScrollPane(area), BorderLayout.CENTER);
    }

    public void showItem(MediaItem item) {
        if (item == null) {
            area.setText("");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("标题: ").append(item.getTitle()).append('\n');
        sb.append("作者: ").append(item.getAuthor()).append('\n');
        sb.append("年份: ").append(item.getPublishYear()).append('\n');
        sb.append("ID: ").append(item.getId()).append('\n');
        if (item.tags() != null && !item.tags().isEmpty()) {
            sb.append("标签: ").append(item.tags().stream().collect(Collectors.joining(", "))).append('\n');
        }
        if (item instanceof Book b) {
            if (b.getIsbn() != null && !b.getIsbn().isBlank()) {
                sb.append("ISBN: ").append(b.getIsbn()).append('\n');
            }
            if (b.getSummary() != null && !b.getSummary().isBlank()) {
                sb.append("简介: ").append(b.getSummary()).append('\n');
            }
        } else if (item instanceof Audio a) {
            sb.append("类型: 音频").append('\n');
            sb.append("时长(秒): ").append(a.durationSeconds()).append('\n');
            sb.append("朗读者: ").append(a.getNarrator() == null ? "未知" : a.getNarrator()).append('\n');
            if (a.associatedFile() != null) sb.append("文件: ").append(a.associatedFile()).append('\n');
        } else if (item instanceof Video v) {
            sb.append("类型: 视频").append('\n');
            sb.append("时长(秒): ").append(v.durationSeconds()).append('\n');
            sb.append("分辨率: ").append(v.getResolution() == null ? "未知" : v.getResolution()).append('\n');
            if (v.associatedFile() != null) sb.append("文件: ").append(v.associatedFile()).append('\n');
        }

        area.setText(sb.toString());
        area.setCaretPosition(0);
    }
}
