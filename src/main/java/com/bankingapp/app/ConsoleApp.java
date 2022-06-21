package com.bankingapp.app;

import java.util.concurrent.ConcurrentHashMap;

import com.bankingapp.struct.UserAccount;
import com.bankingapp.ui.ConsoleInterfaceSession;

public class ConsoleApp {

	public static void main(String[] args) {
		ConcurrentHashMap<String, UserAccount> userAccountDataBases = new ConcurrentHashMap<String, UserAccount>();
		// Initialized database with one user
		String username = "test";
		String hashedPassword = "900150983cd24fb0d6963f7d28e17f72"; // abc in md5
		UserAccount initUserAccount = new UserAccount(100, username, hashedPassword);

		userAccountDataBases.put(username, initUserAccount);
		
		ConsoleInterfaceSession cs = new ConsoleInterfaceSession(userAccountDataBases);
	}

}
