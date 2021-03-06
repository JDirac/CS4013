
	
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;

import javafx.stage.Screen;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class OwnerMenuLoginRegister{
	private boolean regOwner;
	
	public void setRegLogVal(boolean val) {
		this.regOwner = val;
	}
	
	public Scene getOwnerMenuScene() {
		
		BorderPane pane = new BorderPane();

	    // Place nodes in the pane
	    pane.setTop(new LogoPaneAndTitle()); 
	    
	    pane.setCenter(new MainPane(regOwner)); 
	    
	    // Create a scene and place it in the stage
	    Rectangle2D r = Screen.getPrimary().getBounds();
	    Scene scene = new Scene(pane, r.getWidth()-200, r.getHeight()-200);
	    return scene;
	  }	
	} 

	
	class MainPane extends VBox {
	  public MainPane(boolean regOwner) {
		  setAlignment(Pos.CENTER);
		  setStyle("-fx-background-color: #E0EEE0");
		  setSpacing(25);
		  setPadding(new Insets(20,20,20,20));
		  
		  Label name = new Label("Name");
			name.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
			TextField nameInput = new TextField();
			nameInput.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
			nameInput.setMaxWidth(500);
			Label password = new Label("Password");
			password.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
			TextField passwordInput = new TextField();
			passwordInput.setMaxWidth(500);
			passwordInput.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
			
			Button buttonLogin = new Button("Login");
			buttonLogin.setMaxWidth(250);
			buttonLogin.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
			setMargin(buttonLogin, new Insets(40,0,0,0));
			
			Label address = new Label("Address");
			address.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
			TextField addressInput = new TextField();
			addressInput.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
			addressInput.setMaxWidth(500);
			
			Label eircode = new Label("eircode");
			eircode.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
			TextField eircodeInput = new TextField();
			eircodeInput.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
			eircodeInput.setMaxWidth(500);
			
			Button buttonReg = new Button("Register");
			buttonReg.setMaxWidth(250);
			buttonReg.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
			setMargin(buttonReg, new Insets(40,0,0,0));
			
		if(regOwner==true) {
			getChildren().addAll(name,nameInput,password, passwordInput, buttonLogin);
		}else if(regOwner==false) {
			getChildren().addAll(name,nameInput,address, addressInput, eircode, eircodeInput,password, passwordInput, buttonReg);
		}
		buttonLogin.setOnAction(e ->{
			String ownername = nameInput.getText();
			String ownerPassword = passwordInput.getText();
			//find user using above strings and other classes and set a private static variable loggedInOwner in ownerDashboard.
			OwnerDashboard oDash = new OwnerDashboard();
			oDash.setLoggedIn(ownername, ownerPassword);
			PCMMenu.primaryStage.setScene(oDash.getOwnerMenuScene());
		});
		buttonReg.setOnAction(e ->{
			String ownername = nameInput.getText();
			String ownerAddress = addressInput.getText();
			String ownerEircode = eircodeInput.getText();
			String ownerPassword = passwordInput.getText();
			//register user using above strings and other classes and set a private static variable loggedInOwner in ownerDashboard.
			OwnerDashboard oDash = new OwnerDashboard();
			oDash.registerUser(ownername, ownerAddress, ownerEircode, ownerPassword);
			oDash.setLoggedIn(ownername, ownerPassword);
			PCMMenu.primaryStage.setScene(oDash.getOwnerMenuScene());
		});
	    
	  }
		
	}	
	
	class LogoPaneAndTitle extends HBox{
		public LogoPaneAndTitle() {
		
			FileInputStream input=null;
			try {
				input = new FileInputStream("../resources/logo.png");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			Image image = new Image(input);
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(100);
			imageView.setFitWidth(225);
			HBox hboxImage = new HBox();
			hboxImage.getChildren().add(imageView);
			
			Text heading = new Text("Owner Dashboard");
			heading.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));
			heading.setTextAlignment(TextAlignment.CENTER);
			
			heading.setFill(Color.DARKOLIVEGREEN);
			HBox hboxText = new HBox();
			hboxText.setPadding(new Insets(25,20,20,20));
			hboxText.getChildren().add(heading);
			
			setStyle("-fx-background-color: #C0D9AF");
			HBox.setHgrow(hboxText, Priority.ALWAYS);
			
			getChildren().addAll(hboxText, hboxImage);    
		} 
	}
