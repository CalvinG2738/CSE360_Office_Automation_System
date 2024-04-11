package OfficeSystem;


import java.io.BufferedReader;
//IMPORTS
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class patientPortal {
	
	/*public Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	public int width = (int)size.getWidth();
	public int height = (int)size.getHeight();
	USE IF WE WANT TO UTILIZE THE WHOLE SCREEN SIZE*/
	
	//PUBLIC VARIABLES
	public static final int WIDTH = 770, HEIGHT = 420;
	private String patientID;
	private String currentHealthIssue;
	private String currentPerscriptions;
	private String currentImmunizations;
	private String currentPhoneNum;
	private String currentEmail;
	
	public Scene patientPortalFunction(Stage homePageStage, String patientID) {
		System.out.println("patientPortal");
		this.patientID = patientID;
		//**********STRUCTURE**********
		StackPane root = new StackPane();
		Scene newScene = new Scene(root, WIDTH, HEIGHT);
		BorderPane border = new BorderPane();
				
		//**********VARIABLES**********
		Label title = new Label("Office Automation System for Pediatric Doctor's Office");
		
		Label patientHistory = new Label("Patient History:");
		Label healthIssuesLabel = new Label("Health Issues:");
		Label prevMedsLabel = new Label("Previously Prescribed Medications:");
		Label histImmunizationsLabel = new Label("History of Immunizations:");
		Label contInfo = new Label("Contact Information:");
		Label email = new Label("Email:");
		Label phoneNum = new Label("Phone Number:");
		TextField emailField = new TextField("");
		emailField.setEditable(false);
		TextField phoneNumField = new TextField("");
		phoneNumField.setEditable(false);
		//**AREAS ALL RELIENT ON DOC/NURSE INPUTS***
		TextArea healthIssuesArea = new TextArea("");
		healthIssuesArea.setMaxWidth(WIDTH/2 - 100);
		healthIssuesArea.setEditable(false);
		TextArea prevMedsArea = new TextArea("");
		prevMedsArea.setMaxWidth(WIDTH/2 - 100);
		prevMedsArea.setEditable(false);
		TextArea histImmunizationsArea = new TextArea("");
		histImmunizationsArea.setMaxWidth(WIDTH/2 - 100);
		histImmunizationsArea.setEditable(false);
		
		//MenuButton date = new MenuButton("Date");
		Button show = new Button("Show");
		Button updateContInfo = new Button("Update Contact Information");
		Button messaging = new Button("Messaging");
		Button mainMenu = new Button("Main Menu");
		
		//**********STYLE**********
		//TITLE
		BorderPane.setAlignment(title, Pos.CENTER);
		title.setFont(Font.font ("", FontWeight.BOLD, 25));
		title.setPadding(new Insets(0,0,25,0));
		//MAINMENU N MESSAGING
		
		
		//**********LAYOUT**********
		HBox patHist = new HBox(10);
		patHist.getChildren().addAll(patientHistory, show);
		VBox labelsAndAreas = new VBox(10);
		//labelsAndAreas.setStyle("-fx-font-weight: BOLD; -fx-font-size: 15");
		labelsAndAreas.getChildren().addAll(healthIssuesLabel, healthIssuesArea, prevMedsLabel, prevMedsArea, histImmunizationsLabel, histImmunizationsArea);
		
		VBox contactInfoLayout = new VBox(10);
		contactInfoLayout.setStyle("-fx-font-weight: BOLD; -fx-font-size: 15");
		contactInfoLayout.getChildren().addAll(contInfo, email, emailField, phoneNum, phoneNumField, updateContInfo);
		
		VBox leftLayout = new VBox(2);
		leftLayout.setStyle("-fx-font-weight: BOLD; -fx-font-size: 12");
		leftLayout.getChildren().addAll(patHist, labelsAndAreas);
		
		HBox buttons = new HBox(630);
		buttons.getChildren().addAll(mainMenu, messaging);
		buttons.setStyle("-fx-font-weight: BOLD; -fx-font-size: 9");
		BorderPane.setAlignment(buttons, Pos.BOTTOM_CENTER);
		
		border.setTop(title);
		border.setLeft(leftLayout);
		border.setRight(contactInfoLayout);
		border.setBottom(buttons);
		root.getChildren().add(border);
		//**********SHOW BUTTON***********
		show.setOnAction(new EventHandler<>() {
			public void handle(ActionEvent event) {
				
				readFromFile(healthIssuesLabel, healthIssuesArea);
				readFromFile(prevMedsLabel, prevMedsArea);
				readFromFile(histImmunizationsLabel, histImmunizationsArea);
				show.setDisable(true);
			}
		});
		//************SET CONTACT INFORMATION**************
		loadPatientContInfo(patientID, emailField, phoneNumField);
		
		//**********SET SCENE**********
		homePage homePageScene = new homePage();
		mainMenu.setOnAction(e -> homePageStage.setScene(homePageScene.homePageFunction(homePageStage)));
		
		patientUpdateContactInfo patientUpdateContactInfoScene = new patientUpdateContactInfo();
		updateContInfo.setOnAction(e -> homePageStage.setScene(patientUpdateContactInfoScene.patientUpdateContactInfoFunction(homePageStage, patientID)));
		
		patientMessagingPortal patientMessagingPortalScene = new patientMessagingPortal();
		messaging.setOnAction(e -> homePageStage.setScene(patientMessagingPortalScene.patientMessagingPortalFunction(homePageStage, patientID)));
		
		
		return newScene;
	}
	
	public void readFromFile(Label label, TextArea textArea){
		String filePath = "src/OfficeSystem/" + patientID;
		if(label.getText().equals("Health Issues:")) {
			filePath = filePath + "_PatientHealthIssues.txt";
		}
		if(label.getText().equals("Previously Prescribed Medications:")) {
			filePath = filePath + "_PatientMedications.txt";
		}
		if(label.getText().equals("History of Immunizations:")) {
			filePath = filePath + "_PatientImmunizations.txt";
		}
		
		try {
			FileReader fileReader = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			textArea.clear();
			String line;
			while((line = bufferedReader.readLine()) != null) {
				textArea.appendText(line + "\n");
			}
			
			bufferedReader.close();
		} catch(FileNotFoundException e) {
			textArea.setText("File not found");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	// Loads patient info from each file into text fields
		public void loadPatientContInfo(String confirmedID, TextField emailField, TextField phoneNumField) {
			String patientInfoFile = "src/OfficeSystem/" + confirmedID + "_PatientInfo.txt";
	
			try {
			// Load patient info
			try (BufferedReader patientInfoReader = new BufferedReader(new FileReader(patientInfoFile))) {
				String line;
				while ((line = patientInfoReader.readLine()) != null) {
			// Extract first name
			if (line.startsWith("Email:")) {
			    String emailFile = line.substring(line.indexOf(":") + 1).trim();
			    emailField.setText((String.valueOf(emailFile)));
		
				}
			else if (line.startsWith("Phone Number:")) {
				 String phoneNumFile = line.substring(line.indexOf(":") + 1).trim();
				 phoneNumField.setText((String.valueOf(phoneNumFile)));
				
				}
				}
			}
			} catch (IOException e) {
				e.printStackTrace();
				// Handles any IO exceptions
			}
		}
	
	 
	 

}