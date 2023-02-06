package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;

@Service
@Transactional

public class AdministratorService {
	@Autowired
	private AdministratorRepository administratorRepository;

	/**
	 * 管理者情報を挿入する。
	 * 
	 * @param administrator 管理者情報
	 */
	public void insert(Administrator administrator) {
		administratorRepository.insert(administrator);
	}

	/**
	 * ログイン処理をする。
	 * 
	 * @param mailAddress メールアドレス
	 * @param password    パスワード
	 * @return 検索した管理者情報
	 */
	public Administrator login(String mailAddress, String password) {
		Administrator administrator = administratorRepository.findByMailAddressAndPassword(mailAddress, password);
		return administrator;
	}

}
