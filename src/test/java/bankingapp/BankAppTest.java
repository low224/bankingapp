/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package bankingapp;

import org.junit.Test;

import com.bankingapp.struct.UserAccount;

import static org.junit.Assert.*;

public class BankAppTest {
    @Test 
    public void testCheckBalance() {
    	Integer balance = 100;
    	UserAccount testUser = new UserAccount(balance, "abc", "abc");
        
    	assertTrue("Check Balance must return same amount with user accoount", testUser.checkBalance() == balance);
    }
    
    @Test
    public void testDepostit() {
    	Integer balance = 100;
    	Integer depositAmount = 200;
    	UserAccount testUser = new UserAccount(balance, "abc", "abc");
    	testUser.deposit(depositAmount);
    	assertTrue("The balance must equal after deposit", testUser.checkBalance() == balance + depositAmount);
    	
    }
    
    @Test
    public void testWithdrawal() {
    	Integer balance = 100;
    	Integer withdrawalAmount = 50;
    	UserAccount testUser = new UserAccount(balance, "abc", "abc");
    	testUser.withdrawal(withdrawalAmount);
    	assertTrue("The balance must equal after withdrawal", testUser.checkBalance() == balance - withdrawalAmount);
    }
    
    @Test
    public void testTransfer() {
    	Integer balance = 100;
    	Integer transferAmount = 50;
    	UserAccount testUser = new UserAccount(balance, "abc", "abc");
    	UserAccount targetUser = new UserAccount(balance, "abc1", "abc2");
    	testUser.transfer(targetUser, 50);
    	assertTrue("The test user balance must reduce after transfer", testUser.checkBalance() == balance - transferAmount);
    	assertTrue("The target user balance must increase after transfer", targetUser.checkBalance() == balance + transferAmount);
    }
    
}
