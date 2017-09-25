package com.whesh.xlsorderbuilder;

import com.whesh.xlsorderbuilder.controller.OrderCopier;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main extends Application {

    private static Stage primaryStage;
    private TextField tfOrderFilePath;
    private TextField tfPriceFilePath;
    private Label labelLog;

    private File orderFile;
    private File priceFile;

    private final static List<String> extensions =
            new ArrayList<String>(Arrays.asList("*.xls", "*.xlsx"));
    private final static FileChooser fileChooser = new FileChooser();


    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        setPrimaryStage();

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void setPrimaryStage(){
        primaryStage.setTitle("Создание бланка заказа");
        primaryStage.setWidth(400);
        primaryStage.setHeight(600);

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel files", extensions));

        Pane root = new Pane();


        root.getChildren().addAll(initButtons());
        root.getChildren().addAll(initLabeles());
        root.getChildren().addAll(initTextFields());

        primaryStage.setScene(new Scene(root));
    }

    private Button createButton(String btnId, String btnText, double translateX, double translateY, double height, double width){

        Button button = new Button();
        button.setId(btnId);
        button.setText(btnText);
        button.setTranslateX(translateX);
        button.setTranslateY(translateY);
        button.setTranslateZ(4);
        button.setPrefSize(width, height);

        return button;
    }

    private List initButtons(){
        Button btnSetOrderList = createButton("btnSetOrderList", "...", 350, 25, 25, 25);
        Button btnSetPriceList = createButton("btnSetPriceList", "...", 350, 75, 25, 25);
        Button btnCreateOrder = createButton("btnCreateOrder", "Create Order", 150, 125, 50, 100);

        btnSetOrderList.setOnAction(event ->{
            try {
                orderFile = fileChooser.showOpenDialog(primaryStage);
                tfOrderFilePath.setText(orderFile.getCanonicalPath());
            } catch (IOException e) {
                System.out.println("Wrong file selected");
            } catch (NullPointerException e){
                System.out.println("File not selected");
            }
        });

        btnSetPriceList.setOnAction(event ->{
            try {
                priceFile = fileChooser.showOpenDialog(primaryStage);
                tfPriceFilePath.setText(priceFile.getCanonicalPath());
            } catch (IOException e) {
                System.out.println("Wrong file selected");
            } catch (NullPointerException e){
                System.out.println("File not selected");
            }
        });

        btnCreateOrder.setOnAction(event -> {
            OrderCopier orderCopier = new OrderCopier(orderFile, priceFile);
            labelLog.setText(orderCopier.getPriceOwner());

            StringBuilder builder = new StringBuilder();
            for (Map.Entry<String, Double> entry : orderCopier.getOrder().getOrderList().entrySet()){
                builder.append(entry.getKey() + ":" + entry.getValue() + "\n");
            }

            String orderOutput = new String(builder);
            labelLog.setText(labelLog.getText() + orderOutput);
        });

        List<Button> btnList = new ArrayList<Button>();
        btnList.add(btnSetOrderList);
        btnList.add(btnSetPriceList);
        btnList.add(btnCreateOrder);

        return btnList;
    }

    private Label createLable(String labelText, double translateX, double translateY, double height, double width){
        Label label = new Label();

        label.setText(labelText);
        label.setTranslateX(translateX);
        label.setTranslateY(translateY);
        label.setPrefSize(width, height);

        return label;
    }

    private List initLabeles(){
        List<Label> labels = new ArrayList<Label>();

        labelLog = new Label();
        labelLog.setTranslateX(25);
        labelLog.setTranslateY(185);
        labelLog.setPrefSize(350, 300);
        labelLog.setTextFill(Color.WHITE);
        labelLog.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        labelLog.setStyle("-fx-control-inner-background: #000000; -fx-text-fill: #FFFFFF;");
        labelLog.setWrapText(true);
        labelLog.setAlignment(Pos.TOP_LEFT);

        labels.add(createLable("Order Excel File", 15, 25, 25, 100));
        labels.add(createLable("Price Excel File", 15, 75, 25, 100));
        labels.add(labelLog);

        return labels;
    }

    private List initTextFields(){
        List<TextField> textFields = new ArrayList<TextField>();

        tfOrderFilePath = new TextField();
        tfOrderFilePath.setTranslateX(125);
        tfOrderFilePath.setTranslateY(25);
        tfOrderFilePath.setPrefSize(215, 25);

        tfPriceFilePath = new TextField();
        tfPriceFilePath.setTranslateX(125);
        tfPriceFilePath.setTranslateY(75);
        tfPriceFilePath.setPrefSize(215, 25);


        textFields.add(tfOrderFilePath);
        textFields.add(tfPriceFilePath);
//        textFields.add(labelLog);

        return textFields;
    }
}
