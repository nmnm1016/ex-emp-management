package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

/**
 * 従業員情報操作用サービス.s
 * 
 * @author n.shunsuke
 *
 */
@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * 従業員リストの取得.
	 * 
	 * @return 検索した従業員一覧
	 */
	public List<Employee> showList() {
		List<Employee> employeeList = employeeRepository.findAll();
		return employeeList;
	}

	/**
	 * 詳細表示する従業員情報検索.
	 * 
	 * @param id 従業員ID
	 * @return 従業員情報
	 */
	public Employee showDetail(Integer id) {
		Employee employee = employeeRepository.load(id);
		return employee;
	}

	
	/**
	 * 従業員情報を検索する.
	 * 
	 * @param employee 更新する従業員情報
	 */
	public void update(Employee employee) {
		employeeRepository.update(employee);
	}

}
