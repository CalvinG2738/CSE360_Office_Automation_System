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
 
public class patientMessagingPortal {
	public static final int WIDTH = 770, HEIGHT = 420;
	Label title, titleMessage, titleInbox, spacer;
	Button mainMenu, back, send, show;
	TextArea messageArea, inboxArea;
	TextField titleSend;
	StringBuilder warning;
	
    public Scene patientMessagingPortalFunction(Stage homePageStage, String patientName) {
        
        HBox hAreaMid = new HBox();
        
        VBox leftMessage = new VBox();
        titleMessage = new Label("Enter Message: ");
        titleMessage.setStyle("-fx-font-weight: BOLD; -fx-font-size: 15");
        messageArea = new TextArea();
        messageArea.setPrefHeight(120);
        messageArea.setPrefWidth(300);
        messageArea.setPrefColumnCount(15);
        leftMessage.setSpacing(20);
        HBox titleSearchLine = new HBox();
        titleSend = new TextField();
        titleSend.setPromptText("Title");
        send = new Button("Send");
        titleSearchLine.setSpacing(10);
        titleSearchLine.getChildren().addAll(titleSend, send);
        leftMessage.getChildren().addAll(titleMessage, messageArea, titleSearchLine);
        
        VBox rightInbox = new VBox();
        titleInbox = new Label("Inbox: ");
        titleInbox.setStyle("-fx-font-weight: BOLD; -fx-font-size: 15");
        inboxArea = new TextArea();
        inboxArea.setText("Check your inbox here");
        inboxArea.setPrefHeight(120);
        inboxArea.setPrefWidth(300);
        inboxArea.setPrefColumnCount(15);
        rightInbox.setSpacing(20);
        HBox titleInboxSearchLine = new HBox();
        ComboBox titleCombo = new ComboBox();
        titleCombo.setEditable(true);
        titleCombo.getItems().add("Choice 1");
        show = new Button("Show");
        titleInboxSearchLine.setSpacing(10);
        titleInboxSearchLine.getChildren().addAll(titleCombo, show);
        rightInbox.getChildren().addAll(titleInbox, inboxArea, titleInboxSearchLine);
        hAreaMid.setSpacing(80);
        hAreaMid.getChildren().addAll(leftMessage, rightInbox);
        
        HBox hAreaBottom = new HBox();
        mainMenu = new Button("Main Menu");
        back = new Button("Return to Patient Portal");
        hAreaBottom.setSpacing(500);
        hAreaBottom.getChildren().addAll(mainMenu, back);
         
        title = new Label("Office Automation System for Pediatric's Doctor's Office");
        title.setFont(Font.font("", FontWeight.BOLD, 25));
        
        VBox root = new VBox();
		Scene newScene = new Scene(root, WIDTH, HEIGHT);
        root.getChildren().addAll(title, hAreaMid, hAreaBottom);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20,20,20,20));
        root.setSpacing(50);
        
        
        send.setOnAction(new EventHandler<>() {
        	public void handle(ActionEvent event) {
        		warning = new StringBuilder();
        		messageArea.getText();
        		titleSend.getText();
        		messageArea.clear();
        		titleSend.clear();

            	warning = warning.delete(warning.toString().indexOf(0), warning.toString().length());
        		       		
        		if (messageArea.getText().isEmpty() || titleSend.getText().isEmpty()) {
        			warning.append("Please make sure text area and title are filled out!");
        			spacer.setText(warning.toString());
        			messageArea.clear();
            		titleSend.clear();
        		}
        		messageArea.clear();
            	titleSend.clear();
            	warning = warning.delete(warning.toString().indexOf(0), warning.toString().length());
            	warning.append(warning.toString());
            		
            	
        	}	
        });  
        
        inboxArea.setEditable(false);
        
        patientPortal patientPortalScene = new patientPortal();
		back.setOnAction(e -> homePageStage.setScene(patientPortalScene.patientPortalFunction(homePageStage, patientName)));
		
		homePage homePageScene = new homePage();
		mainMenu.setOnAction(e -> homePageStage.setScene(homePageScene.homePageFunction(homePageStage)));
         
		return newScene;
    }
}