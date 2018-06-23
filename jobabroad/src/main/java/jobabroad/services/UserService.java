package jobabroad.services;

import java.sql.Connection;

import jobabroad.entity.User;

public interface UserService {

	User findByUserName(String username);

	User findById(int id);
	
	String getAll();

	String add(User user);

	String update(User user);

	void setConn(Connection conn);

	boolean delete(Integer id);
}
