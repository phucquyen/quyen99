package com.vti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.AccountDto;
import com.vti.entity.Account;
import com.vti.form.AccountFormForCreating;
import com.vti.form.AccountFormForUpdating;
import com.vti.service.IAccountService;

@RestController
@RequestMapping(value = "api/v1/accounts")
@CrossOrigin("*")
public class AccountController {

	@Autowired
	private IAccountService accountService;

	// lay danh sach ban Account
	@GetMapping()
	public ResponseEntity<?> getAllAccounts() {
		List<Account> accountListDB = accountService.getAllAccounts();
		List<AccountDto> accountListDto = new ArrayList<>();

		// convert accountListDB --> accountListDto
		for (Account accountDB : accountListDB) {
			AccountDto accountDto = new AccountDto();
			accountDto.setId(accountDB.getId());
			accountDto.setEmail(accountDB.getEmail());
			accountDto.setUsername(accountDB.getUsername());
			accountDto.setFullname(accountDB.getFullname());
			accountDto.setAvatarImageName(accountDB.getAvatarImageName());
			accountDto.setMobile(accountDB.getMobile());
			accountDto.setAddress(accountDB.getAddress());
			accountDto.setCreateDate(accountDB.getCreateDate());
			accountDto.setStatus(accountDB.getStatus().toString());

			accountListDto.add(accountDto);
		}

		return new ResponseEntity<>(accountListDto, HttpStatus.OK);
	}

// tim Account theo id
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getAccountByID(@PathVariable(name = "id") short id) {
		try {
			Account accountDB = accountService.getAccountByID(id);
			AccountDto accountDto = new AccountDto();
			accountDto.setId(accountDB.getId());
			accountDto.setEmail(accountDB.getEmail());
			accountDto.setUsername(accountDB.getUsername());
			accountDto.setFullname(accountDB.getFullname());
			accountDto.setAvatarImageName(accountDB.getAvatarImageName());
			accountDto.setMobile(accountDB.getMobile());
			accountDto.setAddress(accountDB.getAddress());
			accountDto.setCreateDate(accountDB.getCreateDate());
			accountDto.setStatus(accountDB.getStatus().toString());

			return new ResponseEntity<>(accountDto, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("NOT found", HttpStatus.NOT_FOUND);
		}
	}

	// Them moi Account
	@PostMapping()
	public ResponseEntity<?> createNewAccount(@RequestBody AccountFormForCreating accountNewForm) {
		try {
			Account accountNew = accountService.createNewAccount(accountNewForm);

//			Convert
			AccountDto accountNewDto = new AccountDto();
			accountNewDto.setId(accountNew.getId());
			accountNewDto.setEmail(accountNew.getEmail());
			accountNewDto.setUsername(accountNew.getUsername());
			accountNewDto.setFullname(accountNew.getFullname());
			accountNewDto.setAvatarImageName(accountNew.getAvatarImageName());
			accountNewDto.setMobile(accountNew.getMobile());
			accountNewDto.setAddress(accountNew.getAddress());
			accountNewDto.setCreateDate(accountNew.getCreateDate());
			accountNewDto.setStatus(accountNew.getStatus().toString());

			return new ResponseEntity<>(accountNewDto, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>("Not OK", HttpStatus.BAD_REQUEST);
		}

	}

	// update Account
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateAccount(@PathVariable(name = "id") short id,
			@RequestBody AccountFormForUpdating accountUpdateForm) {
		try {
			Account accountUpdate = accountService.updateAccount(id, accountUpdateForm);

//			Convert
			AccountDto accountDto = new AccountDto();
			accountDto.setId(accountUpdate.getId());
			accountDto.setEmail(accountUpdate.getEmail());
			accountDto.setUsername(accountUpdate.getUsername());
			accountDto.setFullname(accountUpdate.getFullname());
			accountDto.setAvatarImageName(accountUpdate.getAvatarImageName());
			accountDto.setMobile(accountUpdate.getMobile());
			accountDto.setAddress(accountUpdate.getAddress());
			accountDto.setCreateDate(accountUpdate.getCreateDate());
			accountDto.setStatus(accountUpdate.getStatus().toString());

			return new ResponseEntity<>(accountDto, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("Not found", HttpStatus.OK);
		}

	}

	// xoa Account
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteProductById(@PathVariable(name = "id") short id) {
		try {
			Account accountDelete = accountService.getAccountByID(id);

//			Convert
			AccountDto accountDeleteDto = new AccountDto();
			accountDeleteDto.setId(accountDelete.getId());
			accountDeleteDto.setEmail(accountDelete.getEmail());
			accountDeleteDto.setUsername(accountDelete.getUsername());
			accountDeleteDto.setFullname(accountDelete.getFullname());
			accountDeleteDto.setAvatarImageName(accountDelete.getAvatarImageName());
			accountDeleteDto.setMobile(accountDelete.getMobile());
			accountDeleteDto.setAddress(accountDelete.getAddress());
			accountDeleteDto.setCreateDate(accountDelete.getCreateDate());
			accountDeleteDto.setStatus(accountDelete.getStatus().toString());

//			Xóa Product
			accountService.deleteAccountById(id);

			return new ResponseEntity<>(accountDeleteDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Not found", HttpStatus.OK);
		}
	}
}
