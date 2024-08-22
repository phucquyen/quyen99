package com.vti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vti.entity.Account;
import com.vti.form.AccountFormForCreating;
import com.vti.form.AccountFormForUpdating;
import com.vti.repository.IAccountRepository;

@Service
public class AccountService implements IAccountService {
	@Autowired
	private IAccountRepository accountRepository;

	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return accountRepository.findAll();
	}

	@Override
	public Account getAccountByID(short id) {
		// TODO Auto-generated method stub
		return accountRepository.getById(id);
	}

	@Override
	public Account createNewAccount(AccountFormForCreating accountNewForm) {

		Account account = new Account();
		account.setEmail(accountNewForm.getEmail());
		account.setUsername(accountNewForm.getUsername());
		account.setFullname(accountNewForm.getFullname());
		account.setAvatarImageName(accountNewForm.getAvatarImageName());
		account.setMobile(accountNewForm.getMobile());
		account.setAddress(accountNewForm.getAddress());
		account.setPassword(accountNewForm.getPassword());

		Account accountNew = accountRepository.save(account);

		return accountNew;
	}

	@Override
	public Account updateAccount(short id, AccountFormForUpdating accountUpdateForm) {
		Account account = accountRepository.getById(id);
		account.setFullname(accountUpdateForm.getFullname());
		account.setAvatarImageName(accountUpdateForm.getAvatarImageName());
		account.setMobile(accountUpdateForm.getMobile());
		account.setAddress(accountUpdateForm.getAddress());

		Account accountUpdate = accountRepository.save(account);

		return accountUpdate;

	}

	@Override
	public void deleteAccountById(short id) {
		accountRepository.deleteById(id);

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Tim thong tin Account theo Username nhan duoc
		Account account = accountRepository.findByUsername(username);
		// kiem tra xem co du lieu duoi DB
		if (account != null) {
			return new User(account.getUsername(), account.getPassword(), AuthorityUtils.createAuthorityList("user"));
		} else {
			throw new UsernameNotFoundException(username);

		}

	}

	@Override
	public Account getAccountByUsername(String username) {

		return accountRepository.findByUsername(username);
	}

}
