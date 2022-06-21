package com.bankingapp.struct;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UserAccount {
	private Integer balance = 0;
	private String username = "";
	private String hashedPassword = "";
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	
	public UserAccount(Integer balance, String username, String hashedPassword) {
		this.balance = balance;
		this.username = username;
		this.hashedPassword = hashedPassword;
	}
	
	public Integer checkBalance() {
		try {
			lock.readLock().lock();
			return balance;
		}finally {
			lock.readLock().unlock();
		}
	}
	
	// Return false if deposit fail
	public boolean deposit(Integer depositAmount) {
		try {
			lock.writeLock().lock();
			balance = balance + depositAmount;
			return true;
		}catch(Exception ex) {
			System.out.println(ex);
			return false;
		}
		finally {
			lock.writeLock().unlock();
		}
	}
	
	// Return false if withdrawal failed, only used by  ATM inteface
	public boolean withdrawal(Integer withdrawalAmount) {
		try {
			lock.writeLock().lock();
			
			if(balance <= 0)
				return false;
			
			balance = balance - withdrawalAmount;
			return true;	
		}finally {
			lock.writeLock().unlock();
		}
	}
	
	
	public boolean transfer(UserAccount targetUserAccount, Integer amount) {
		try {
			lock.writeLock().lock();
			targetUserAccount.deposit(amount);
			this.withdrawal(amount);
			return true;
		}catch(Exception ex) {
			System.out.println(ex);
			return false;
		} finally {
			lock.writeLock().unlock();
		}
	}
	
	public String getHashedPassword() {
		return  hashedPassword;
	}
	
}
