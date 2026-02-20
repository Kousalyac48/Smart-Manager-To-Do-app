import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SmartTodoApp {

    private JFrame frame;
    private JTextField taskField;
    private DefaultListModel<String> taskModel;
    private JList<String> taskList;

    public SmartTodoApp() {
        frame = new JFrame("Smart To-Do Manager");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        taskField = new JTextField();
        JButton addButton = new JButton("Add Task");

        topPanel.add(taskField, BorderLayout.CENTER);
        topPanel.add(addButton, BorderLayout.EAST);

        // Center List
        taskModel = new DefaultListModel<>();
        taskList = new JList<>(taskModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Bottom Panel
        JPanel bottomPanel = new JPanel();
        JButton deleteButton = new JButton("Delete Task");
        JButton completeButton = new JButton("Mark Complete");
        JButton clearButton = new JButton("Clear All");

        bottomPanel.add(deleteButton);
        bottomPanel.add(completeButton);
        bottomPanel.add(clearButton);

        // Add components to frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Button Actions

        addButton.addActionListener(e -> {
            String task = taskField.getText().trim();
            if (!task.isEmpty()) {
                taskModel.addElement(task);
                taskField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a task!");
            }
        });

        deleteButton.addActionListener(e -> {
            int selected = taskList.getSelectedIndex();
            if (selected != -1) {
                taskModel.remove(selected);
            } else {
                JOptionPane.showMessageDialog(frame, "Select a task to delete!");
            }
        });

        completeButton.addActionListener(e -> {
            int selected = taskList.getSelectedIndex();
            if (selected != -1) {
                String task = taskModel.get(selected);
                taskModel.set(selected, "✔ " + task);
            } else {
                JOptionPane.showMessageDialog(frame, "Select a task to mark complete!");
            }
        });

        clearButton.addActionListener(e -> {
            taskModel.clear();
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SmartTodoApp());
    }
}
