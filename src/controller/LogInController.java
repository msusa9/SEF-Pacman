package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import application.mainMenu;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import controller.User;

public class LogInController {
	@FXML
	private Label lblStatus;
	@FXML
	private TextField txtUsername;
	@FXML
	private TextField txtPassword;
	@FXML
	private Button btnLogin;
	
	public int numOfUsers = 1;
	public int state = 0;
	public User[] users = new User[100];



	
	public void Login(ActionEvent event)
	{  
		/*for(int i = 0; i < 10; i++)
		{
			users[i] = new User(new Integer(i).toString(), "1234");
		}
		serializeUsers(); */
		int currentUser =-1; 
		deserializeUsers();
		grabCurrentUsers();
		for(int i = 0; i < numOfUsers; i++)
		{
			System.out.println(i + ". Username: " + users[i].getName()+ ", Password: " + users[i].getPassword());
		}
		lblStatus.setVisible(true);
		if(txtUsername.getText().equals("Admin") && txtPassword.getText().equals("Joshua"))
		{
			lblStatus.setText("Login Successfully, Admin Privellages Granted");
			currentUser = -2;
			changeScene(event);
		}

		else
		{ 
			for(int i = 0; i < numOfUsers; i++)
			{
				if(users[i].attemptLogin(txtUsername.getText(), txtPassword.getText()))
				{
					Stage previousStage = (Stage) btnLogin.getScene().getWindow();
					previousStage.close();
					lblStatus.setText("Login Successful");
					currentUser = i;
					Stage primaryStage = new Stage();
					mainMenu mM = new mainMenu(primaryStage);
					mM.setUser(users[i]);
					primaryStage.setScene(mM.mMScene);
					break;

				}
			}
		}
		if(currentUser == -1)
		{
			lblStatus.setText("Login Failed");
			txtPassword.setText("");
		}
		
	}
	
	public void Cancel(ActionEvent event)
	{
		Platform.exit();
		System.exit(0);
	}
	public void Register(ActionEvent event)
	{
		deserializeUsers();
		grabCurrentUsers();
		boolean valid = true;
		if(state == 0)
		{
		lblStatus.setText("Enter Name & Password");
		lblStatus.setVisible(true);
		txtUsername.setText("");
		txtPassword.setText("");
		btnLogin.setDisable(true);
		state = 1;
		}
		else if(state == 1)
		{
			if(txtUsername.getText().equals("") || txtPassword.getText().equals(""))
			{
				lblStatus.setText("Username or Password is empty");
				valid = false;
			} 
			else
			{
				for(int i = 0; i < numOfUsers; i++)
				{
					if(txtUsername.getText().equals(users[i].getName()))
					{
						lblStatus.setText("Sorry that username has been taken");
						valid = false;
						break;
					}
				}
				if(valid)
				{

					System.out.println(numOfUsers);
					users[numOfUsers] = new User(txtUsername.getText(), txtPassword.getText());
					serializeUsers(); 
					lblStatus.setText("Account Created");
					btnLogin.setDisable(false);
					state = 0;
					numOfUsers++;
				}
			}
		}
	}
	public void serializeUsers()
	{
		Path currentRelativePath = Paths.get("");
		String pathString = currentRelativePath.toAbsolutePath().toString();
		pathString += "\\Users.ser";
		
		try
		{
			File file = new java.io.File(pathString);
			file.getParentFile().mkdirs();
			if (!file.exists()) {
			    file.createNewFile();
			}
			FileOutputStream OutputFile = new FileOutputStream(pathString);
			ObjectOutputStream outputUser = new ObjectOutputStream(OutputFile);
			outputUser.writeObject(users);
			outputUser.close();
			OutputFile.close();
		}
		catch(IOException exception)
		{
			exception.printStackTrace();
		}
	}
	public void deserializeUsers()
	{
		try 
		{
			
			Path currentRelativePath = Paths.get("");
			String pathString = currentRelativePath.toAbsolutePath().toString();
			pathString += "\\Users.ser";
			
			File file = new java.io.File(pathString);
			file.getParentFile().mkdirs();
			if (!file.exists()) {
			    file.createNewFile();
			}
			
			FileInputStream inputFile = new FileInputStream(pathString);
			ObjectInputStream inputUser = new ObjectInputStream(inputFile);
			users = (User[]) inputUser.readObject();
			inputUser.close();
			inputFile.close();
			
		}catch(ClassNotFoundException exception)
		{
			exception.printStackTrace();
			return;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void grabCurrentUsers()
	{
		for(int i = 0; i < users.length-1; i++)
		{
			if(users[i] == null)
			{
				numOfUsers = i;
				break;
			}
		}
		
	}
	public void  changeScene(ActionEvent event)
	{
		Stage primaryStage = new Stage();
		try {
			Parent ParentTable = FXMLLoader.load(getClass().getResource("/application/AdminScreen.fxml"));
			Scene TableView = new Scene(ParentTable);
			Stage TableViewWindow = (Stage) (((Node) event.getSource()).getScene().getWindow());
			
			TableViewWindow.setScene(TableView);
			TableViewWindow.show();
				/*
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Admin Page");
			primaryStage.show();
			*/
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void  changeSceneUser(ActionEvent event)
	{
		Stage primaryStage = new Stage();
		try {
			
			lblStatus.setText("Login Successful");
			Stage mainMenuStage = new Stage();
			mainMenu mM = new mainMenu(primaryStage);
			mainMenuStage.setScene(mM.mMScene);
			
				/*
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Admin Page");
			primaryStage.show();
			*/
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	
		
	}
}
