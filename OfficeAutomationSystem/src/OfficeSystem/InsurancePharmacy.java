//package OfficeSystem;
//
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//
////IMPORTS
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.StackPane;
//import javafx.scene.layout.VBox;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.HBox;
//import javafx.stage.Stage;
//
//
//public class InsurancePharmacy {
//	
//	public static final int WIDTH = 770, HEIGHT = 420;
//	public String updatedInfo = "";
//	public String fileInfo = "";
//	public String pharmacyInfo = "";
//	public String insuranceInfo = "";
//	
//	public Scene InsurancePharmacyFunction(Stage homePageStage, String patientName) {
//		System.out.println("patientUpdateContactInfo");
//		//**********STRUCTURE**********
//		StackPane root = new StackPane();
//		Scene newScene = new Scene(root, WIDTH, HEIGHT);
//		BorderPane border = new BorderPane();
//		BorderPane userType = new BorderPane();
//		BorderPane existingUserBorder = new BorderPane();
//		VBox currentContact = new VBox();
//		BorderPane updateContactBorder = new BorderPane();
//		VBox updateContact = new VBox();
//		VBox updateBorder = new VBox();
//		HBox bottomButton = new HBox();
//		
//		//**********VARIABLES**********
//		Label title = new Label("Office Automation System for Pediatric Doctor's Office");
//		Label currentContactLabel = new Label("Current Insurance and Pharmacy Information");
//		Label updateContactLabel = new Label("Update Insurance and Pharmacy Information");
//		Label insuranceLabel = new Label("Insurance:");
//		Label pharmacyLabel = new Label("Pharmacy:");
//		TextField updateInsurance = new TextField();
//		TextField updatePharmacy = new TextField();
//		Label insuranceCurrentLabel = new Label("Insurance:");
//		Label pharmacyLabelCurrent = new Label("Pharmacy:");
//		TextField updateinsuranceCurrentLabel = new TextField();
//		TextField updatePharmacyCurrent = new TextField();
//		Button updateButton = new Button("Update");
//		Button mainMenu = new Button("Main Menu");
//		Button back = new Button("Back");
//		
//		//**********STYLE**********
//		//TITLE
//		BorderPane.setAlignment(title, Pos.CENTER);
//		title.setFont(Font.font ("", FontWeight.BOLD, 25));
//		title.setPadding(new Insets(0,0,25,0));
//		//EXISTING USER
//		userType.setStyle("-fx-font-weight: BOLD; -fx-font-size: 15");
//		currentContact.setStyle("-fx-padding: 0 50 0 50");
//		currentContact.setMinWidth(WIDTH/2);
//		//existingUser.setAlignment(Pos.CENTER);
//		BorderPane.setAlignment(currentContactLabel, Pos.CENTER);
//		currentContactLabel.setFont(Font.font("", FontWeight.BOLD, 20));
//		insuranceLabel.setPadding(new Insets(15,0,0,0));
//		pharmacyLabel.setPadding(new Insets(15,0,0,0));
//		updateBorder.setPadding(new Insets(15,0,0,0));
//		updateBorder.setAlignment(Pos.CENTER);
//		//NEW USER
//		updateContact.setStyle("-fx-padding: 0 50 0 50");
//		updateContact.setMinWidth(WIDTH/2);
//		BorderPane.setAlignment(updateContactLabel, Pos.CENTER);
//		updateContactLabel.setFont(Font.font("", FontWeight.BOLD, 20));
//		insuranceCurrentLabel.setPadding(new Insets(15,0,0,0));
//		pharmacyLabelCurrent.setPadding(new Insets(15,0,0,0));
//		//BOTTOM BUTTOM
//		updateBorder.setPadding(new Insets(0,0,80,0));
//		updateButton.setPadding(new Insets(5,50,5,50));
//		bottomButton.setPadding(new Insets(0,0,10,10));
//		bottomButton.setStyle("-fx-font-weight: BOLD");
//		bottomButton.setSpacing(515);
//		
//		//**********LAYOUT**********
//		root.getChildren().add(border);
//		//TITLE
//		border.setTop(title);
//		//EXISTING USER
//		border.setCenter(userType);
//		userType.setLeft(existingUserBorder);
//		existingUserBorder.setTop(currentContactLabel);
//		existingUserBorder.setCenter(currentContact);
//		currentContact.getChildren().addAll(insuranceLabel, updateInsurance, pharmacyLabel, updatePharmacy);
//		//NEW USER
//		userType.setRight(updateContactBorder);
//		updateContactBorder.setTop(updateContactLabel);
//		updateContactBorder.setCenter(updateContact);
//		updateContact.getChildren().addAll(insuranceCurrentLabel, updateinsuranceCurrentLabel, pharmacyLabelCurrent, updatePharmacyCurrent);
//		//BOTTOM BUTTON
//		userType.setBottom(updateBorder);
//		updateBorder.getChildren().addAll(updateButton);
//		border.setBottom(bottomButton);
//		bottomButton.getChildren().addAll(mainMenu, back);
//	
//		//**********FUNCTIONALITY**********
//		
//		String fileName = "src/" + patientName + "_PatientInfo.txt";
//		try {
//			System.out.println("READ FILE");
//			fileInfo = ReadFileFunction(fileName);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		insuranceInfo = extractLine(fileInfo, 7);
//		updatePharmacy.setText(insuranceInfo.substring(5));
//		pharmacyInfo = extractLine(fileInfo, 8);
//		updateInsurance.setText(pharmacyInfo.substring(5));
//		fileInfo = extractLine(fileInfo, 1) +"\n"+ extractLine(fileInfo, 2)+"\n"+extractLine(fileInfo, 3)+"\n"+extractLine(fileInfo, 4)+"\n";
//		System.out.println("**********FILEINFO**********"+fileInfo);
//		
//		
//		updateButton.setOnAction(new EventHandler<>() {
//			public void handle(ActionEvent event) {
//				updatedInfo = fileInfo + "Insurance: "+updatePharmacyCurrent.getText()+"\n"
//									   + "Pharmacy: "+updateinsuranceCurrentLabel.getText();
//				try {
//					System.out.println("UPDATED FILE WITH: \n"+ updatedInfo);
//					WriteFileFunction(fileName, updatedInfo);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				updatePharmacy.setText(updatePharmacyCurrent.getText());
//				updateInsurance.setText(updateinsuranceCurrentLabel.getText());
//				updatePharmacyCurrent.setText("");
//				updateinsuranceCurrentLabel.setText("");
//			}
//		});
//		
//		updateInsurance.setEditable(false);
//		updatePharmacy.setEditable(false);
//			
//		//**********SET SCENE**********
//		homePage homePageScene = new homePage();
//		nursePatientVitals nursePageScene = new nursePatientVitals();
//		back.setOnAction(e -> homePageStage.setScene(nursePageScene.nursePatientVitalsFunction(homePageStage)));
//		mainMenu.setOnAction(e -> homePageStage.setScene(homePageScene.homePageFunction(homePageStage)));
//       
//		return newScene;
//	}
//	
//	
//	
//	public void WriteFileFunction(String name, String data) throws IOException {
//		FileWriter myWriter = new FileWriter(name);
//		myWriter.write(data);
//		myWriter.close();
//	}
//	
//	public static String ReadFileFunction(String name) throws IOException {
//		FileReader myReader = new FileReader(name);
//		String data = "";
//		int i;
//		while((i = myReader.read()) != -1) {
//			System.out.print((char)i);
//			data += (char)i;
//		}
//		myReader.close();
//		System.out.println("\n*****READFILE*****\n"+data+"\n**********");
//		return data;
//	}
//	
//	public static String extractLine(String input, int line) {
//		String[] arrOfstr = input.split("\n");
//		if(arrOfstr.length >= line) {
//			return arrOfstr[line - 1];
//		}
//		return "";
//	}
//}

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


