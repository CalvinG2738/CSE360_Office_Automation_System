//Phase2 Submission
//Tu42: Abe Troop, Shawn Neill, Calvin Gregory, Jordan Clifford, Helen Zhang

package OfficeSystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class doctorPatientSearch {
	public static final int WIDTH = 770, HEIGHT = 420;
	private boolean fileReadSuccessful;
	private String patientID;
	String patientName;
	
	public Scene doctorPatientSearchFunction(Stage homePageStage) {
		//**********STRUCTURE**********
		StackPane root = new StackPane();
		Scene newScene = new Scene(root, WIDTH, HEIGHT);
		BorderPane mainPane = new BorderPane();
		VBox centerPane = new VBox();
		HBox searchBarContainer = new HBox();
		VBox contactInformationContainer = new VBox();
		HBox rightButtonContainer = new HBox();
		BorderPane bottomButtonContainer = new BorderPane();
		
		//**********VARIABLES**********
		Label title = new Label("Office Automation System for Pediatric Doctor's Office");
		TextField searchBar = new TextField();
		Button searchButton = new Button("Search");
		Label contactInformationLabel = new Label("Patient Contact Information:");
		TextArea contactInformationTextArea = new TextArea();
		Button messageButton = new Button("Message");
		Button insurancePharmacyButton = new Button("Insurance and Pharmacy");
		Button mainMenu = new Button("Main Menu");
		Button continueButton = new Button("Continue");
		
		//**********STYLE**********
		//TITLE
		BorderPane.setAlignment(title, Pos.CENTER);
		title.setFont(Font.font ("", FontWeight.BOLD, 25));
		title.setPadding(new Insets(0,0,25,0));
		
		//RIGHT PANE
		centerPane.setPadding(new Insets(0,50,0,0));
		centerPane.setStyle("-fx-font-weight: BOLD; -fx-font-size: 15");
		centerPane.setSpacing(20);
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
		mainPane.setCenter(centerPane);
		mainPane.setBottom(bottomButtonContainer);
		
		//CENTER PANE
		centerPane.getChildren().addAll(searchBarContainer, contactInformationContainer, rightButtonContainer);
		
		//SEARCH BAR CONTAINER
		searchBarContainer.getChildren().addAll(searchBar, searchButton);
		
		//CONTACT INFORMATION CONTAINER
		contactInformationContainer.getChildren().addAll(contactInformationLabel, contactInformationTextArea);
		
		//RIGHT BUTTON CONTAINER
		rightButtonContainer.getChildren().addAll(messageButton, insurancePharmacyButton);
		
		//BOTTOM BUTTON CONTAINER
		bottomButtonContainer.setLeft(mainMenu);
		bottomButtonContainer.setRight(continueButton);
		
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
		
		doctorPage doctorPageScene = new doctorPage();
		continueButton.setOnAction(new EventHandler<>() {
			public void handle(ActionEvent event) {
				
				if(!fileReadSuccessful) {
					contactInformationTextArea.setText("Patient not found. Please enter a valid patient ID.");
				}
				else {
					homePageStage.setScene(doctorPageScene.doctorPageFunction(homePageStage, patientID));
				}
			}
		});
		
		//**********SET SCENE**********
		homePage homePageScene = new homePage();
		mainMenu.setOnAction(e -> homePageStage.setScene(homePageScene.homePageFunction(homePageStage)));
		
		//helens part
		DocNurseMessage messagingPortalScene = new DocNurseMessage();
		messageButton.setOnAction(e -> homePageStage.setScene(messagingPortalScene.DocNurseMessageFunction(homePageStage, patientID)));
		InsurancePharmacy insuranceAndPharmacyScene = new InsurancePharmacy();
		insurancePharmacyButton.setOnAction(e -> homePageStage.setScene(insuranceAndPharmacyScene.InsurancePharmacyFunction(homePageStage, patientID)));
		
		return newScene;
	}
	
	public void readFromFile(String patientID, TextArea textArea) {
		fileReadSuccessful = true;
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
}