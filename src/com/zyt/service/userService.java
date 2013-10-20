package com.zyt.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sun.security.timestamp.TSRequest;

import com.zyt.domain.User;
import com.zyt.util.SqlHelper;

public class userService {

	public User getUserById(String id) {
		User user = new User();
		String sql = "SELECT * FROM users where id=?";
		String[] parameter = { id };
		ResultSet rs = SqlHelper.executeQuery(sql, parameter);
		try {

			if (rs.next()) {
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setGrade(rs.getString(4));
				user.setPwd(rs.getString(5));
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}
		
		 return user;
	}
	
	//add user
	
	public boolean addUser(User user) {
		boolean b = true;
		String sql = "insert into users values(null, ?, ?, ?, ?)";
		String[] parameters = {
				user.getUsername(),
				user.getEmail(),
				user.getGrade(),
				user.getPwd()
		};
		
		try {
			SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			
			b = false;
			// TODO: handle exception
		}
		
		return b;
		
	}
	
	//modify user
	
	public boolean updateUser(User user) {
		String sql = "UPDATE users SET username=?, email=?, grade=?, password=? WHERE ID = ?";
		String[] parameters = {user.getUsername(), user.getEmail(), user.getGrade(), user.getPwd(), Integer.toString(user.getId())};
//		for (String string : parameters) {
//			System.out.println(string);
//		}
		
		boolean b =false;
		
		try {
			SqlHelper.executeUpdate(sql, parameters);
			b=true;
		} catch (Exception e) {
			// TODO: handle exception
			b= false;
		}
		
		return b;
	}
	

	public boolean delUser(String id) {
		boolean b = true;

		String sql = "DELETE FROM users WHERE ID = ?";

		String[] parameters = { id };

		try {
			SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			b = false;
			// TODO: handle exception
		}

		return b;
	}

	public int getPageCount(int pageSize) {
		String sql = "SELECT count(*) FROM users";

		ResultSet rs = SqlHelper.executeQuery(sql, null);

		int rowCount = -1;

		try {
			rs.next();
			rowCount = rs.getInt(1);
			System.out.println(rowCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}

		return (rowCount - 1) / pageSize + 1;

	}

	public ArrayList getUsersByPage(int pageNow, int pageSize) {
		ArrayList<User> al = new ArrayList<User>();

		String sql = "SELECT * FROM users limit " + ((pageNow - 1) * pageSize)
				+ ", " + pageSize;

		ResultSet rs = SqlHelper.executeQuery(sql, null);

		try {
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt(1));
				u.setUsername(rs.getString(2));
				u.setEmail(rs.getString(3));
				u.setGrade(rs.getString(4));

				al.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}

		return al;
	}

	// verify if the user is OK

	public boolean checkUser(User user) {

		boolean b = false;

		String sql = "SELECT * FROM users WHERE id=? and password=?";

		String[] parameters = { user.getId() + "", user.getPwd() };

		System.out.println(parameters[0] + " " + parameters[1]);

		ResultSet rs = SqlHelper.executeQuery(sql, parameters);

		try {
			if (rs.next()) {
				b = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}

		return b;
	}

}
