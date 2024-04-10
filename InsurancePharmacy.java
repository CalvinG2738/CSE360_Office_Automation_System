package OfficeSystem;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
 
public class InsurancePharmacy {
	Label title, currentInfoTitle, insuranceLeft, pharmacyLeft, updateInfoTitle, insuranceRight, pharmacyRight, spacer;
	Button update, mainMenu, back;
	TextField updateInsurance, updatePharmacy;
	StringBuilder warning;
	public static final int WIDTH = 770, HEIGHT = 420;
    
    public Scene InsurancePharmacyFunction(Stage homePageStage) {
        title = new Label("Office Automation System for Pediatric Doctor's Office");
        title.setFont(Font.font("", FontWeight.BOLD, 25));
        
        HBox midPane = new HBox();
        VBox patientInfo = new VBox();
        currentInfoTitle = new Label("Current Insurance and Pharmacy Information");
        insuranceLeft = new Label("Insurance: ");
        pharmacyLeft = new Label("Pharmacy: ");
        patientInfo.setSpacing(10);
        patientInfo.getChildren().addAll(currentInfoTitle, insuranceLeft, pharmacyLeft);
        VBox updateInfo = new VBox();
        updateInfoTitle = new Label("Update Information: ");
        insuranceRight = new Label("Insurance: ");
        pharmacyRight = new Label("Pharmacy: ");
        updateInsurance = new TextField();
        updatePharmacy = new TextField();
        spacer = new Label();
        updateInfo.setSpacing(10);
        updateInfo.getChildren().addAll(updateInfoTitle, insuranceRight, updateInsurance, pharmacyRight, updatePharmacy, spacer);
        midPane.setAlignment(Pos.CENTER);
        midPane.setSpacing(100);
        midPane.getChildren().addAll(patientInfo, updateInfo);
        
        update = new Button("Update");
        
        HBox bottomPane = new HBox();
        mainMenu = new Button("Main Menu");
        back = new Button("Back");
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setSpacing(600);
        bottomPane.getChildren().addAll(mainMenu, back);
        
        //put it on the VBox
        VBox root = new VBox();
        Scene newScene = new Scene(root, WIDTH, HEIGHT);
        root.getChildren().addAll(title, midPane, update, bottomPane);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20,20,20,20));
        root.setSpacing(50);
        
        update.setOnAction(new EventHandler<>() {
        	public void handle(ActionEvent event) {
        		updateInsurance.clear();
        		updatePharmacy.clear();

                warning = new StringBuilder();
        		updateInsurance.getText();
        		updatePharmacy.getText();
        		
        		if (updateInsurance.getText() == null || updatePharmacy.getText() == null) {
        			warning.append("Please make sure both fields are filled out!");
        			spacer.setText(warning.toString());
        		}
        	}
        });
        
        homePage homePageScene = new homePage();
        nursePatientVitals nursePageScene = new nursePatientVitals();
		back.setOnAction(e -> homePageStage.setScene(nursePageScene.nursePatientVitalsFunction(homePageStage)));
		mainMenu.setOnAction(e -> homePageStage.setScene(homePageScene.homePageFunction(homePageStage)));
        
        return newScene;
    }
}