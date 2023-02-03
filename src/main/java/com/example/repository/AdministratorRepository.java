package com.example.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Administrator;


@Repository
public class AdministratorRepository {

	public static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {

		Administrator administrator = new Administrator();

		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setId(rs.getInt("id"));

		return administrator;

	};

	
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 *　渡した管理者情報を保存する. 
	 * 
	 */
	public void insert(Administrator administrator) {

		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);

		String insertSql

				= "INSERT INTO employees(name,age,mail_address,password) VALUES(administrator)";

		template.update(insertSql, param);
	}
	 /**
	  * 渡されたメールアドレス、パスワードがDBに存在するか確認をする.
	  * 
	  */
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {

		String sql = "SELECT id, name, age, mail_address, password WHERE mail_address = :mailAddress AND password=:password";

		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password",
				password);

		List<Administrator> administratorList = template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);

		if (administratorList.size() == 0) {
			return null;
		}

		return administratorList.get(0);

	}

}
