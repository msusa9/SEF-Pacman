package controller;

import java.io.Serializable;

public class User implements Serializable {
	String Name;
	String Password;
	int[] HighScores;
	
	public User()
	{
		this.Name = "UNKNOWN";
		this.Password = "UNKNOWN";
	}

	

	public User(String name, String password)
	{
		this.Name = name;
		this.Password = password;
	}
	public User(String name, String password, int[] highScore)
	{
		this.Name = name;
		this.Password = password;
		this.HighScores = highScore;
	}
	public boolean attemptLogin(String Username, String Password)
	{
		if(Username.equals(this.Name))
		{
			if(Password.equals(this.Password))
			{
				return true;
			}
		}
		return false;
	}
	public String getName() {
		return Name;
	}

	public int[] getHighScores() {
		return HighScores;
	}

	public String getPassword() {
		return Password;
	}



	public void setName(String name) {
		Name = name;
	}



	public void setPassword(String password) {
		Password = password;
	}



	
}

