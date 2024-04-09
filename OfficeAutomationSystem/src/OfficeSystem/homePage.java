//Phase3 Submission
//Tu42: Abe Troop, Shawn Neill, Calvin Gregory, Jordan Clifford, Helen Zhang

package OfficeSystem;

//IMPORTS
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class homePage extends Application{
	
	public static final int WIDTH = 770, HEIGHT = 420;
	
	public static void main (String[] args) {
		launch(args);
	}
	
	public void start(Stage homePageStage) {
		//**********SET SCENE**********
		Scene newScene = homePageFunction(homePageStage);
		
		homePageStage.setTitle("Pediatric Doctor's Office");
	    homePageStage.setScene(newScene);
	    homePageStage.show();
	}
    
    public Scene homePageFunction(Stage homePageStage) {
    	//**********STRUCTURE**********
    	StackPane root = new StackPane();
    	Scene newScene = new Scene(root, WIDTH, HEIGHT);
		BorderPane border = new BorderPane();
		VBox userSelect = new VBox();
		
		//**********VARIABLES**********
		Label title = new Label("Office Automation System for Pediatric Doctor's Office");
		Label iAm = new Label("I am a:");
		Button doctor = new Button("Doctor");
		Button nurse = new Button("Nurse");
		Button patient = new Button("Patient");
		
		//**********STYLE**********
		//TITLE
		BorderPane.setAlignment(title, Pos.CENTER);
		title.setFont(Font.font ("", FontWeight.BOLD, 25));
		title.setPadding(new Insets(0,0,25,0));
		//USER SELECT
		userSelect.setAlignment(Pos.TOP_CENTER);
		userSelect.setStyle("-fx-font-weight: BOLD; -fx-font-size: 15");
		userSelect.setSpacing(20);
		doctor.setPadding(new Insets(15,100,15,100));
		nurse.setPadding(new Insets(15,100,15,100));
		patient.setPadding(new Insets(15,100,15,100));
		
		//**********LAYOUT**********
		root.getChildren().addAll(border);
		border.setTop(title);
		border.setCenter(userSelect);
		userSelect.getChildren().addAll(iAm, doctor, nurse, patient);
		
		//**********SET SCENE**********
		patientLogin patientLoginScene = new patientLogin();
		patient.setOnAction(e -> homePageStage.setScene(patientLoginScene.patientLoginFunction(homePageStage)));
		
		return newScene;
    }
}
