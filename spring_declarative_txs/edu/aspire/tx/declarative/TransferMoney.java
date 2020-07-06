package edu.aspire.tx.declarative;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.aspire.decl.daos.DepositDaoImpl;
import edu.aspire.decl.daos.WithdrawDaoImpl;
import edu.aspire.decl.pojos.Deposit;
import edu.aspire.decl.pojos.Withdraw;

@Service("transfermoney")
@Transactional(propagation = Propagation.REQUIRED)
public class TransferMoney implements ITransferMoney {
	@Autowired
	private WithdrawDaoImpl withdrawdao;

	@Autowired
	private DepositDaoImpl depositdao;

	public TransferMoney() {
	}

	public void transfer(final int fromAccNo, final int toAccNo) {
		Withdraw w = withdrawdao.read(fromAccNo); // SELECT
		w.setAmount(w.getAmount() - 100);
		withdrawdao.update(w); // UPDATE

		Deposit d = depositdao.read(toAccNo); // SELECT
		d.setAmount(d.getAmount() + 100); // UPDATE
		depositdao.update(d);
	}
}