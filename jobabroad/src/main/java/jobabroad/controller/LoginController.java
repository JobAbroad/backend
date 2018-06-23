package jobabroad.controller;

import static jobabroad.utils.JsonUtil.readProperty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jobabroad.entity.User;
import jobabroad.security.JWTUtil;
import jobabroad.services.UserService;
import jobabroad.services.UserServiceImpl;
import jobabroad.utils.Constants;
import spark.Request;
import spark.Response;

public class LoginController {

	JWTUtil jwtUtil = new JWTUtil();
	UserService userService = new UserServiceImpl();

	public Response login(Request request, Response response) {

		String userJson = request.body();
		userService.setConn(this.getConnection());
		User user = userService.findByUserName(readProperty("username", userJson));

		if (user != null && user.getPassword().equals(readProperty("password", userJson))
				&& user.getUsername().equals(readProperty("username", userJson))) {
			response.status(201);
			response.body(jwtUtil.create(user.getUsername()));
			return response;
		} else {
			response.status(401);
			response.body("Authentication error. Unable to login using User/Password provided.");
			return response;
		}
	};

	public boolean verify(Request request, Response response) {

		try {
			Jws<Claims> jws = jwtUtil.decode(request.headers("Authorization"));
			User user = userService.findByUserName(jws.getBody().getSubject());

			if (user != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	private Connection getConnection() {
		try {
			return DriverManager.getConnection(Constants.DBURL, Constants.DBUSER, Constants.DBPASSWORD);
		} catch (SQLException e) {
			throw(new RuntimeException(Constants.ERROR_UNABLE_CONNECT));
		}
	}
}
