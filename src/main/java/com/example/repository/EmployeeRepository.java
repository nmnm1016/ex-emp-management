package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Employee;

@Repository
public class EmployeeRepository {

	public static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {

		Employee employee = new Employee();

		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));

		return employee;

	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 従業員情報を降順に表示
	 * 
	 * @return 従業員リスト
	 */
	public List<Employee> findAll() {

		String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, "
				+ " address, telephone, salary, characteristics, dependents_count FROM employees "
				+ "ORDER BY hire_date DESC";

		List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);

		return employeeList;

	}

	/**
	 * 主キーから従業員情報を取得する.
	 * 
	 * @return 検索された従業員情報
	 */

	public Employee load(Integer id) {

		String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, "
				+ "address, telephone, salary, characteristics, dependents_count FROM employees  WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
		return employee;
	}

	/**
	 * 従業員の更新
	 * 
	 * @param employee 更新後の従業員情報
	 */
	public void update(Employee employee) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		String updateSql = "UPDATE employees  SET name=:name, image=:image, gender=:gender hire_date=:hireDate mail_address=:mailAddress "
				+ " zip_code=:zipCode address=:address telephone=:telephone salary=:salary charasteristics=:characteristics dependents_count=:dependentsCount WHERE id =:id";

		template.update(updateSql, param);
	}

}
