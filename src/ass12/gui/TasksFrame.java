package ass12.gui;

import javax.swing.*;
import java.awt.*;

public class TasksFrame extends JFrame {

    private TasksTableModel model = new TasksTableModel();

    public TasksFrame() {
        super("Task generator");

        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scroll);

        JButton addButton = new JButton("Add task");
        addButton.addActionListener(e -> addTask());
        add(addButton, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
    }

    private void addTask() {
        model.addTask();
    }

    public void refresh() {
        model.fireTableDataChanged();
    }

}
