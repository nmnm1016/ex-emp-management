package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;

/**
 * 従業員用コントローラー
 * 
 * @author n.shunsuke
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * 従業員一覧を表示するメソッド
	 *
	 * @param model モデル
	 * @return 従業員一覧画面
	 */
	@GetMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = employeeService.showList();
		model.addAttribute("employeeList", employeeList);
		return "employee/list";
	}

	@GetMapping("/showDetail")
	public String showDetail(String id, Model model, UpdateEmployeeForm form) {
		Employee employee = employeeService.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		return "employee/detail";
	}
	
	/**
	 *従業員IDを受け取り、対象の扶養人数を更新
	 * 
	 * @param id 
	 * @param dependentsCounts 扶養人数
	 * @return　従業員一覧
	 */
	@PostMapping("/update")
	public String update(String id, String dependentsCount) {
		Employee employee = employeeService.showDetail(Integer.parseInt(id));
		employee.setDependentsCount(Integer.parseInt(dependentsCount));
		employeeService.update(employee);
		return "redirect:/employee/showList";
	}
	
}
