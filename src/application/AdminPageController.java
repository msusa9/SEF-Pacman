package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import controller.User;


public class AdminPageController implements Initializable {
	
	@FXML
	private Button btnExit;
	@FXML
	private TableView<User> tblTable;
	@FXML
	private TableColumn<User, String> colUsername;
	@FXML
	private TableColumn<User, String>  colPassword;

	public static void main(String[] args) {
		

	}
	public void  changeScene(ActionEvent event)
	{
		Stage primaryStage = new Stage();
		try {
			Parent ParentTable = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
			Scene TableView = new Scene(ParentTable);
			Stage TableViewWindow = (Stage) (((Node) event.getSource()).getScene().getWindow());
			
			TableViewWindow.setScene(TableView);
			TableViewWindow.show();
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	public void changedUsernameCellEvent(CellEditEvent edittedCell)
	{
		User userselected = tblTable.getSelectionModel().getSelectedItem();
		int index = tblTable.getSelectionModel().getFocusedIndex();
		User[] users = new User[100];
		users = deserializeUsers(users);
		users[index].setName(edittedCell.getNewValue().toString());
		serializeUsers(users);
	}
	public void changedPasswordCellEvent(CellEditEvent edittedCell)
	{
		int index = tblTable.getSelectionModel().getFocusedIndex();
		User[] users = new User[100];
		users = deserializeUsers(users);
		int numOfUsers = grabCurrentUsers(users);
		users[index].setPassword(edittedCell.getNewValue().toString());
		serializeUsers(users);	
;	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		colUsername.setCellValueFactory(new PropertyValueFactory<User, String>("Name"));
		colPassword.setCellValueFactory(new PropertyValueFactory<User, String>("Password"));
		
		
		
		ObservableList<User> Person = FXCollections.observableArrayList();
		int numOfUsers = 0;
		User[] users = new User[100];
		users = deserializeUsers(users);
		numOfUsers = grabCurrentUsers(users);
		for(int i = 0; i < numOfUsers; i++)
		{
			Person.add(new User(users[i].getName(), users[i].getPassword(), users[i].getHighScores()));
		} 
		
		tblTable.setItems(Person);
		
		tblTable.setEditable(true);
		colUsername.setCellFactory(TextFieldTableCell.forTableColumn());
		colPassword.setCellFactory(TextFieldTableCell.forTableColumn());
		
	}
	public ObservableList<User> getUser()
	{
		System.out.println("Get User called");
		int numOfUsers = 0;
		User[] users = new User[100];
		users = deserializeUsers(users);
		numOfUsers = grabCurrentUsers(users);
		ObservableList<User> listUsers = FXCollections.observableArrayList();
		System.out.println("Observable list formatted to FX Collections");
		System.out.println("Num of users: " + numOfUsers);
		for(int i = 0; i < numOfUsers; i++)
		{
			System.out.println(i);
			listUsers.add(new User(users[i].getName(), users[i].getPassword(), users[i].getHighScores()));
		}
		System.out.println("Done");
		return listUsers;
		
	}
	public int grabCurrentUsers(User[] users)
	{
		int numOfUsers = 0;
		for(int i = 0; i < users.length-1; i++)
		{
			if(users[i] == null)
			{
				numOfUsers = i;
				break;
			}
		}
		return numOfUsers;
		
	}
	
	public User[] deserializeUsers(User[] users)
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
			return users;
		}catch(ClassNotFoundException exception)
		{
			exception.printStackTrace();
			return users;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;		
	}
	public void serializeUsers(User[] users)
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
}
