package com.vti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vti.entity.Account;

public interface IAccountRepository extends JpaRepository<Account, Short> {
	public Account findByUsername(String username);

	@Override
	List<Account> findAll();

}
