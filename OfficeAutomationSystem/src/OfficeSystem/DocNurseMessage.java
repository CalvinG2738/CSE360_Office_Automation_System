//Helen Zhang
//ASU ID: 1224604511
//Prof. Carter, Tues 1:30-2:45
//import java.util.Stack;

package OfficeSystem;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
 
public class DocNurseMessage {
	public static final int WIDTH = 770, HEIGHT = 420;
	Label title, titleMessage, titleInbox, spacer;
	Button mainMenu, back, send, show;
	TextArea messageArea, inboxArea;
	TextField titleSend;
	StringBuilder warning;
	String patientName;
	
    public Scene DocNurseMessageFunction(Stage homePageStage, String patientName) {
        
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
        Label titleOfCombo = new Label("Message Title: ");
        TextField titleCombo = new TextField("");
        titleCombo.setEditable(false);
        //titleCombo.setEditable(true);
        //titleCombo.getItems().add("Choice 1");
        //show = new Button("Show");
        titleInboxSearchLine.setSpacing(10);
        titleInboxSearchLine.getChildren().addAll(titleOfCombo, titleCombo);
        rightInbox.getChildren().addAll(titleInbox, inboxArea, titleInboxSearchLine);
        hAreaMid.setSpacing(80);
        hAreaMid.getChildren().addAll(leftMessage, rightInbox);
        
        HBox hAreaBottom = new HBox();
        mainMenu = new Button("Main Menu");
        back = new Button("Back");
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
        
        
        File inboxCheck = new File("src/OfficeSystem/" + patientName +"_DocNurseMessage.txt");
        if(inboxCheck.exists()) {
        	try {
				String messageContent = ReadFileFunction("src/OfficeSystem/" + patientName +"_DocNurseMessage.txt");
				inboxArea.setText(messageContent);
				titleCombo.setText(extractLine(messageContent, 1));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        send.setOnAction(new EventHandler<>() {
        	public void handle(ActionEvent event) {
        		if (messageArea.getText().isEmpty() || titleSend.getText().isEmpty()) {
        		} else {
        			String messageFileName = "src/OfficeSystem/" + patientName +"_PatientMessage.txt";
                	String messageContent = titleSend.getText()+ "\n" + messageArea.getText();
                	File newPatientMessage = new File(messageFileName);
                	try {
    					WriteFileFunction(messageFileName, messageContent);
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
                	messageArea.setText("");
                	titleSend.setText("");
        		}
        	}	
        });  
        
        inboxArea.setEditable(false);
        
        nursePatientVitals nurseScene = new nursePatientVitals();
		back.setOnAction(e -> homePageStage.setScene(nurseScene.nursePatientVitalsFunction(homePageStage)));
		
		homePage homePageScene = new homePage();
		mainMenu.setOnAction(e -> homePageStage.setScene(homePageScene.homePageFunction(homePageStage)));
         
		return newScene;
    }
    
    public void WriteFileFunction(String name, String data) throws IOException {
		FileWriter myWriter = new FileWriter(name);
		myWriter.write(data);
		myWriter.close();
	}
	
	public static String ReadFileFunction(String name) throws IOException {
		FileReader myReader = new FileReader(name);
		String data = "";
		int i;
		while((i = myReader.read()) != -1) {
			System.out.print((char)i);
			data += (char)i;
		}
		myReader.close();
		System.out.println("\n*****READFILE*****\n"+data+"\n**********");
		return data;
	}
	
	public static String extractLine(String input, int line) {
		String[] arrOfstr = input.split("\n");
		if(arrOfstr.length >= line) {
			return arrOfstr[line - 1];
		}
		return "";
	}
}