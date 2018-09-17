package com.capgemini.bankapp.daoImpl;

import java.util.Set;

import com.capgemini.bankapp.dao.BankAccountDao;
import com.capgemini.bankapp.database.DummyDataBase;
import com.capgemini.bankapp.model.BankAccount;

public class BankAccountDaoImpl implements BankAccountDao{

	Set<BankAccount> bankAccounts = DummyDataBase.getBankAccount();
	
	@Override
	public double getBalance(long accountId) {
		for (BankAccount bankAccount : bankAccounts) {
			if(bankAccount.getAccountId()==accountId)
			{
				return bankAccount.getAccountBalance();
			}
		}
		return 0;
	}

	@Override
	public boolean updateBalance(long accountId, double newBalance) {
		for (BankAccount bankAccount : bankAccounts) {
			if(bankAccount.getAccountId()==accountId)
			{
				bankAccount.setAccountBalance(newBalance);
				DummyDataBase.setBankAccounts(bankAccounts);
				return true;
			}
		}
		return false;
	}
}
