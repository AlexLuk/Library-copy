package org.library.steps;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.library.db.domain.Reader;
import org.library.db.repo.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories(basePackageClasses = ReaderRepository.class)
public class UserLoginStepDef {
    @Autowired
    ReaderRepository readerRepository;

    private String userLogin;
    private String userPassword;
    private String userPasswordFromDatabase;

    @When("^User enter login \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void userEnterLoginAndPassword(String login, String password) throws Throwable {
        userLogin = login;
        userPassword = getSaltedMD5(password);
    }

    @Then("^Then Login should execute$")
    public void thenLoginShouldExecute() throws Throwable {
        if (!userPasswordFromDatabase.equals(userPassword)) {
            throw new PendingException();
        }
    }

    @Then("^Then Login should fail$")
    public void thenLoginShouldFail() throws Throwable {
        if (userPasswordFromDatabase != null && userPasswordFromDatabase.equals(userPassword)) {
            throw new PendingException();
        }
    }

    @And("^Execute query to database$")
    public void executeQueryToDatabase() throws Throwable {
        Optional<Reader> reader = readerRepository.findByEmail("Madonna@mail.ru");

        reader.ifPresent(obj -> {
            userPasswordFromDatabase = obj.getPassword();
        });
    }

    /**
     * Method provide user password as it is stored in database
     *
     * @param userPassword - clear user password from input
     * @return userPassword converted with salt and md5
     */
    private String getSaltedMD5(String userPassword) {
        //perform salting and md5 conversion of password
        //String md5UserPassword =
        return userPassword;
    }

    @Given("^User open login page$")
    public void userOpenLoginPage() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^Login to library$")
    public void loginToLibrary() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
