//Phase2 Submission
//Tu42: Abe Troop, Shawn Neill, Calvin Gregory, Jordan Clifford, Helen Zhang

//TODO CHANGE BACK TO OfficeSystem
package OfficeSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class doctorPage {
	public static final int WIDTH = 770, HEIGHT = 420;
	private String patientID;
	private String currentHealthIssue;
	private String currentPerscriptions;
	private String currentImmunizations;
	
	public Scene doctorPageFunction(Stage homePageStage, String patientID) {
		this.patientID = patientID;
		//**********STRUCTURE**********
		StackPane root = new StackPane();
		Scene newScene = new Scene(root, WIDTH, HEIGHT);
		BorderPane mainPane = new BorderPane();
		VBox leftPane = new VBox();
		VBox rightPane = new VBox();
		BorderPane bottomButtonContainer = new BorderPane();
		
		
		//**********VARIABLES**********
		Label title = new Label("Office Automation System for Pediatric Doctor's Office");
		Label patientHistoryTitle = new Label("Patient History:");
		Label healthIssuesLabel = new Label("Health Issues:");
		Label medicationsLabel = new Label("Previously Prescribed Medications:");
		Label immunizationsLabel = new Label("History of Immunizations:");
		TextArea healthIssuesTextArea = new TextArea();
		TextArea medicationsTextArea = new TextArea();
		TextArea immunizationsTextArea = new TextArea();
		Label updateHealthIssuesTitle = new Label("Update Health Issues:");
		TextField updateHealthIssuesTextField = new TextField();
		Button submitButton1 = new Button("Submit");
		Label perscribeMedicineTitle = new Label("Perscribe Medication:");
		TextField perscribeMedicineTextField = new TextField();
		Button submitButton2 = new Button("Submit");
		Label addImmunizationTitle = new Label("Add Immunization:");
		TextField addImmunizationTextField = new TextField();
		Button submitButton3 = new Button("Submit");
		Button mainMenu = new Button("Main Menu");
		Button newPatient = new Button("New Patient");
		
		//**********STYLE**********
		//TITLE
		BorderPane.setAlignment(title, Pos.CENTER);
		title.setFont(Font.font ("", FontWeight.BOLD, 25));
		title.setPadding(new Insets(0,0,10,0));
		
		//LEFT PANE
		final double SPACING = 10;
		leftPane.setPadding(new Insets(0,0,0,50));
		leftPane.setStyle("-fx-font-weight: BOLD; -fx-font-size: 15");
		patientHistoryTitle.setStyle("-fx-font-weight: BOLD; -fx-font-size: 20");
		healthIssuesLabel.setPadding(new Insets(SPACING,0,0,0));
		medicationsLabel.setPadding(new Insets(SPACING,0,0,0));
		immunizationsLabel.setPadding(new Insets(SPACING,0,0,0));
		healthIssuesTextArea.setMaxWidth(WIDTH/2 - 100);
		healthIssuesTextArea.setEditable(false);
		medicationsTextArea.setMaxWidth(WIDTH/2 - 100);
		medicationsTextArea.setEditable(false);
		immunizationsTextArea.setMaxWidth(WIDTH/2 - 100);
		immunizationsTextArea.setEditable(false);
		
		//RIGHT PANE
		rightPane.setPadding(new Insets(0,50,0,0));
		rightPane.setStyle("-fx-font-weight: BOLD; -fx-font-size: 15");
		rightPane.setSpacing(5);
		rightPane.setAlignment(Pos.CENTER);
		updateHealthIssuesTextField.setMinWidth(300);
		
		//BOTTOM BUTTOM CONTAINER
		bottomButtonContainer.setPadding(new Insets(10,10,10,10));
		
		//**********INITIALIZE**********
		readFromFile(healthIssuesLabel, healthIssuesTextArea);
		readFromFile(medicationsLabel, medicationsTextArea);
		readFromFile(immunizationsLabel, immunizationsTextArea);
		
		//**********LAYOUT**********
		root.getChildren().addAll(mainPane);
		
		//MAIN PANE
		mainPane.setTop(title);
		mainPane.setLeft(leftPane);
		mainPane.setRight(rightPane);
		mainPane.setBottom(bottomButtonContainer);
		
		//LEFT PANE
		leftPane.getChildren().addAll(patientHistoryTitle, healthIssuesLabel, healthIssuesTextArea, 
				medicationsLabel, medicationsTextArea, immunizationsLabel, immunizationsTextArea);
		
		//RIGHT PANE
		rightPane.getChildren().addAll(updateHealthIssuesTitle, updateHealthIssuesTextField, submitButton1, perscribeMedicineTitle, perscribeMedicineTextField, submitButton2, addImmunizationTitle, addImmunizationTextField, submitButton3);
		
		//BOTTOM BUTTON CONTAINER
		bottomButtonContainer.setLeft(mainMenu);
		bottomButtonContainer.setRight(newPatient);
		
		//**********FUNCTIONALITY**********
		submitButton1.setOnAction(new EventHandler<>() {
			public void handle(ActionEvent event) {
				currentHealthIssue = updateHealthIssuesTextField.getText();
				updateHealthIssuesTextField.clear();
				writeToFile(healthIssuesLabel);
				readFromFile(healthIssuesLabel, healthIssuesTextArea);
			}
		});
		
		submitButton2.setOnAction(new EventHandler<>() {
			public void handle(ActionEvent event) {
				currentPerscriptions = perscribeMedicineTextField.getText();
				perscribeMedicineTextField.clear();
				writeToFile(medicationsLabel);
				readFromFile(medicationsLabel, medicationsTextArea);
			}
		});
		
		submitButton3.setOnAction(new EventHandler<>() {
			public void handle(ActionEvent event) {
				currentImmunizations = addImmunizationTextField.getText();
				addImmunizationTextField.clear();
				writeToFile(immunizationsLabel);
				readFromFile(immunizationsLabel, immunizationsTextArea);
			}
		});
			
		//**********SET SCENE**********
		homePage homePageScene = new homePage();
		mainMenu.setOnAction(e -> homePageStage.setScene(homePageScene.homePageFunction(homePageStage)));
		doctorPatientSearch doctorPatientSearchScene = new doctorPatientSearch();
		newPatient.setOnAction(e -> homePageStage.setScene(doctorPatientSearchScene.doctorPatientSearchFunction(homePageStage)));
		
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
	
	public void writeToFile(Label label){
		String filePath = "src/OfficeSystem/" + patientID;
		String name = "";
		if(label.getText().equals("Health Issues:")) {
			filePath = filePath + "_PatientHealthIssues.txt";
			name = currentHealthIssue;
		}
		if(label.getText().equals("Previously Prescribed Medications:")) {
			filePath = filePath + "_PatientMedications.txt";
			name = currentPerscriptions;
		}
		if(label.getText().equals("History of Immunizations:")) {
			filePath = filePath + "_PatientImmunizations.txt";
			name = currentImmunizations;
		}
		
		try {
			FileWriter fileWriter = new FileWriter(filePath, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			bufferedWriter.append(name + "\n");
			
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}