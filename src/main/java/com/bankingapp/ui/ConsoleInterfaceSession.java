package com.bankingapp.ui;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.concurrent.ConcurrentHashMap;

import com.bankingapp.struct.UserAccount;
import com.bankingapp.ui.UserInterface.UIType;

public class ConsoleInterfaceSession implements UserInterface {
	UIType uiType = UIType.CONSOLE;
	String username = "";
	String password = "";
	UIType userInterfaceType = null;
	MessageDigest digest;
	UserAccount userAccount = null;
	ConcurrentHashMap<String, UserAccount> userAccountDataBases = null;
	private static final char[] HEX_ARRAY = "0123456789abcdef".toCharArray();
	
	
	public ConsoleInterfaceSession(ConcurrentHashMap<String, UserAccount> userAccountDataBases) {
		this.userAccountDataBases = userAccountDataBases;
		
		try {
			digest = MessageDigest.getInstance("MD5"); 
		}catch(Exception ex) {
			
		}
		Thread runningThread = new Thread(new ConsoleSession());
		runningThread.start();
	}
	
	@Override
	public boolean login(String name, String plainPassword) {
		userAccount = userAccountDataBases.get(name);
		// Clear the plain password from memory ASAP
		this.password = "";
		
		if(userAccount == null)
			return false;
		
		digest.update(plainPassword.getBytes());
		byte[] hashedByteArray = digest.digest();
		
	    //Converting the byte array in to HexString format
	    String hexString = bytesToHex(hashedByteArray);
	      
	    
	    if(userAccount.getHashedPassword().equals(hexString.toString())) {
	    	return true;
	    }else 
	    	return false;
		
	}

	@Override
	public void logout() {
		this.userAccount = null;
		this.username = "";
		this.password = "";
		
	}
	
	class ConsoleSession implements Runnable{

		@Override
		public void run() {
			while(true) {
				promptLogin();
				if(login(username, password) == true){
					promptAction();
				}else{
					System.out.println("Invalid username or password");
				}
				logout();
			}
			
		}	
	}
	
	private void promptLogin(){
		System.out.println("Please enter username:");
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			username = reader.readLine();
			//Only run in command prompt interfac, do not work in IDE
			System.out.println("Please enter password:");
			password = reader.readLine();
		}catch(IOException ex) {
			System.out.println("Error input username. Try again");
		}	
	}
	
	private void promptAction() {		
		System.out.println("=====================ACTION=====================");
		System.out.println("Please choose an action:");
		System.out.println("1. CHECK BALANCE \n2. DEPOSIT \n3. WITHDRAWAL \n4. TRANSFER \n5. Logout" );
		
		Integer action = 0;
		
		while (true) {
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				action = Integer.valueOf(reader.readLine());
				break;
			} catch (Exception ex) {
				System.out.println("Invalid action");
			}
		}
		
		switch(action) {
		case 1:
			System.out.println("Balance: " + userAccount.checkBalance());
			break;
		case 2:
			Integer depositAmount = 0;
			while (true) {
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
					System.out.println("Depost Amount");
					depositAmount = Integer.valueOf(reader.readLine());
					break;
				} catch (Exception ex) {
					System.out.println("Invalid input");
				}
			}
			userAccount.deposit(depositAmount);
			break;
		case 3:
			Integer withdrawalAmount = 0;
			while (true) {
				try{
					BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
					System.out.println("Withdrawal Amount");
					withdrawalAmount = Integer.valueOf(reader.readLine());
					break;
				} catch (Exception ex) {
					System.out.println("Invalid input");
				}
			}
			userAccount.deposit(withdrawalAmount);
			break;
		case 4:
			Integer transferAmount = 0;
			String targetUsername = "";
			while (true) {
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
					System.out.println("Depost Amount:");
					transferAmount = Integer.valueOf(reader.readLine());
					System.out.println("username:");
					targetUsername = reader.readLine();
					break;
				} catch (Exception ex) {
					System.out.println("Invalid action");
				}
			}
			UserAccount targetUserAcount = userAccountDataBases.get(targetUsername); 
			if(targetUserAcount == null) {
				System.out.println("no such user exist");
			}else {
				userAccount.transfer(targetUserAcount, transferAmount);
			}
			break;
		case 5:
			System.out.println("Invalid Action");
			break;
		default:
			System.out.println("System exit");
		}
	}
	
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for (int j = 0; j < bytes.length; j++) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = HEX_ARRAY[v >>> 4];
	        hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
	    }
	    return new String(hexChars);
	}

}
