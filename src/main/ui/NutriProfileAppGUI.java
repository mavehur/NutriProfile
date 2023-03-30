package ui;

import model.Ingredient;
import model.IngredientDatabase;
import persistence.JsonReader;
import persistence.JsonWriter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;


// Graphical Interface for NutriProfile application
public class NutriProfileAppGUI extends JFrame implements ActionListener {

    private IngredientDatabase ingredientDb;

    private static final String JSON_STORE = "./data/IngredientDatabase.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    private JList<String> ingredientJList;
    private DefaultListModel<String> listModel;


    private JButton viewList;
    private JButton addIngredient;
    private JButton saveList;
    private JButton loadList;
//    private JButton searchIngredient;
//    private JButton deleteIngredients;

    private JTextField categoryField = new JTextField(15);
    private JTextField nameField = new JTextField(15);
    private JTextField reasonField = new JTextField(15);

    private JLabel categoryLabel = new JLabel("Enter category name (GOOD or BAD): ");
    private JLabel nameLabel = new JLabel("Enter ingredient name: ");
    private JLabel reasonLabel = new JLabel("Enter reason (e.g. allergy causing): ");

    private JWindow splashScreen;

    // EFFECTS: runs NutriProfile GUI application
    public NutriProfileAppGUI() {
        super("Your NutriProfile");
        initializeSplashScreen();
        splashScreen.dispose();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Font font = new Font("Courier New", Font.PLAIN, 13);
        UIManager.put("Button.font", font);
        UIManager.put("Label.font", font);
        UIManager.put("TextField.font", font);
        categoryLabel.setFont(font);
        nameLabel.setFont(font);
        reasonLabel.setFont(font);

        setPreferredSize(new Dimension(600, 250));
        createIngredientDb();
        createInputLayout();
        createButtonLayout();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    // This method is modeled based on https://www.youtube.com/watch?v=Kmgo00avvEw
    // MODIFIES: this
    // EFFECTS: initializes a JWindow for a splash screen before the main menu
    public void initializeSplashScreen() {
        splashScreen = new JWindow();
        splashScreen.setSize(576, 324);
        JLabel splashLabel = new JLabel(new ImageIcon("visual components/NutriProfile splash_resized.gif"));
        splashScreen.add(splashLabel);
        splashScreen.pack();
        splashScreen.setLocationRelativeTo(null);
        splashScreen.setVisible(true);

        Graphics2D g = (Graphics2D)splashScreen.getRootPane().getGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // This method is modeled based on https://www.youtube.com/watch?v=Kmgo00avvEw
    // EFFECTS: creates an Ingredient list.
    public void createIngredientDb() {
        ingredientDb = new IngredientDatabase();
        listModel = new DefaultListModel<>();

        for (Ingredient i: ingredientDb.getIngredientDb()) {
            listModel.addElement(i.toString());
        }
        ingredientJList = new JList<>(listModel);
        ingredientJList.setVisibleRowCount(3);
    }

    // This method is modeled based on ListDemo: https://docs.oracle.com/javase/tutorial/uiswing/components/list.html
    // EFFECTS: creates a layout for buttons.
    public void createButtonLayout() {
        addIngredient = new JButton("Add Ingredient");
        addIngredient.addActionListener(this);

        viewList = new JButton("View My List");
        viewList.addActionListener(new ViewListListener());

        saveList = new JButton("Save My List");
        saveList.addActionListener(new SaveListListener());

        loadList = new JButton("Load My List");
        loadList.addActionListener(new LoadListListener());
//        searchIngredient = new JButton("Seach Ingredients");
//        searchIngredient.addActionListener(new SearchIngredientLister());
//        deleteIngredients = new JButton("Delete Ingredients");
//        deleteIngredients.addActionListener(new DeleteIngredientListener());

        JPanel buttonPane = new JPanel();
        buttonPane.setBackground(new Color(240, 248, 255));
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));

        buttonPane.add(addIngredient);
        buttonPane.add(viewList);
        buttonPane.add(saveList);
        buttonPane.add(loadList);
//        buttonPane.add(searchIngredient);
//        buttonPane.add(deleteIngredients);
        buttonPane.add(Box.createHorizontalGlue());
        add(buttonPane);
    }

