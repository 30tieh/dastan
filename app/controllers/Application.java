package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.entities.users.User;
import models.entities.users.registeration.Registration;
import models.entities.users.registeration.RegistrationException;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public Result signUp() {
        JsonNode json = request().body().asJson();
        User signedUpUser = null;
        if (json == null) {
            return badRequest("expecting Json data");
        } else {
            String name = json.findPath("name").textValue();
            String username = json.findPath("username").textValue();
            String password = json.findPath("password").textValue();
            if (name == null || username == null || password == null) {
                return badRequest("Missing parameters");
            } else {
                try {
                    signedUpUser = Registration.signUp(name, username, password);
                } catch (RegistrationException e) {
                    e.printStackTrace();
                }
                return ok("Signed up successfully");
            }
        }
    }

    public Result signIn() {
        JsonNode json = request().body().asJson();
        User signedInUser = null;
        if (json == null) {
            return badRequest("expecting Json data");
        } else {
            String username = json.findPath("username").textValue();
            String password = json.findPath("password").textValue();
            if (username == null || password == null) {
                return badRequest("Missing parameters");
            } else {
                try {
                    signedInUser = Registration.signIn(username, password);
                } catch (RegistrationException e) {
                    e.printStackTrace();
                }
            }
        }
        return ok("Signed in successfully");
    }
}