package edu.aspire.profiles.daos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import edu.aspire.pojos.Customer2;

@Repository("custdao")
public class CustomerDaoImpl implements CustomerDao {
	@Autowired(required=true)
	private HibernateTemplate hibernateTemplate;

	public CustomerDaoImpl() {}

	@Override
	public void save(final Customer2 c) {
		hibernateTemplate.save(c);
	}

	@Override
	public void delete(int eno) {
	}

	@Override
	public Customer2 get(int eno) {
		return null;
	}

	@Override
	public void update(Customer2 e) {
	}
}
