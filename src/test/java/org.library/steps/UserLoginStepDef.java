package org.library.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class UserLoginStepDef {
    private String userLogin;
    private String userPassword;
    private String userPasswordFromDatabase;

    @When("^User enter login \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void userEnterLoginAndPassword(String login, String password) throws Throwable {
        userLogin = login;
        userPassword = password;
    }

    @Then("^Then Login should execute$")
    public void thenLoginShouldExecute() throws Throwable {
        if (!userPasswordFromDatabase.equals(userPassword)) {
            throw new PendingException();
        }
    }

    @Then("^Then Login should fail$")
    public void thenLoginShouldFail() throws Throwable {
        if (userPasswordFromDatabase.equals(userPassword)) {
            throw new PendingException();
        }
    }

    @And("^Execute query to database$")
    public void executeQueryToDatabase() throws Throwable {
        //execute query to database with userLogin
        //get userPassword
        //temporary code for cucumber test
        //userPasswordFromDatabase = getPasswordFromDatabase
        userPasswordFromDatabase = "password1";
    }
}
