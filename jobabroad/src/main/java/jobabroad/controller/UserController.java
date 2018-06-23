package jobabroad.controller;

import static jobabroad.utils.JsonUtil.dataToJson;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jobabroad.entity.User;
import jobabroad.services.UserService;
import jobabroad.services.UserServiceImpl;
import jobabroad.utils.Constants;
import spark.Request;
import spark.Response;

public class UserController {

	UserService userService = new UserServiceImpl();

	public Response getAll(Request request, Response response) {
		
		userService.setConn(getConnection());
		response.body(userService.getAll());

		return response;
	};

	public Response getById(Request request, Response response) {

		userService.setConn(getConnection());
		User user = userService.findById(Integer.valueOf(request.params(":id")));

		if (user != null) {
			response.body(dataToJson(user));
		} else {
			response.status(404);
			response.body("User not found");
		}
		return response;
	};
	
	//{ "username": "JonathaMS", "name": "Jonatha Schmitz", "email":"jonatha.schmitz@gmail.com", "password" : "teste",  "permission" : "1" }
	public Response create(Request request, Response response) {
		
		try {
			userService.setConn(getConnection());
			ObjectMapper mapper = new ObjectMapper();
			User user = new User();

			user = mapper.readValue(request.body(), User.class);
			
			String status = userService.add(user);
			
			if (status == Constants.USER_CREATED) {
				response.status(201);
				response.body(status);
				return response;
			} else {
				response.body(status);
				response.status(500);
				return response;
			}

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		response.status(500);
		return response;
	};

	public Response update(Request request, Response response) {
		try {
			userService.setConn(getConnection());
			ObjectMapper mapper = new ObjectMapper();
			User user = new User();

			user = mapper.readValue(request.body(), User.class);
			String status = userService.update(user);

			if (status == Constants.USER_UPDATED) {
				response.status(201);
				response.body(Constants.USER_UPDATED);
				return response;
			} else {
				response.status(500);
				response.body(status);
				return response;
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		response.status(401);
		return response;
	};

	public Response delete(Request request, Response response) {
		userService.setConn(getConnection());
		boolean isDeleted = userService.delete(Integer.valueOf(request.params(":id")));

		if (isDeleted) {
			response.status(201);
			response.body(Constants.USER_DELETED);
		} else {
			response.status(401);
			response.body(Constants.ERROR_USER_NOT_FOUND);
		}

		return response;
	};

	private Connection getConnection() {
		try {
			return DriverManager.getConnection(Constants.DBURL, Constants.DBUSER, Constants.DBPASSWORD);
		} catch (SQLException e) {
			throw(new RuntimeException(Constants.ERROR_UNABLE_CONNECT));
		}
	}
}
