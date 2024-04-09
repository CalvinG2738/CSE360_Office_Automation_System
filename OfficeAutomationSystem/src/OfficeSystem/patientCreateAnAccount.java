//Phase2 Submission
//Tu42: Abe Troop, Shawn Neill, Calvin Gregory, Jordan Clifford, Helen Zhang

package OfficeSystem;


//IMPORTS
import java.io.BufferedWriter;
import java.io.File;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class patientCreateAnAccount {

	/*public Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	public int width = (int)size.getWidth();
	public int height = (int)size.getHeight();
	USE IF WE WANT TO UTILIZE THE WHOLE SCREEN SIZE*/
	
	//PUBLIC VARIABLES
	public static final int WIDTH = 770, HEIGHT = 420;
	public final int maxLength = 32;
	public final int birthdayLength = 10;
	
	public Scene patientCreateAnAccountFunction(Stage homePageStage) {
		
		//**********STRUCTURE**********
		StackPane root = new StackPane();
		Scene newScene = new Scene(root, WIDTH, HEIGHT);
		BorderPane border = new BorderPane();
		
		
		//**********VARIABLES**********
		Label title = new Label("Office Automation System for Pediatric Doctor's Office");
		Label firstNameLabel = new Label("First Name:");
		Label lastNameLabel = new Label("Last Name:");
		Label birthdayLabel = new Label("Birthdate:");
		TextField firstNameInput = new TextField();
		TextField lastNameInput = new TextField();
		TextField birthdayInput = new TextField();
		birthdayInput.setPromptText("mm/dd/yyyy");
		Button createAccount = new Button("Create Account");
		Button mainMenu = new Button("Main Menu");
		
		//**********STYLE**********
		//TITLE
		BorderPane.setAlignment(title, Pos.TOP_CENTER);
		title.setFont(Font.font ("", FontWeight.BOLD, 25));
		title.setPadding(new Insets(0,0,25,0));
		title.setAlignment(Pos.TOP_CENTER);
		//MAINMENU
		BorderPane.setAlignment(mainMenu, Pos.BOTTOM_LEFT);
		mainMenu.setStyle("-fx-font-weight: BOLD");
		
		//**********LAYOUT**********
		VBox firstLO = new VBox(5);
		firstLO.getChildren().addAll(firstNameLabel, firstNameInput);
		VBox lastLO = new VBox(5);
		lastLO.getChildren().addAll(lastNameLabel, lastNameInput);
		VBox birthLO = new VBox(5);
		birthLO.getChildren().addAll(birthdayLabel, birthdayInput);
		
		HBox labelsAndInputs = new HBox(10);
		labelsAndInputs.getChildren().addAll(firstLO, lastLO, birthLO);
		labelsAndInputs.setAlignment(Pos.CENTER);
		labelsAndInputs.setStyle("-fx-font-weight: BOLD; -fx-font-size: 15");
		
		VBox MainLayout = new VBox(20);
		MainLayout.getChildren().addAll(labelsAndInputs, createAccount);
		MainLayout.setAlignment(Pos.CENTER);
		MainLayout.setStyle("-fx-font-weight: BOLD; -fx-font-size: 15");
		
		border.setTop(title);
		border.setCenter(MainLayout);
		border.setBottom(mainMenu);
		
		root.getChildren().add(border);
		
		
		//**********FUNCTIONALITY**********
		createAccount.setOnAction(new EventHandler<>() {
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
		            		// When the user puts it in correctly
		            			if(flag == true) {
		            				System.out.println("signIn clicked\nFirst Name: "+firstNameInput.getText()+"\nLast Name: "+lastNameInput.getText()+"\nBirthday: "+birthdayInput.getText()+"\n");
		            			
		            			// Collect patient info from textfields
		            			String firstName = firstNameInput.getText();
		            			String lastName = lastNameInput.getText();
		            			String birthday = birthdayInput.getText();
		            			
		            			String patientID = createPatientID(firstName, lastName);
		            			
		            			String patientInfo = "Patient ID: " + patientID + "\n" 
		            					+ "First Name: " + firstName + "\n"
		            					+ "Last Name: " + lastName + "\n"
		            					+ "Birthday: " + birthday + "\n"
		            					+ "Phone Number: N/A\n"
		            					+ "Email: N/A\n";
		      
		            			savePatientInfoToFile(patientID, patientInfo); // save that info son
		            			
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
		
		homePage homePageScene = new homePage();
		mainMenu.setOnAction(e -> homePageStage.setScene(homePageScene.homePageFunction(homePageStage)));
		
		
		return newScene;
	}
	
	
	// purely based on their first and last names rn for database
		public String createPatientID(String fname, String lname) {
			Random rand = new Random();
			//int randID = rand.nextInt(1000); // "there's a thousand yous, there's only one of me" 🗣️🗣️🗣️🗣️🗣️
			
			String patientID = fname + lname;  //randID; commented out to make it easier for now, prob should get rid of it
			return patientID;
		}
	
	// Save Info to file
		private void savePatientInfoToFile(String patientID, String patientInfo) {
			String fileName = patientID + "_PatientInfo.txt";
			String filePath = "src/OfficeSystem/" + fileName; 
			File file = new File(filePath);
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				writer.write(patientInfo);
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	
}
