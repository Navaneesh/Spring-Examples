package edu.aspire.profiles.daos;

import edu.aspire.pojos.Customer2;

public interface CustomerDao {
	public void save(Customer2 e);
	public void update(Customer2 e);
	public void delete(int eno);
	public Customer2 get(int eno);
}
