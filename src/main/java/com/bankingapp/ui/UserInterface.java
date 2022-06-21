package com.bankingapp.ui;



public interface UserInterface {
	public enum UIType{
		CONSOLE,
		ATM,
		ONLINE
	}
	
	public boolean login(String name, String plainPassword);
	public void logout();

}
