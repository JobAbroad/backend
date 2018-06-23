package jobabroad.services;

import static jobabroad.jooq.Tables.USER;

import java.sql.Connection;

import org.jooq.DSLContext;
import org.jooq.JSONFormat;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import jobabroad.entity.User;
import jobabroad.exceptions.UserNotFoundException;
import jobabroad.utils.Constants;

public class UserServiceImpl implements UserService {

	private Connection conn;
	
	public UserServiceImpl() {
	}

	@Override
	public User findByUserName(String username) {
		User user = new User();
		try {
			DSLContext dslContext = DSL.using(this.getConn(), SQLDialect.MYSQL);

			Record result = dslContext.select().from(USER).where("USER.USERNAME = '" + username + "'").fetchOne();

			if (result == null) {
				throw new UserNotFoundException("user not found");
			} else {
				user.setUsername(result.get(USER.USERNAME));
				user.setPassword(result.get(USER.PASSWORD));
				user.setName(result.get(USER.NAME));
				user.setEmail(result.get(USER.EMAIL));
				user.setId(result.get(USER.ID));
				user.setPermission(result.get(USER.PERMISSION));
			}

			dslContext.close();

		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}
	
	@Override
	public User findById(int id) {
		User user = new User();
		try {
			DSLContext dslContext = DSL.using(this.getConn(), SQLDialect.MYSQL);

			Record result = dslContext.select().from(USER).where("USER.ID =" + id ).fetchOne();

			if (result == null) {
				throw new UserNotFoundException("user not found");
			} else {
				user.setId(result.get(USER.ID));
				user.setUsername(result.get(USER.USERNAME));
				user.setEmail(result.get(USER.EMAIL));
				user.setPassword(result.get(USER.PASSWORD));
				user.setPermission(result.get(USER.PERMISSION));
				user.setName(result.get(USER.NAME));
			}

			dslContext.close();

		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public String getAll() {
		try {
			DSLContext dslContext = DSL.using(this.getConn(), SQLDialect.MYSQL);
			
			String result = dslContext.select().from(USER).fetch().formatJSON(JSONFormat.DEFAULT_FOR_RECORDS);
			
			dslContext.close();
			
			return result;
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Constants.ERROR_USER_NOT_FOUND;
	}

	@Override
	public String add(User user) {
		
		String status = Constants.ERROR_SAVING_USER;
		
		try {
			DSLContext dslContext = DSL.using(this.getConn(), SQLDialect.MYSQL);

			Result<Record> newUser = dslContext.select().from(USER)
											.where("username = '" + user.getUsername() + "'")
											.or("email = '" + user.getEmail() + "'")
											.fetch();

			
			if (newUser != null && newUser.size() == 0 ){

				dslContext.insertInto(USER, USER.USERNAME, USER.NAME, USER.EMAIL, USER.PASSWORD, USER.PERMISSION)
						  .values(user.getUsername(), 
								  user.getName(), 
								  user.getEmail(), 
								  user.getPassword(), 
								  user.getPermission())
						  .execute();
				status = Constants.USER_CREATED;
			} else {
				status = Constants.USER_ALREADY_EXISTS;
			}

			dslContext.close();
			
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public String update(User user) {
		
		String status = Constants.ERROR_SAVING_USER;
		
		try{
			DSLContext dslContext = DSL.using(this.getConn(), SQLDialect.MYSQL);

			Record result = dslContext.select()
									  .from(USER)
									  .where("USER.ID = " + user.getId())
									  .fetchOne();

			if (result != null) {
				result.set(USER.NAME, user.getName());
				result.set(USER.PASSWORD, user.getPassword());
				result.set(USER.PERMISSION, user.getPermission());
				
				dslContext.update(USER).set(result)
										.where("USER.ID = " + user.getId())
										.execute();
				status = Constants.USER_UPDATED;
			} else {
				status = Constants.ERROR_USER_NOT_FOUND;
			}

			dslContext.close();

			return status;
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	@Override
	public void setConn(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean delete(Integer id) {
		try {
			DSLContext dslContext = DSL.using(this.getConn(), SQLDialect.MYSQL);
			Record result = dslContext.select().from(USER).where("USER.ID = " + id).fetchOne();

			if (result == null) {
				dslContext.close();
				return false;
			}

			dslContext.deleteFrom(USER).where("USER.ID = " + id).execute();
			dslContext.close();

			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	public Connection getConn() {
		return conn;
	}

}
