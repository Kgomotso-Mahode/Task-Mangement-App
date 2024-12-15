
package taskmanagementapp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TaskManagerGUI {

    private ArrayList<String> tasks;
    private JFrame frame;
    private JTextField taskInput;
    private JList<String> taskList; 
    private DefaultListModel<String> taskListModel;

    public TaskManagerGUI() {
        tasks = new ArrayList<>();
        taskListModel = new DefaultListModel<>();
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("Task Manager Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 350);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel taskLabel = new JLabel("Please enter task:");
        taskInput = new JTextField(25);
        inputPanel.add(taskLabel);
        inputPanel.add(taskInput);
        topPanel.add(inputPanel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(new AddTaskListener());
        buttonPanel.add(addButton);

        JButton removeButton = new JButton("Remove Task");
        removeButton.addActionListener(new RemoveTaskListener());
        buttonPanel.add(removeButton);

        topPanel.add(buttonPanel, BorderLayout.EAST);
        frame.add(topPanel, BorderLayout.NORTH);

        taskList = new JList<>(taskListModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        JScrollPane scrollPane = new JScrollPane(taskList);
        frame.add(scrollPane, BorderLayout.CENTER);

        JButton viewButton = new JButton("View Tasks");
        viewButton.addActionListener(new ViewTasksListener());
        frame.add(viewButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private class AddTaskListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String task = taskInput.getText().trim();
            if (!task.isEmpty()) {
                tasks.add(task);
                taskListModel.addElement(task); 
                taskInput.setText("");
                JOptionPane.showMessageDialog(frame, "Task added");
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a task.");
            }
        }
    }

    private class ViewTasksListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    private class RemoveTaskListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                String task = taskListModel.getElementAt(selectedIndex);
                tasks.remove(task);
                taskListModel.remove(selectedIndex); 
                JOptionPane.showMessageDialog(frame, "Task removed");
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a task to remove.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TaskManagerGUI());
    }
}


