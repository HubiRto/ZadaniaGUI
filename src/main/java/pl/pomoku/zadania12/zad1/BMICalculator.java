package pl.pomoku.zadania12.zad1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BMICalculator extends Application {
    private Slider weightSlider;
    private Slider heightSlider;
    private Label bmiLabel;

    @Override
    public void start(Stage primaryStage) {
        Label weightLabel = new Label("Waga (kg):");
        weightSlider = new Slider(0, 200, 70);
        weightSlider.setShowTickLabels(true);
        weightSlider.setShowTickMarks(true);

        Label heightLabel = new Label("Wzrost (cm):");
        heightSlider = new Slider(0, 250, 170);
        heightSlider.setShowTickLabels(true);
        heightSlider.setShowTickMarks(true);

        Label resultLabel = new Label("BMI:");
        bmiLabel = new Label();

        bmiLabel.textProperty().bind(
                weightSlider.valueProperty()
                        .divide(heightSlider.valueProperty().divide(100).multiply(heightSlider.valueProperty().divide(100)))
                        .asString("%.2f"));

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(weightLabel, weightSlider, heightLabel, heightSlider, resultLabel, bmiLabel);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Kalkulator BMI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
