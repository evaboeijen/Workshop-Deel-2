package service;

import java.util.List;
import business.*;
import dao.*;

 

public class AccountDaoService {
	
	private static AccountDao accountDao;

	public AccountDaoService() {
		accountDao = new AccountDao();
	}

	public void persist(Account entity) {
		accountDao.openCurrentSessionwithTransaction();
		accountDao.persist(entity);
		accountDao.closeCurrentSessionwithTransaction();
	}

	public void update(Account entity) {
		accountDao.openCurrentSessionwithTransaction();
		accountDao.update(entity);
		accountDao.closeCurrentSessionwithTransaction();
	}
	
	public Account findById(Long id) {
		accountDao.openCurrentSession();
		Account account = accountDao.findById(id);
		accountDao.closeCurrentSession();
		return account;
	}
	
	//public void delete(String id) {
	public void delete(Long id) {
		accountDao.openCurrentSessionwithTransaction();
		Account account = accountDao.findById(id);
		System.out.println(account + "will be deleted.");
		accountDao.delete(account);
		accountDao.closeCurrentSessionwithTransaction();
	}
	
	public List<Account> findAll() {
		accountDao.openCurrentSession();
		List<Account> accounten = accountDao.findAll();
		accountDao.closeCurrentSession();
		return accounten;
	}

	public void deleteAll() {
		accountDao.openCurrentSessionwithTransaction();
		accountDao.deleteAll();
		accountDao.closeCurrentSessionwithTransaction();
	}

	public AccountDao accountDao() {
		return accountDao;
	}
}

