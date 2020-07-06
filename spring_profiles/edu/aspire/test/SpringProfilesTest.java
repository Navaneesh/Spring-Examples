package edu.aspire.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.aspire.config.SpringProfilesConfig;
import edu.aspire.pojos.Customer2;
import edu.aspire.profiles.daos.CustomerDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringProfilesConfig.class })
@ActiveProfiles(profiles = {"dev"})
public class SpringProfilesTest {
	@Autowired
	ApplicationContext context;

	@Test
	public void testSpringHibernate() {
		CustomerDao custDao = (CustomerDao) context.getBean("custdao");
		Customer2 c = new Customer2();
		c.setCno(1);
		c.setCname("ramesh");
		c.setAddress("Ameerpet");
		c.setPhone(7799108899L);
		custDao.save(c);
		System.out.println("Record inserted successfully...");
	}
}
