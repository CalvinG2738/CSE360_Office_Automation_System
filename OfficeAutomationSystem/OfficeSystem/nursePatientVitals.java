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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class nursePatientVitals {
	public static final int WIDTH = 770, HEIGHT = 420;
	private boolean fileReadSuccessful;
	private String patientID;
	private String weight;
	private String height;
	private String temperature;
	private String bloodPressure;
	private boolean over12 = false;
	String patientName;
	
	public Scene nursePatientVitalsFunction(Stage homePageStage) {
		System.out.println("nursePatientVitals");
		//**********STRUCTURE**********
		StackPane root = new StackPane();
		Scene newScene = new Scene(root, WIDTH, HEIGHT);
		BorderPane mainPane = new BorderPane();
		VBox leftPane = new VBox();
		VBox rightPane = new VBox();
		HBox searchBarContainer = new HBox();
		VBox contactInformationContainer = new VBox();
		HBox rightButtonContainer = new HBox();
		BorderPane bottomButtonContainer = new BorderPane();
		
		//**********VARIABLES**********
		Label title = new Label("Office Automation System for Pediatric Doctor's Office");
		Label weightLabel = new Label("Weight:");
		Label heightLabel = new Label("Height:");
		Label temperatureLabel = new Label("Body Temperature:");
		Label bloodPressureLabel = new Label("Blood Pressure:");
		TextField weightTextField = new TextField();
		TextField heightTextField = new TextField();
		TextField temperatureTextField = new TextField();
		TextField bloodPressureTextField = new TextField();
		CheckBox ageCheckBox = new CheckBox("Patient is 12 or older");
		TextField searchBar = new TextField();
		Button searchButton = new Button("Search");
		Label contactInformationLabel = new Label("Patient Contact Information:");
		TextArea contactInformationTextArea = new TextArea();
		Button messageButton = new Button("Message");
		Button insurancePharmacyButton = new Button("Insurance and Pharmacy");
		Button mainMenu = new Button("Main Menu");
		Button addInformation = new Button("Add Information");
		
		//**********STYLE**********
		//TITLE
		BorderPane.setAlignment(title, Pos.CENTER);
		title.setFont(Font.font ("", FontWeight.BOLD, 25));
		title.setPadding(new Insets(0,0,25,0));
		
		//LEFT PANE
		final double SPACING = 20;
		leftPane.setPadding(new Insets(0,0,0,50));
		leftPane.setStyle("-fx-font-weight: BOLD; -fx-font-size: 15");
		heightLabel.setPadding(new Insets(SPACING,0,0,0));
		temperatureLabel.setPadding(new Insets(SPACING,0,0,0));
		bloodPressureLabel.setPadding(new Insets(SPACING,0,0,0));
		ageCheckBox.setPadding(new Insets(SPACING,0,0,0));
		
		//RIGHT PANE
		rightPane.setPadding(new Insets(0,50,0,0));
		rightPane.setStyle("-fx-font-weight: BOLD; -fx-font-size: 15");
		rightPane.setSpacing(20);
		searchBar.setPrefWidth(375 - searchButton.getPrefWidth());
		contactInformationTextArea.setPrefSize(375, 300);
		contactInformationTextArea.setEditable(false);
		rightButtonContainer.setAlignment(Pos.CENTER);
		rightButtonContainer.setSpacing(10);
		
		//BOTTOM BUTTOM CONTAINER
		bottomButtonContainer.setPadding(new Insets(10,10,10,10));
		
		//**********LAYOUT**********
		root.getChildren().addAll(mainPane);
		
		//MAIN PANE
		mainPane.setTop(title);
		mainPane.setLeft(leftPane);
		mainPane.setRight(rightPane);
		mainPane.setBottom(bottomButtonContainer);
		
		//LEFT PANE
		leftPane.getChildren().addAll(weightLabel, weightTextField, heightLabel, heightTextField, temperatureLabel, 
				temperatureTextField, bloodPressureLabel, bloodPressureTextField, ageCheckBox);
		
		//RIGHT PANE
		rightPane.getChildren().addAll(searchBarContainer, contactInformationContainer, rightButtonContainer);
		
		//SEARCH BAR CONTAINER
		searchBarContainer.getChildren().addAll(searchBar, searchButton);
		
		//CONTACT INFORMATION CONTAINER
		contactInformationContainer.getChildren().addAll(contactInformationLabel, contactInformationTextArea);
		
		//RIGHT BUTTON CONTAINER
		rightButtonContainer.getChildren().addAll(messageButton, insurancePharmacyButton);
		
		//BOTTOM BUTTON CONTAINER
		bottomButtonContainer.setLeft(mainMenu);
		bottomButtonContainer.setRight(addInformation);
		
		//**********FUNCTIONALITY**********
		searchButton.setOnAction(new EventHandler<>() {
			public void handle(ActionEvent event) {
				readFromFile(searchBar.getText(), contactInformationTextArea);
				if(fileReadSuccessful) {
					patientID = searchBar.getText();
				} else {
					contactInformationTextArea.setText("Patient not found. Please enter a valid patient ID.");
				}
			}
		});
		
		nursePatientHistory nursePatientHistoryScene = new nursePatientHistory();
		addInformation.setOnAction(new EventHandler<>() {
			public void handle(ActionEvent event) {
				boolean emptyTextFields = false;
				weight = weightTextField.getText();
				height = heightTextField.getText();
				temperature = temperatureTextField.getText();
				bloodPressure = bloodPressureTextField.getText();
				
				if(over12) {
					if(weightTextField.getText().isEmpty() || heightTextField.getText().isEmpty() ||
							temperatureTextField.getText().isEmpty() || bloodPressureTextField.getText().isEmpty()) {
						contactInformationTextArea.setText("Please fill in all text fields.");
						emptyTextFields = true;
					}
				}
				
				if(!fileReadSuccessful) {
					contactInformationTextArea.setText("Patient not found. Please enter a valid patient ID.");
				}
				
				if(fileReadSuccessful && !emptyTextFields) {
					writeToFile();
					homePageStage.setScene(nursePatientHistoryScene.nursePatientHistoryFunction(homePageStage, patientID));
				}
			}
		});
		
		weightTextField.setEditable(false);
		heightTextField.setEditable(false);
		temperatureTextField.setEditable(false);
		bloodPressureTextField.setEditable(false);
		ageCheckBox.setOnAction(new EventHandler<>() {
			public void handle(ActionEvent event) {
				if(ageCheckBox.isSelected()) {
					weightTextField.setEditable(true);
					heightTextField.setEditable(true);
					temperatureTextField.setEditable(true);
					bloodPressureTextField.setEditable(true);
					over12 = true;
				} else {
					weightTextField.setEditable(false);
					heightTextField.setEditable(false);
					temperatureTextField.setEditable(false);
					bloodPressureTextField.setEditable(false);
					
					weightTextField.clear();
					heightTextField.clear();
					temperatureTextField.clear();
					bloodPressureTextField.clear();
					
					over12 = false;
				}
			}
		});
		
		//**********SET SCENE**********
		homePage homePageScene = new homePage();
		mainMenu.setOnAction(e -> homePageStage.setScene(homePageScene.homePageFunction(homePageStage)));
		
		DocNurseMessage messagingPortalScene = new DocNurseMessage();
        messageButton.setOnAction(e -> homePageStage.setScene(messagingPortalScene.DocNurseMessageFunction(homePageStage, patientID)));
        
        InsurancePharmacy insuranceAndPharmacyScene = new InsurancePharmacy();
        insurancePharmacyButton.setOnAction(e -> homePageStage.setScene(insuranceAndPharmacyScene.InsurancePharmacyFunction(homePageStage, patientID)));
		//TODO Helen's Messaging Portal and Insurance/Pharmacy scenes needed
        DocNurseMessage DocNurseMessageScene = new DocNurseMessage();
		messageButton.setOnAction(e -> homePageStage.setScene(DocNurseMessageScene.DocNurseMessageFunction(homePageStage, patientID)));
		InsurancePharmacy InsurancePharmacyScene = new InsurancePharmacy();
		insurancePharmacyButton.setOnAction(e -> homePageStage.setScene(InsurancePharmacyScene.InsurancePharmacyFunction(homePageStage, patientID)));
		
		return newScene;
	}
	
	public void readFromFile(String patientID, TextArea textArea) {
		fileReadSuccessful = true;
		//TODO Change filepath to OfficeSystem
		String filePath = "src/OfficeSystem/" + patientID + "_PatientInfo.txt";
		try {
			FileReader fileReader = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			bufferedReader.readLine();
			
			textArea.clear();
			String line;
			while((line = bufferedReader.readLine()) != null) {
				textArea.appendText(line + "\n");
			}
			
			bufferedReader.close();
		} catch(FileNotFoundException e) {
			fileReadSuccessful = false;
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeToFile() {
		//TODO Change filepath to OfficeSystem
		String filePath = "src/OfficeSystem/" + patientID + "_PatientInsuranceAndPharmacy.txt";
		
		try {
			FileWriter fileWriter = new FileWriter(filePath);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			if(over12) {
				bufferedWriter.append("Weight: " + weight + "\n");
				bufferedWriter.append("Height: " + height + "\n");
				bufferedWriter.append("Body Temperature: " + temperature + "\n");
				bufferedWriter.append("Blood Pressure: " + bloodPressure + "\n");
			} else {
				bufferedWriter.append("Under 12 years old.");
			}
			
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}