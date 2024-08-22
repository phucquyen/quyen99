package com.vti.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.LoginDto;
import com.vti.entity.Account;
import com.vti.service.IAccountService;

@RestController
@RequestMapping(value = "api/v1/logins")
@CrossOrigin("*")
public class LoginController {

	@Autowired
	private IAccountService service;

	@GetMapping()
	public ResponseEntity<?> login(Principal principal) {
// Nhận lại thông tin username của Account đăng nhập từ Principal 
		String username = principal.getName();
// Tìm account dưới DB
		Account accountDB = service.getAccountByUsername(username);

//	Chuyển đổi dữ liệu để gửi về FE
		LoginDto loginDto = new LoginDto();
		loginDto.setId(accountDB.getId());
		loginDto.setFullName(accountDB.getFullname());
		loginDto.setStatus(accountDB.getStatus().toString());

		return new ResponseEntity<>(loginDto, HttpStatus.OK);
	}

}
