package com.whesh.xlsorderbuilder;

import com.whesh.xlsorderbuilder.controller.OrderCopier;
import com.whesh.xlsorderbuilder.model.PriceUnknown;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Application {

    private static Stage primaryStage;
    private CheckBox cbUserSettings;
    private TextField tfOrderFilePath;
    private TextField tfPriceFilePath;
    private TextField tfArticulCell;
    private TextField tfColsCell;
    private Text textLog;
//    private Label labelLog;

    private File orderFile;
    private File priceFile;

    private final static List<String> extensions =
            new ArrayList<String>(Arrays.asList("*.xls"));
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

        cbUserSettings = new CheckBox();
        cbUserSettings.setTranslateX(25);
        cbUserSettings.setTranslateY(125);
        cbUserSettings.setPrefSize(250, 25);
        cbUserSettings.setSelected(false);
        cbUserSettings.setText("Пользовательские настройки");

        cbUserSettings.selectedProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue){
                tfArticulCell.setEditable(true);
                tfArticulCell.setDisable(false);
                tfColsCell.setEditable(true);
                tfColsCell.setDisable(false);
            } else if (!newValue){
                tfArticulCell.setEditable(false);
                tfArticulCell.setDisable(true);
                tfColsCell.setEditable(false);
                tfColsCell.setDisable(true);
            }

        });

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setTranslateX(25);
        scrollPane.setTranslateY(300);
        scrollPane.setPrefSize(350, 300);

        textLog = new Text();
        textLog.setTranslateX(5);
        textLog.setTranslateY(5);
        textLog.setStyle("-fx-text-origin: top");
        textLog.setText("Выберите прайс-листы для работы и нажимите \"Создать заказ\"");
        textLog.setFill(Color.RED);
        textLog.wrappingWidthProperty().bind(scrollPane.heightProperty());

        scrollPane.setContent(textLog);


//        labelLog = new Label();
//        labelLog.setTranslateX(25);
//        labelLog.setTranslateY(185);
//        labelLog.setPrefSize(350, 300);
//        labelLog.setTextFill(Color.WHITE);
//        labelLog.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
//        labelLog.setStyle("-fx-control-inner-background: #000000; -fx-text-fill: #FFFFFF;");
//        labelLog.setWrapText(true);
//        labelLog.setAlignment(Pos.TOP_LEFT);


        root.getChildren().add(scrollPane);
        root.getChildren().add(cbUserSettings);
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
        Button btnCreateOrder = createButton("btnCreateOrder", "Создать заказ", 150, 225, 50, 100);

        btnSetOrderList.setOnAction(event ->{
            try {
                orderFile = fileChooser.showOpenDialog(primaryStage);
                tfOrderFilePath.setText(orderFile.getCanonicalPath());
            } catch (IOException e) {
                System.out.println("Выбран не верный формат файла");
            } catch (NullPointerException e){
                System.out.println("Файл не выбран");
            }
        });

        btnSetPriceList.setOnAction(event ->{
            try {
                priceFile = fileChooser.showOpenDialog(primaryStage);
                tfPriceFilePath.setText(priceFile.getCanonicalPath());
            } catch (IOException e) {
                System.out.println("Выбран не верный формат файла");
            } catch (NullPointerException e){
                System.out.println("Файл не выбран");
            }
        });

        btnCreateOrder.setOnAction(event -> {

            OrderCopier orderCopier;

            if (cbUserSettings.isSelected()){

                orderCopier =
                        new OrderCopier(orderFile,
                                        priceFile,
                                        new PriceUnknown("",
                                                        Integer.valueOf(tfArticulCell.getText()) - 1,
                                                        Integer.valueOf(tfColsCell.getText()) - 1));
            } else {

                orderCopier = new OrderCopier(orderFile, priceFile);
            }

            String orderOutput = orderCopier.commit();
            textLog.setText(orderOutput);
//            labelLog.setText(labelLog.getText() + orderOutput);
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

        labels.add(createLable("Файл с заказом", 15, 25, 25, 100));
        labels.add(createLable("Прайс поставщика", 15, 75, 25, 100));
        labels.add(createLable("Столбец артикула", 15, 175, 25, 100));
        labels.add(createLable("Столбец кол-ва", 200, 175, 25, 100));
//        labels.add(labelLog);

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

        tfArticulCell = new TextField();
        tfArticulCell.setTranslateX(130);
        tfArticulCell.setTranslateY(175);
        tfArticulCell.setPrefSize(50, 25);
        tfArticulCell.setEditable(false);
        tfArticulCell.setDisable(true);

        tfColsCell = new TextField();
        tfColsCell.setTranslateX(310);
        tfColsCell.setTranslateY(175);
        tfColsCell.setPrefSize(50, 25);
        tfColsCell.setEditable(false);
        tfColsCell.setDisable(true);

        textFields.add(tfOrderFilePath);
        textFields.add(tfPriceFilePath);
        textFields.add(tfArticulCell);
        textFields.add(tfColsCell);
//        textFields.add(labelLog);

        return textFields;
    }
}
