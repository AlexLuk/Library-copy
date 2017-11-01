package org.library.steps;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.library.db.repo.ReaderRepository;
import org.library.selen.SelenControl;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses = ReaderRepository.class)
public class UserLoginStepDef {

    private final static Logger logger = LoggerFactory.getLogger(SelenControl.class);
    private SelenControl selen = null;

    @Autowired
    ReaderRepository readerRepository;

    @Before
    public void beforeScenario() throws Exception {
        // setting the Selenium control
        selen = new SelenControl();

        // connecting to Selenium
        selen.start();

        logger.info("Selenium started.");
    }


    @Given("^User open login page \"([^\"]*)\"$")
    public void userOpenLoginPage(String url) throws Throwable {
        selen.openPage(url);
    }


    @And("^user press Reader button$")
    public void userPressButton() throws Throwable {
        String readerButtonXpath = "//a[@id='reader_enter']";
        selen.getElemXpath(readerButtonXpath).click();
    }

    @When("^User set login to \"([^\"]*)\"$")
    public void userSetLoginTo(String login) throws Throwable {
        String loginXpath = "//input[@name='email_enter']";
        selen.wait(100, loginXpath);
        WebElement loginInput = selen.getElemXpath(loginXpath);
        selen.setElemText(loginInput, login);
    }

    @And("^User set password \"([^\"]*)\"$")
    public void userSetPassword(String password) throws Throwable {
        String passwordXpath = "//input[@name='passwd_enter']";
        WebElement passwordInput = selen.getElemXpath(passwordXpath);
        selen.setElemText(passwordInput, password);
    }

    @And("^Press Log in button$")
    public void loginPress() throws Throwable {
        String loginButtonXpath = "//button[@name='check_passwd']";
        selen.getElemXpath(loginButtonXpath).click();
    }

    @Then("^User goes to librarian home page$")
    public void userGoesToLibrarianHomePage() throws Throwable {
        if(!selen.getUrl().contains("admin"))
            throw new PendingException();
    }

    @Then("^User goes to reader home page$")
    public void userGoesToReaderHomePage() throws Throwable {
        if(!selen.getUrl().contains("reader"))
            throw new PendingException();
    }

    @And("^user press Librarian button$")
    public void userPressLibrarianButton() throws Throwable {
            String adminButtonXpath = "//a[@id='admin_enter']";
        selen.getElemXpath(adminButtonXpath).click();
    }

    @After
    public void afterScenario(){
        selen.stop();
    }
}
