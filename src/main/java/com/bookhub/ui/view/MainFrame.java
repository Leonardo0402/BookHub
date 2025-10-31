package com.bookhub.ui.view;

import com.bookhub.ui.controller.MainController;
import com.bookhub.ui.model.LibraryTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Swing 主窗口骨架。包含菜单、工具栏、表格、状态栏等组件，展示事件监听与布局。
 */
public class MainFrame extends JFrame {

    private final MainController controller;
    private final LibraryTableModel tableModel;
    private final JLabel statusLabel = new JLabel("Ready");
    private final ItemDetailsPanel detailsPanel = new ItemDetailsPanel();

    public MainFrame(MainController controller, LibraryTableModel tableModel) {
        super("BookHub 学习版");
        this.controller = controller;
        this.tableModel = tableModel;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setJMenuBar(createMenuBar());
        add(createToolBar(), BorderLayout.NORTH);
        add(createMainPanel(), BorderLayout.CENTER);
        add(createStatusBar(), BorderLayout.SOUTH);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("文件");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(new JMenuItem(new AbstractAction("退出") {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }));
        menuBar.add(fileMenu);

        JMenu searchMenu = new JMenu("搜索");
        searchMenu.add(new JMenuItem(new AbstractAction("刷新") {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.performSearch("");
                statusLabel.setText("显示全部条目");
                detailsPanel.showItem(null);
            }
        }));
        menuBar.add(searchMenu);
        return menuBar;
    }

    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        JTextField keywordField = new JTextField(20);
        JButton searchButton = new JButton("搜索");
        Runnable doSearch = () -> {
            String kw = keywordField.getText() == null ? "" : keywordField.getText().trim();
            controller.performSearch(kw);
            statusLabel.setText("搜索: " + kw);
        };
        searchButton.addActionListener(e -> doSearch.run());
        keywordField.addActionListener(e -> doSearch.run());
        toolBar.add(keywordField);
        toolBar.add(searchButton);
        return toolBar;
    }

    private JComponent createMainPanel() {
        JTable table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    var item = tableModel.getItemAt(row);
                    statusLabel.setText("选中: " + item);
                    detailsPanel.showItem(item);
                }
            }
        });
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(table), detailsPanel);
        split.setResizeWeight(0.6);
        return split;
    }

    private JPanel createStatusBar() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 8));
        panel.add(statusLabel, BorderLayout.WEST);
        return panel;
    }
}

