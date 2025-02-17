package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;

import jakarta.servlet.http.HttpSession;

/**
 * 管理者登録関連コントローラー.
 * 
 * @author n.shunsuke
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private HttpSession session;

	@GetMapping("/")
	public String toLogin(LoginForm form) {
		return "administrator/login";
	}

	@Autowired
	private AdministratorService administratorService;

	/**
	 * 管理者情報登録画面に遷移する.
	 * 
	 * @param form フォーム
	 * @return 管理者情報登録画面
	 */

	@GetMapping("/toInsert")
	public String toInsert(InsertAdministratorForm form) {
		return "administrator/insert";
	}

	/**
	 * 管理者情報を追加する.
	 * 
	 * @param form フォーム
	 * @return ログイン画面
	 */
	@PostMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);
		return "redirect:/";
	}

	/**
	 * ログインする.
	 * 
	 * @param form  フォーム
	 * @param model モデル
	 * @return 正なら従業員一覧ページ、不正ならログインページに遷移
	 */
	@PostMapping("/login")
	public String login(LoginForm form, Model model) {
		String mailAddress = form.getMailAddress();
		String password = form.getPassword();

		Administrator adminInfo = administratorService.login(mailAddress, password);

		if (adminInfo == null) {
			// String falseMessage = "メールアドレスまたはパワスワードが不正です";
			model.addAttribute("falseMessage", "メールアドレスまたはパワスワードが不正です");
			return "administrator/login";
		}

		session.setAttribute("adminName", adminInfo.getName());
		return "redirect:employee/showList";

	}

	/**
	 * ログアウト処理.
	 * 
	 * @param form ログイン情報
	 * @return ログイン画面
	 */
	@GetMapping("/logout")
	public String logout(LoginForm form) {
		session.invalidate();
		return "administrator/login";
	}
}
