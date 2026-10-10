import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class ZooFrame extends JFrame {
    // In-memory collection storing animals (Polymorphism)
    private final ArrayList<Animal> zooAnimals = new ArrayList<>();

    // GUI Form Components
    private JTextField txtName;
    private JTextField txtAge;
    private JComboBox<String> cmbAnimalType;
    private JTextField txtFurColor;
    private JCheckBox chkCanFly;
    private JPanel specificPanel;
    private CardLayout specificCardLayout;
    private JTextArea txtLog;

    public ZooFrame() {
        setTitle("Digital Zoo Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 560);
        setMinimumSize(new Dimension(600, 480));
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        // Main Container with padding
        JPanel mainPanel = new JPanel(new BorderLayout(12, 12));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Header Panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblTitle = new JLabel("Digital Zoo Manager");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(new Color(33, 66, 99));
        headerPanel.add(lblTitle);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Center Split: Left = Input Form, Right/Bottom = Log Display
        JPanel centerPanel = new JPanel(new BorderLayout(12, 12));

        // --- 1. Input Form Panel ---
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new CompoundBorder(
                new TitledBorder(BorderFactory.createLineBorder(new Color(180, 195, 210)), " Add New Animal "),
                new EmptyBorder(10, 12, 12, 12)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        formPanel.add(new JLabel("Animal Name:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtName = new JTextField(15);
        formPanel.add(txtName, gbc);

        // Age Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        formPanel.add(new JLabel("Age (Years):"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtAge = new JTextField(15);
        formPanel.add(txtAge, gbc);

        // Animal Type ComboBox
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        formPanel.add(new JLabel("Animal Type:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        String[] types = {"Generic Mammal", "Generic Bird"};
        cmbAnimalType = new JComboBox<>(types);
        formPanel.add(cmbAnimalType, gbc);

        // Dynamic Specific Attribute Panel (CardLayout)
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;

        specificCardLayout = new CardLayout();
        specificPanel = new JPanel(specificCardLayout);

        // Mammal Sub-panel (Fur Color)
        JPanel mammalPanel = new JPanel(new BorderLayout(8, 0));
        mammalPanel.add(new JLabel("Fur Color:      "), BorderLayout.WEST);
        txtFurColor = new JTextField(15);
        mammalPanel.add(txtFurColor, BorderLayout.CENTER);

        // Bird Sub-panel (Can Fly?)
        JPanel birdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        chkCanFly = new JCheckBox("Can Fly?");
        chkCanFly.setSelected(true);
        birdPanel.add(chkCanFly);

        specificPanel.add(mammalPanel, "Generic Mammal");
        specificPanel.add(birdPanel, "Generic Bird");
        formPanel.add(specificPanel, gbc);

        // Buttons Panel inside Form
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(12, 6, 6, 6);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        JButton btnAdd = new JButton("Add Animal");
        btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAdd.setFocusPainted(false);

        JButton btnReset = new JButton("Reset Form");
        btnReset.setFocusPainted(false);

        btnPanel.add(btnAdd);
        btnPanel.add(btnReset);
        formPanel.add(btnPanel, gbc);

        centerPanel.add(formPanel, BorderLayout.NORTH);

        // --- 2. Log Display Area ---
        JPanel logPanel = new JPanel(new BorderLayout(6, 6));
        logPanel.setBorder(new CompoundBorder(
                new TitledBorder(BorderFactory.createLineBorder(new Color(180, 195, 210)), " Activity Log & Zoo Roster "),
                new EmptyBorder(8, 8, 8, 8)
        ));

        txtLog = new JTextArea();
        txtLog.setEditable(false);
        txtLog.setFont(new Font("Consolas", Font.PLAIN, 13));
        txtLog.setLineWrap(true);
        txtLog.setWrapStyleWord(true);
        txtLog.setText("=== Digital Zoo Manager Initialized ===\nReady to register animals into the zoo.\n\n");

        JScrollPane scrollPane = new JScrollPane(txtLog);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        logPanel.add(scrollPane, BorderLayout.CENTER);

        // Footer buttons below Log
        JPanel logActionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        JButton btnShowAll = new JButton("Show All Animals");
        JButton btnClearLog = new JButton("Clear Log");

        logActionPanel.add(btnShowAll);
        logActionPanel.add(btnClearLog);
        logPanel.add(logActionPanel, BorderLayout.SOUTH);

        centerPanel.add(logPanel, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        setContentPane(mainPanel);

        // --- 3. Event Listeners ---
        // Switch specific attribute panel based on combo selection
        cmbAnimalType.addActionListener(e -> {
            String selected = (String) cmbAnimalType.getSelectedItem();
            if (selected != null) {
                specificCardLayout.show(specificPanel, selected);
            }
        });

        // Add Animal Button
        btnAdd.addActionListener(e -> handleAddAnimal());

        // Reset Form Button
        btnReset.addActionListener(e -> resetForm());

        // Show All Animals Button (Polymorphic iteration)
        btnShowAll.addActionListener(e -> handleShowAllAnimals());

        // Clear Log Button
        btnClearLog.addActionListener(e -> txtLog.setText("=== Activity Log Cleared ===\n\n"));
    }

    private void handleAddAnimal() {
        String name = txtName.getText().trim();
        String ageStr = txtAge.getText().trim();
        String selectedType = (String) cmbAnimalType.getSelectedItem();

        // 1. Validation
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter an animal name.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE);
            txtName.requestFocus();
            return;
        }

        if (ageStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter the animal's age.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE);
            txtAge.requestFocus();
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageStr);
            if (age < 0) {
                JOptionPane.showMessageDialog(this,
                        "Age must be a non-negative number.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE);
                txtAge.requestFocus();
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Invalid age! Please enter a valid whole number.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            txtAge.requestFocus();
            return;
        }

        // 2. Object Creation & Polymorphism
        Animal newAnimal;
        String logPrefix;

        if ("Generic Mammal".equals(selectedType)) {
            String furColor = txtFurColor.getText().trim();
            if (furColor.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter the mammal's fur color.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE);
                txtFurColor.requestFocus();
                return;
            }
            newAnimal = new Mammal(name, age, furColor);
            logPrefix = "Added a new Mammal!";
        } else {
            boolean canFly = chkCanFly.isSelected();
            newAnimal = new Bird(name, age, canFly);
            logPrefix = "Added a new Bird!";
        }

        // 3. Add to ArrayList<Animal>
        zooAnimals.add(newAnimal);

        // 4. Update JTextArea Log with getInfo() and makeSound()
        String logEntry = String.format("%s Info: %s. Sound: %s\n",
                logPrefix,
                newAnimal.getInfo(),
                newAnimal.makeSound());
        txtLog.append(logEntry);
        txtLog.setCaretPosition(txtLog.getDocument().getLength());

        // 5. Reset inputs for next entry
        resetForm();
    }

    private void resetForm() {
        txtName.setText("");
        txtAge.setText("");
        txtFurColor.setText("");
        chkCanFly.setSelected(true);
        txtName.requestFocus();
    }

    private void handleShowAllAnimals() {
        if (zooAnimals.isEmpty()) {
            txtLog.append("\n[ZOO ROSTER] Currently, there are no animals in the zoo.\n\n");
            return;
        }

        txtLog.append(String.format("\n=== CURRENT ZOO ROSTER (Total: %d Animals) ===\n", zooAnimals.size()));
        int index = 1;
        // Demonstrating Polymorphic loop over ArrayList<Animal>
        for (Animal animal : zooAnimals) {
            txtLog.append(String.format("%d. %s | Sound: %s\n",
                    index++,
                    animal.getInfo(),
                    animal.makeSound()));
        }
        txtLog.append("===================================================\n\n");
        txtLog.setCaretPosition(txtLog.getDocument().getLength());
    }
}