    // This method is modeled based on https://www.youtube.com/watch?v=Kmgo00avvEw
    // EFFECTS: creates layout for user's input for ingredient
    public void createInputLayout() {
        JPanel inputPane = new JPanel();
        inputPane.setBackground(new Color(240, 248, 255));
        inputPane.setLayout(new BoxLayout(inputPane, BoxLayout.Y_AXIS));
        inputPane.add(categoryLabel);
        inputPane.add(categoryField);
        inputPane.add(nameLabel);
        inputPane.add(nameField);
        inputPane.add(reasonLabel);
        inputPane.add(reasonField);
        add(inputPane, BorderLayout.BEFORE_FIRST_LINE);
    }

    // This method is modeled based on https://www.youtube.com/watch?v=Kmgo00avvEw
    //EFFECTS: displays an image on the pop-up window
    public void displayImage(String fileName) {
        JFrame listedImage = new JFrame();
        listedImage.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        listedImage.setPreferredSize(new Dimension(200, 200));
        ImageIcon image = new ImageIcon(fileName);
        JLabel imageLabel = new JLabel(image);
        listedImage.add(imageLabel);
        listedImage.pack();
        listedImage.setLocationRelativeTo(null);
        listedImage.setVisible(true);
    }


    // EFFECTS: adds a new ingredient from user's input into ingredient database if user filled in all the blanks.
    @Override
    public void actionPerformed(ActionEvent e) {
        String category = categoryField.getText();
        String name = nameField.getText();
        String reason = reasonField.getText();

        if (category == null || name == null || reason == null
                || category.isEmpty() || name.isEmpty() || reason.isEmpty()) {
            JOptionPane.showMessageDialog(NutriProfileAppGUI.this,
                    "Please fill in all fields.");
        } else {
            Ingredient ingredient = new Ingredient(category, name, reason);
            boolean check = ingredientDb.addToDb(ingredient);
            if (check) {
                updateIngredientList();
            } else {
                JOptionPane.showMessageDialog(NutriProfileAppGUI.this, ingredient.getName()
                        + " is already in the list. Please add a different one.");
            }
        }
        categoryField.setText("");
        nameField.setText("");
        reasonField.setText("");
    }

    // EFFECTS: updates ingredient list
    private void updateIngredientList() {
        listModel.clear();
        for (Ingredient i : ingredientDb.getIngredientDb()) {
            listModel.addElement(i.toString());
        }
    }


    // action listener for viewing list
    class ViewListListener implements ActionListener {

        // This method is modeled based on https://www.youtube.com/watch?v=Kmgo00avvEw
        // EFFECTS: displays ingredient list
        @Override
        public void actionPerformed(ActionEvent e) {
//            listModel.clear();
//            for (Ingredient i : ingredientDb.getIngredientDb()) {
//                listModel.addElement(i.toString());
//            }
            JFrame popup = new JFrame("");
            JScrollPane listPane = new JScrollPane(ingredientJList);
            popup.add(listPane);
            popup.pack();
            popup.setLocationRelativeTo(null);
            popup.setVisible(true);
        }

    }

    // This method is modeled based on https://www.youtube.com/watch?v=Kmgo00avvEw
    // action listener for saving file
    class SaveListListener implements ActionListener {

        // EFFECT: saves IngredientDatabase to file
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(ingredientDb);
                jsonWriter.close();
                displayImage("./visual components/saved_resized.png");
            } catch (FileNotFoundException exc) {
                JOptionPane.showMessageDialog(NutriProfileAppGUI.this,
                        "FileNotFoundException occurred!");
            }
        }
    }

    // This method is modeled based on https://www.youtube.com/watch?v=Kmgo00avvEw
    // action listener for loading file
    class LoadListListener implements ActionListener {

        // MODIFIES: this
        // EFFECTS: loads IngredientDatabase from file
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ingredientDb = jsonReader.read();
                listModel.clear();
                for (Ingredient i: ingredientDb.getIngredientDb()) {
                    listModel.addElement(i.toString());
                }
                displayImage("./visual components/loaded_resized.png");
            } catch (IOException exc) {
                JOptionPane.showMessageDialog(NutriProfileAppGUI.this, "IOException occurred!");
            }
        }
    }

}


