package com.capgemini.bankapp.serviceImpl;

import java.util.Set;

import com.capgemini.bankapp.dao.BankAccountDao;
import com.capgemini.bankapp.daoImpl.BankAccountDaoImpl;
import com.capgemini.bankapp.database.DummyDataBase;
import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.service.BankAccountService;

public class BankAccountServiceImpl implements BankAccountService {

	private BankAccountDao bankAccountDao = new BankAccountDaoImpl();
	Set<BankAccount> bankAccounts = DummyDataBase.getBankAccount();

	@Override
	public double getBalance(long accountId) {
		return bankAccountDao.getBalance(accountId);
	}

	@Override
	public double withdraw(long accountId, double amount) {
		double newBalance;
		for (BankAccount bankAccount : bankAccounts) {
			if(bankAccount.getAccountId()==accountId) {
			if (bankAccount.getAccountBalance() - amount >= 0) {
				newBalance = bankAccount.getAccountBalance() - amount;
				if (bankAccountDao.updateBalance(accountId, newBalance))			
				return newBalance;
			}
//			else 
//				throw new InsufficientBalanceException("Insufficient balance in the account for transaction");
		}
		}
		return 0;
	}

	@Override
	public double deposit(long accountId, double amount) {
		double newBalance;
		for (BankAccount bankAccount : bankAccounts) {
			if(bankAccount.getAccountId()==accountId) {
			newBalance = bankAccount.getAccountBalance() + amount;
			if (bankAccountDao.updateBalance(accountId, newBalance))
			return newBalance;
		}
			}
		return 0;
	}

	@Override
	public boolean fundTransfer(long fromAcc, long toAcc, double amount){

		for (BankAccount bankAccount : bankAccounts) {
			if ((bankAccount.getAccountId() == toAcc) && (toAcc!=fromAcc)){
			  if (withdraw(fromAcc, amount) == 0)
						return false;
			  else {
					deposit(toAcc, amount);
					return true;
				}
			}
		}
		return false;
	}
}
