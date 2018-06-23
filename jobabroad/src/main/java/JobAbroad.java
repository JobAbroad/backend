import static spark.Spark.before;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;
import static spark.Spark.put;

import jobabroad.controller.LoginController;
import jobabroad.controller.UserController;
import jobabroad.utils.Constants;
import spark.Request;
import spark.Response;

public class JobAbroad {
	
    public static void main(String[] args) {
    	
    	UserController userController = new UserController();
    	
    	before(Constants.PATH_USER, (req, res) -> {
			verifyAuth(req, res);
		});

		before(Constants.PATH_USER_ID, (req, res) -> {
			verifyAuth(req, res);
		});
    	
    	// {"username":"jonatha","password":"password"}
    	post(Constants.PATH_LOGIN, (req, res) -> {
    		LoginController loginController = new LoginController();
    		Response response = loginController.login(req, res);
    		return response.body();
    	});
    	
    	get(Constants.PATH_USER, (req, res) -> {
			Response response = userController.getAll(req, res);
			return response.body();
		});
    	
    	post(Constants.PATH_USER, (req, res) -> {
			Response response = userController.create(req, res);
			return response.body();
		});
    	
    	get(Constants.PATH_USER_ID, (req,res) -> {
    		Response response = userController.getById(req, res);
    		return response.body();
    	});
    	
    	delete(Constants.PATH_USER_ID, (req,res) -> {
    		Response response = userController.delete(req, res);
    		return response.body();
    	});
    	
    	put(Constants.PATH_USER_ID, (req,res) -> {
    		Response response = userController.update(req, res);
    		return response.body();
    	});
    }
    
    private static void verifyAuth(Request req, Response res) {
		LoginController loginController = new LoginController();

		if (!loginController.verify(req, res)) {
			halt(401, "User not logged in or disconnected. Please login again");
		}
	}
}