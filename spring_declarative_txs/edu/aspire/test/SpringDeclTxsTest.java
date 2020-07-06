package edu.aspire.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.aspire.config.SpringDeclTxsConfig;
import edu.aspire.tx.declarative.ITransferMoney;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringDeclTxsConfig.class })
public class SpringDeclTxsTest {
	@Autowired
	private ApplicationContext context;

	@Test
	public void testSpringDeclTxs() {
		ITransferMoney transMoney = (ITransferMoney) context.getBean("transfermoney");
		transMoney.transfer(1, 1);
		System.out.println("successfully transferred...");
	}
}
