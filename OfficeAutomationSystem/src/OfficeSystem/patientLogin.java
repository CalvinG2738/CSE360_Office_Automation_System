package OfficeSystem;

import java.io.File;

//IMPORTS
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
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class patientLogin{
	/*public Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	public int width = (int)size.getWidth();
	public int height = (int)size.getHeight();
	USE IF WE WANT TO UTILIZE THE WHOLE SCREEN SIZE*/
	
	//PUBLIC VARIABLES
	public static final int WIDTH = 770, HEIGHT = 420;
	public final int maxLength = 32;
	public final int birthdayLength = 10;
	
	public Scene patientLoginFunction(Stage homePageStage) {
		System.out.println("patientLogin");
		//**********STRUCTURE**********
		StackPane root = new StackPane();
		Scene newScene = new Scene(root, WIDTH, HEIGHT);
		BorderPane border = new BorderPane();
		HBox userType = new HBox();
		BorderPane existingUserBorder = new BorderPane();
		VBox existingUser = new VBox();
		BorderPane newUserBorder = new BorderPane();
		VBox newUser = new VBox();
		VBox signInBorder = new VBox();
		HBox bottomButton = new HBox();
		
		//**********VARIABLES**********
		Label title = new Label("Office Automation System for Pediatric Doctor's Office");
		Label existingUserLabel = new Label("Existing User");
		Label newUserLabel = new Label("New User");
		Label firstNameLabel = new Label("First Name:");
		Label lastNameLabel = new Label("Last Name:");
		Label birthdayLabel = new Label("Birthdate:");
		TextField firstNameInput = new TextField();
		TextField lastNameInput = new TextField();
		TextField birthdayInput = new TextField();
		birthdayInput.setPromptText("mm/dd/yyyy");
		Button signIn = new Button("Sign In");
		Button createAccount = new Button("Create Account");
		Button mainMenu = new Button("Main Menu");
		
		//**********STYLE**********
		//TITLE
		BorderPane.setAlignment(title, Pos.CENTER);
		title.setFont(Font.font ("", FontWeight.BOLD, 25));
		title.setPadding(new Insets(0,0,25,0));
		//EXISTING USER
		userType.setStyle("-fx-font-weight: BOLD; -fx-font-size: 15");
		existingUser.setStyle("-fx-border-style: solid; -fx-border-width:0 1 0 0; -fx-border-color: lightgrey; -fx-padding: 0 50 0 50");
		existingUser.setMinWidth(WIDTH/2);
		//existingUser.setAlignment(Pos.CENTER);
		BorderPane.setAlignment(existingUserLabel, Pos.CENTER);
		existingUserLabel.setFont(Font.font("", FontWeight.BOLD, 20));
		firstNameLabel.setPadding(new Insets(15,0,0,0));
		lastNameLabel.setPadding(new Insets(15,0,0,0));
		birthdayLabel.setPadding(new Insets(15,0,0,0));
		signInBorder.setPadding(new Insets(15,0,0,0));
		signInBorder.setAlignment(Pos.CENTER);
		//NEW USER
		newUser.setMinWidth(WIDTH/2);
		newUser.setAlignment(Pos.CENTER);
		BorderPane.setAlignment(newUserLabel, Pos.CENTER);
		newUserLabel.setFont(Font.font("", FontWeight.BOLD, 20));
		//BOTTOM BUTTOM
		bottomButton.setPadding(new Insets(0,0,10,10));
		bottomButton.setStyle("-fx-font-weight: BOLD");
		
		//**********LAYOUT**********
		root.getChildren().add(border);
		//TITLE
		border.setTop(title);
		//EXISTING USER
		border.setCenter(userType);
		userType.getChildren().addAll(existingUserBorder, newUserBorder);
		existingUserBorder.setTop(existingUserLabel);
		existingUserBorder.setCenter(existingUser);
		existingUser.getChildren().addAll(firstNameLabel, firstNameInput, lastNameLabel, lastNameInput, birthdayLabel, birthdayInput, signInBorder);
		signInBorder.getChildren().addAll(signIn);
		//NEW USER
		newUserBorder.setTop(newUserLabel);
		newUserBorder.setCenter(newUser);
		newUser.getChildren().addAll(createAccount);
		//BOTTOM BUTTON
		border.setBottom(bottomButton);
		bottomButton.getChildren().addAll(mainMenu);
		
		//**********FUNCTIONALITY**********
		signIn.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
            	if(firstNameInput.getText().length() <= maxLength && lastNameInput.getText().length() <= maxLength) {
            		if(birthdayInput.getText().length() == birthdayLength) {
            			Boolean flag = false;
            			for(int i = 0; i < birthdayInput.getText().length(); i++) {
            				if(i != 2 && i != 5) {
            					flag = Character.isDigit(birthdayInput.getText().charAt(i));
            					if(flag == false) {
            						i = 10;
            						System.out.println("ERROR: Birthday must only use digits between '/' in the following format (mm/dd/yyyy)");
            					}
            				} else {
            					if(birthdayInput.getText().charAt(i) != '/') {
            						flag = false;
            						i = 10;
            						System.out.println("ERROR: Birthday must only use digits between '/' in the following format (mm/dd/yyyy)");
            					}
            				}
            			}
            			if(flag == true) {
            				String fileName = "src/OfficeSystem/" + firstNameInput.getText()+lastNameInput.getText()+"_PatientInfo.txt";
            				System.out.println(fileName);
            				File fileCheck = new File(fileName);
            				if(fileCheck.exists()) {
            					System.out.println("signIn clicked\nFirst Name: "+firstNameInput.getText()+"\nLast Name: "+lastNameInput.getText()+"\nBirthday: "+birthdayInput.getText()+"\n");
                				String patientName = firstNameInput.getText()+lastNameInput.getText();
                				patientPortal patientPortalScene = new patientPortal();
                				homePageStage.setScene(patientPortalScene.patientPortalFunction(homePageStage, patientName));
            				}
            			}
            		} else {
            			System.out.println("ERROR: Birthday must be in the following format(mm/dd/yyyy)");
            		}
            	} else {
            		System.out.println("ERROR: Name length must not exceed 32 characters.");
            	}
            	firstNameInput.setText("");
            	lastNameInput.setText("");
            	birthdayInput.setText("");
            }
        });
		
		
		//**********SET SCENE**********
		homePage homePageScene = new homePage();
		mainMenu.setOnAction(e -> homePageStage.setScene(homePageScene.homePageFunction(homePageStage)));
		
		patientCreateAnAccount patientCreateAnAccountScene = new patientCreateAnAccount();
		createAccount.setOnAction(e -> homePageStage.setScene(patientCreateAnAccountScene.patientCreateAnAccountFunction(homePageStage)));
		
		return newScene;
	}
}