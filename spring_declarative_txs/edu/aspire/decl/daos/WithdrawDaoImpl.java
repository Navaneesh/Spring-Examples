package edu.aspire.decl.daos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import edu.aspire.decl.pojos.Withdraw;

@Repository
public class WithdrawDaoImpl {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public Withdraw read(int id) {
		return (Withdraw) hibernateTemplate.get(Withdraw.class,
				new Integer(id));
	}
	public void update(Withdraw w){
		hibernateTemplate.update(w);
	}
}
