package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import database.DBManager;
import database.users.UserManager;
import models.entities.User;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public Result signUp() {
        JsonNode json = request().body().asJson();
        if (json == null) {
            return badRequest("expecting Json data");
        } else {
            String name = json.findPath("name").textValue();
            String username = json.findPath("username").textValue();
            String password = json.findPath("password").textValue();
            if (name == null || username == null || password == null) {
                return badRequest("Missing parameters");
            } else {
                User user = new User(name, username, password);
                DBManager.insert(user);
                return ok("Signed up successfully");
            }
        }
    }

    public Result signIn() {
        JsonNode json = request().body().asJson();
        if (json == null) {
            return badRequest("expecting Json data");
        } else {
            String username = json.findPath("username").textValue();
            String password = json.findPath("password").textValue();
            if (username == null || password == null) {
                return badRequest("Missing parameters");
            } else {
                User user = UserManager.getUserByUsername(username);
                if (user == null) {
                    return ok("You haven't signed up yet");
                } else if (user.getPassword().equals(password)) {
                    System.out.println("######### saved password: " + user.getPassword());
                    System.out.println("######### provided      : " + password);
                    return ok("Signed in successfully");
                } else {
                    return ok("Password is incorrect");
                }
            }
        }
    }
}