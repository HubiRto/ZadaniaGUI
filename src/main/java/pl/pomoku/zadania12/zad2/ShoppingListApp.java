package pl.pomoku.zadania12.zad2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ShoppingListApp extends Application {

    private ObservableList<ShoppingItem> shoppingList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        shoppingList = FXCollections.observableArrayList();

        ListView<ShoppingItem> listView = new ListView<>(shoppingList);
        listView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(ShoppingItem item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                }
            }
        });

        Label itemLabel = new Label("Przedmiot:");
        Label quantityLabel = new Label("Ilość:");
        TextField newItemTextField = new TextField();
        Slider quantitySlider = new Slider(1, 10, 1);
        quantitySlider.setShowTickLabels(true);
        quantitySlider.setShowTickMarks(true);
        quantitySlider.setMajorTickUnit(1);
        quantitySlider.setBlockIncrement(1);
        Button addButton = new Button("Dodaj");
        addButton.setOnAction(event -> {
            String newItem = newItemTextField.getText().trim();
            int quantity = (int) quantitySlider.getValue();
            if (!newItem.isEmpty()) {
                ShoppingItem shoppingItem = new ShoppingItem(newItem, quantity);
                shoppingList.add(shoppingItem);
                newItemTextField.clear();
                quantitySlider.setValue(1);
            }
        });

        Button removeButton = new Button("Usuń zaznaczone");
        removeButton.setOnAction(event -> {
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                shoppingList.remove(selectedIndex);
            }
        });

        GridPane inputGridPane = new GridPane();
        inputGridPane.add(itemLabel, 0, 0);
        inputGridPane.add(newItemTextField, 1, 0);
        inputGridPane.add(quantityLabel, 0, 1);
        inputGridPane.add(quantitySlider, 1, 1);
        inputGridPane.add(addButton, 2, 0, 1, 2);
        inputGridPane.setHgap(10);
        inputGridPane.setVgap(5);
        inputGridPane.setPadding(new Insets(10));

        HBox buttonBox = new HBox(removeButton);
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setTop(inputGridPane);
        root.setCenter(listView);
        root.setBottom(buttonBox);

        Scene scene = new Scene(root, 300, 400);
        primaryStage.setTitle("Lista zakupów");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

