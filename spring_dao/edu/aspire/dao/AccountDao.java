package edu.aspire.dao;

import edu.aspire.model.Account;
public interface AccountDao {
	public void save(Account account);
	public Account get(int accno);
	public void update(Account accout);
	public void remove(int accno);
	
	//finder methods
}