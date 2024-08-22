package com.vti.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.vti.entity.Account;
import com.vti.form.AccountFormForCreating;
import com.vti.form.AccountFormForUpdating;

public interface IAccountService extends UserDetailsService {

	List<Account> getAllAccounts();

	Account getAccountByUsername(String username);

	Account getAccountByID(short id);

	Account createNewAccount(AccountFormForCreating accountNewForm);

	Account updateAccount(short id, AccountFormForUpdating accountUpdateForm);

	void deleteAccountById(short id);

}
