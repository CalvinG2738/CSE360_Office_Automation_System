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
	
	public Scene patientPortalFunction(Stage homePageStage) {
		//**********STRUCTURE**********
		StackPane root = new StackPane();
		Scene newScene = new Scene(root, WIDTH, HEIGHT);
		BorderPane border = new BorderPane();
				
		//**********VARIABLES**********
		Label title = new Label("Office Automation System for Pediatric Doctor's Office");
		
		Label patientHistory = new Label("Patient History: ");
		Label healthIssuesLabel = new Label("Health Issues: ");
		Label prevMedsLabel = new Label("Previously Prescribed Medications: ");
		Label histImmunizationsLabel = new Label("History of Immunizations: ");
		Label contInfo = new Label("Contact Information: ");
		Label email = new Label("Email: ");
		Label phoneNum = new Label("Phone Number: ");
		//**AREAS ALL RELIENT ON DOC/NURSE INPUTS***
		TextArea healthIssuesArea = new TextArea("");
		TextArea prevMedsArea = new TextArea("");
		TextArea histImmunizationsArea = new TextArea("");
		healthIssuesArea.setEditable(false);
		prevMedsArea.setEditable(false);
		histImmunizationsArea.setEditable(false);
		
		MenuButton date = new MenuButton("Date");
		Button show = new Button("Show");
		Button updateContInfo = new Button("Update Contact Information");
		Button messaging = new Button("Messaging");
		Button mainMenu = new Button("Main Menu");
		
		//**********STYLE**********
		//TITLE
		BorderPane.setAlignment(title, Pos.CENTER);
		title.setFont(Font.font ("", FontWeight.BOLD, 25));
		title.setPadding(new Insets(0,0,25,0));
		
		//**********LAzy ahh laYOUT**********
		HBox patHist = new HBox(10);
		patHist.getChildren().addAll(patientHistory, date, show);
		VBox labelsAndAreas = new VBox(10);
		labelsAndAreas.getChildren().addAll(healthIssuesLabel, healthIssuesArea, prevMedsLabel, prevMedsArea, histImmunizationsLabel, histImmunizationsArea);
		VBox contactInfoLayout = new VBox(10);
		contactInfoLayout.getChildren().addAll(contInfo, email, phoneNum, updateContInfo);
		VBox MainLayout = new VBox(2);
		MainLayout.getChildren().addAll(title, patHist, labelsAndAreas, contactInfoLayout, messaging, mainMenu);
		

		border.setCenter(MainLayout);
		root.getChildren().add(border);
		
		//**********SET SCENE**********
		homePage homePageScene = new homePage();
		mainMenu.setOnAction(e -> homePageStage.setScene(homePageScene.homePageFunction(homePageStage)));
		
		return newScene;
	}

}
