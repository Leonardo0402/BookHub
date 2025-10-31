package com.bookhub.ui.model;

import com.bookhub.domain.MediaItem;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Swing 表格模型，展示 MVC 中 Model 的实现方式。
 */
public class LibraryTableModel extends AbstractTableModel {

    private final List<MediaItem> items = new ArrayList<>();
    private final String[] columns = {"ID", "标题", "作者", "年份"};

    public void setItems(List<MediaItem> items) {
        this.items.clear();
        if (items != null) {
            this.items.addAll(items);
        }
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MediaItem item = items.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> item.getId();
            case 1 -> item.getTitle();
            case 2 -> item.getAuthor();
            case 3 -> item.getPublishYear();
            default -> "";
        };
    }

    public MediaItem getItemAt(int row) {
        return items.get(row);
    }
}