public class InsurancePharmacy {
	
	public static final int WIDTH = 770, HEIGHT = 420;
	public String updatedInfo = "";
	public String fileInfo = "";
	public String insuranceInfo = "";
	public String pharmacyInfo = "";
	
	public Scene InsurancePharmacyFunction (Stage homePageStage, String patientName) {
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
		Label currentContactLabel = new Label("Current Insurance/Pharmacy Information");
		Label updateContactLabel = new Label("Update Insurance/Pharmacy Information");
		Label insuranceLabel = new Label("Insurance: ");
		Label pharmacyLabel = new Label("Pharmacy: ");
		TextField insuranceInput = new TextField();
		TextField pharmacyInput = new TextField();
		Label insuranceLabelCurrent = new Label("Insurance: ");
		Label pharmacyLabelCurrent = new Label("Pharmacy:");
		TextField insuranceInputCurrent = new TextField();
		TextField pharmacyInputCurrent = new TextField();
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
		currentContactLabel.setFont(Font.font("", FontWeight.BOLD, 15));
		insuranceLabel.setPadding(new Insets(15,0,0,0));
		pharmacyLabel.setPadding(new Insets(15,0,0,0));
		updateBorder.setPadding(new Insets(15,0,0,0));
		updateBorder.setAlignment(Pos.CENTER);
		//NEW USER
		updateContact.setStyle("-fx-padding: 0 50 0 50");
		updateContact.setMinWidth(WIDTH/2);
		BorderPane.setAlignment(updateContactLabel, Pos.CENTER);
		updateContactLabel.setFont(Font.font("", FontWeight.BOLD, 15));
		insuranceLabelCurrent.setPadding(new Insets(15,0,0,0));
		pharmacyLabelCurrent.setPadding(new Insets(15,0,0,0));
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
		currentContact.getChildren().addAll(insuranceLabel, insuranceInput, pharmacyLabel, pharmacyInput);
		//NEW USER
		userType.setRight(updateContactBorder);
		updateContactBorder.setTop(updateContactLabel);
		updateContactBorder.setCenter(updateContact);
		updateContact.getChildren().addAll(insuranceLabelCurrent, insuranceInputCurrent, pharmacyLabelCurrent, pharmacyInputCurrent);
		//BOTTOM BUTTON
		userType.setBottom(updateBorder);
		updateBorder.getChildren().addAll(updateButton);
		border.setBottom(bottomButton);
		bottomButton.getChildren().addAll(mainMenu, patientPortalButton);
	
		//**********FUNCTIONALITY**********
		
		String fileName = "src/OfficeSystem/" + patientName + "_PatientInfo.txt";
		try {
			System.out.println("READ FILE");
			fileInfo = ReadFileFunction(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pharmacyInfo = extractLine(fileInfo, 7);
		//pharmacyInput.setText(pharmacyInfo.substring(14));
		insuranceInfo = extractLine(fileInfo, 8);
		//insuranceInput.setText(insuranceInfo.substring(7));
		fileInfo = extractLine(fileInfo, 1) +"\n"+ extractLine(fileInfo, 2)+"\n"+extractLine(fileInfo, 3)+"\n"+extractLine(fileInfo, 4)+"\n" +
				extractLine(fileInfo, 5) + "\n" + extractLine(fileInfo, 6) + "\n";
		System.out.println("**********FILEINFO**********"+fileInfo);
		
		
		updateButton.setOnAction(new EventHandler<>() {
			public void handle(ActionEvent event) {
				updatedInfo = fileInfo + "Insurance: "+pharmacyInputCurrent.getText()+"\n"
									   + "Pharmacy: "+insuranceInputCurrent.getText();
				try {
					System.out.println("UPDATED FILE WITH: \n"+ updatedInfo);
					WriteFileFunction(fileName, updatedInfo);
				} catch (IOException e) {
					e.printStackTrace();
				}
				pharmacyInput.setText(pharmacyInputCurrent.getText());
				insuranceInput.setText(insuranceInputCurrent.getText());
				pharmacyInputCurrent.setText("");
				insuranceInputCurrent.setText("");
			}
		});
		
		insuranceInput.setEditable(false);
		pharmacyInput.setEditable(false);
			
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