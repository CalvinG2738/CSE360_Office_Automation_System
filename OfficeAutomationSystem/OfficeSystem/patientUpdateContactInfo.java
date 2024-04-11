package OfficeSystem;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//IMPORTS
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class patientUpdateContactInfo {
	
	public static final int WIDTH = 770, HEIGHT = 420;
	public String updatedInfo = "";
	public String fileInfo = "";
	public String endInfo = "";
	public String emailInfo = "";
	public String phoneInfo = "";
	
	public Scene patientUpdateContactInfoFunction (Stage homePageStage, String patientName) {
		System.out.println("patientUpdateContactInfo");
		//**********STRUCTURE**********
		StackPane root = new StackPane();
		Scene newScene = new Scene(root, WIDTH, HEIGHT);
		BorderPane border = new BorderPane();
		BorderPane userType = new BorderPane();
		BorderPane existingUserBorder = new BorderPane();
		VBox currentContact = new VBox();
		BorderPane updateContactBorder = new BorderPane();
		VBox updateContact = new VBox();
		VBox updateBorder = new VBox();
		HBox bottomButton = new HBox();
		
		//**********VARIABLES**********
		Label title = new Label("Office Automation System for Pediatric Doctor's Office");
		Label currentContactLabel = new Label("Current Contact Information");
		Label updateContactLabel = new Label("Update Contact Information");
		Label emailLabel = new Label("Email:");
		Label phoneLabel = new Label("Phone Number:");
		TextField emailInput = new TextField();
		TextField phoneInput = new TextField();
		Label emailLabelCurrent = new Label("Email:");
		Label phoneLabelCurrent = new Label("Phone Number:");
		TextField emailInputCurrent = new TextField();
		TextField phoneInputCurrent = new TextField();
		Button updateButton = new Button("Update");
		Button mainMenu = new Button("Main Menu");
		Button patientPortalButton = new Button("Return to Patient Portal");
		
		//**********STYLE**********
		//TITLE
		BorderPane.setAlignment(title, Pos.CENTER);
		title.setFont(Font.font ("", FontWeight.BOLD, 25));
		title.setPadding(new Insets(0,0,25,0));
		//EXISTING USER
		userType.setStyle("-fx-font-weight: BOLD; -fx-font-size: 15");
		currentContact.setStyle("-fx-padding: 0 50 0 50");
		currentContact.setMinWidth(WIDTH/2);
		//existingUser.setAlignment(Pos.CENTER);
		BorderPane.setAlignment(currentContactLabel, Pos.CENTER);
		currentContactLabel.setFont(Font.font("", FontWeight.BOLD, 20));
		emailLabel.setPadding(new Insets(15,0,0,0));
		phoneLabel.setPadding(new Insets(15,0,0,0));
		updateBorder.setPadding(new Insets(15,0,0,0));
		updateBorder.setAlignment(Pos.CENTER);
		//NEW USER
		updateContact.setStyle("-fx-padding: 0 50 0 50");
		updateContact.setMinWidth(WIDTH/2);
		BorderPane.setAlignment(updateContactLabel, Pos.CENTER);
		updateContactLabel.setFont(Font.font("", FontWeight.BOLD, 20));
		emailLabelCurrent.setPadding(new Insets(15,0,0,0));
		phoneLabelCurrent.setPadding(new Insets(15,0,0,0));
		//BOTTOM BUTTOM
		updateBorder.setPadding(new Insets(0,0,80,0));
		updateButton.setPadding(new Insets(5,50,5,50));
		bottomButton.setPadding(new Insets(0,0,10,10));
		bottomButton.setStyle("-fx-font-weight: BOLD");
		bottomButton.setSpacing(515);
		
		//**********LAYOUT**********
		root.getChildren().add(border);
		//TITLE
		border.setTop(title);
		//EXISTING USER
		border.setCenter(userType);
		userType.setLeft(existingUserBorder);
		existingUserBorder.setTop(currentContactLabel);
		existingUserBorder.setCenter(currentContact);
		currentContact.getChildren().addAll(emailLabel, emailInput, phoneLabel, phoneInput);
		//NEW USER
		userType.setRight(updateContactBorder);
		updateContactBorder.setTop(updateContactLabel);
		updateContactBorder.setCenter(updateContact);
		updateContact.getChildren().addAll(emailLabelCurrent, emailInputCurrent, phoneLabelCurrent, phoneInputCurrent);
		//BOTTOM BUTTON
		userType.setBottom(updateBorder);
		updateBorder.getChildren().addAll(updateButton);
		border.setBottom(bottomButton);
		bottomButton.getChildren().addAll(mainMenu, patientPortalButton);
	
		//**********FUNCTIONALITY**********
		
		String fileName = "src/OfficeSystem/" + patientName+"_PatientInfo.txt";
		try {
			System.out.println("READ FILE");
			fileInfo = ReadFileFunction(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		phoneInfo = extractLine(fileInfo, 5);
		phoneInput.setText(phoneInfo.substring(14));
		emailInfo = extractLine(fileInfo, 6);
		emailInput.setText(emailInfo.substring(7));
		endInfo = extractLine(fileInfo, 7) + "\n" + extractLine(fileInfo, 8) + "\n";
		fileInfo = extractLine(fileInfo, 1) +"\n"+ extractLine(fileInfo, 2)+"\n"+extractLine(fileInfo, 3)+"\n"+extractLine(fileInfo, 4)+"\n";
				
		System.out.println("**********FILEINFO**********"+fileInfo);
		
		
		updateButton.setOnAction(new EventHandler<>() {
			public void handle(ActionEvent event) {
				updatedInfo = fileInfo + "Phone Number: "+phoneInputCurrent.getText()+"\n"
									   + "Email: "+emailInputCurrent.getText() + "\n"
									   + endInfo;
				try {
					System.out.println("UPDATED FILE WITH: \n"+ updatedInfo);
					WriteFileFunction(fileName, updatedInfo);
				} catch (IOException e) {
					e.printStackTrace();
				}
				phoneInput.setText(phoneInputCurrent.getText());
				emailInput.setText(emailInputCurrent.getText());
				phoneInputCurrent.setText("");
				emailInputCurrent.setText("");
			}
		});
		
		emailInput.setEditable(false);
		phoneInput.setEditable(false);
			
		//**********SET SCENE**********
		homePage homePageScene = new homePage();
		mainMenu.setOnAction(e -> homePageStage.setScene(homePageScene.homePageFunction(homePageStage)));
		
		patientPortal patientPortalScene = new patientPortal();
		patientPortalButton.setOnAction(e -> homePageStage.setScene(patientPortalScene.patientPortalFunction(homePageStage, patientName)));
		
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