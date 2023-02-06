package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;

/**
 * 管理者登録関連コントローラー.
 * @author n.shunsuke
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

	

	@GetMapping("/")
	public String toLogin(LoginForm form) {
		return "administrator/login";
	}
	@Autowired
	private AdministratorService administratorService;

	/**
	 * 管理者情報登録画面に遷移する.
	 * @param form フォーム
	 * @return 管理者情報登録画面
	 */
	
	@GetMapping("/toInsert")
	public String toInsert(InsertAdministratorForm form) {
		return "administrator/insert";
	}
	
	@PostMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);
		return "redirect:/";
	}
}
