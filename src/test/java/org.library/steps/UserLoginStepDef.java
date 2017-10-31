package org.library.steps;

import cucumber.api.PendingException;
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


    @And("^user press \"([^\"]*)\" button$")
    public void userPressButton(String buttonText) throws Throwable {
        String buttonXpath = "//*[@id=\"check_passwd\"]";
//        WebElement button = selen.getElemXpath(buttonXpath);
        WebElement button =selen.getElemXpath("/html/body/div/div/div/a[1]");

        if (button != null && button.getText().equals(buttonText)) {
            System.err.println("Click button " + button.getText());
            button.click();
        } else {
            System.err.println("No enter button");
        }
    }

    @When("^User set login to \"([^\"]*)\"$")
    public void userSetLoginTo(String login) throws Throwable {
       // String loginXpath = "//*[@id=\"email\"]";
        String loginXpath = "//input";
        selen.wait(100, loginXpath);
        WebElement loginInput = selen.getElemXpath(loginXpath);
        if (loginInput != null) {
            System.err.println("Login found " + login);
            selen.setElemText(loginInput, login);
        } else {
            System.err.println("No loginInput");
        }
    }

    @And("^User set password \"([^\"]*)\"$")
    public void userSetPassword(String password) throws Throwable {
        WebElement passwordInput = selen.getElemXpath("//*[@id=\"passwd\"]");
        if (passwordInput != null) {
            System.err.println("Password found " + password);
            selen.setElemText(passwordInput, password);
        } else {
            System.err.println("No passwordInput");
        }
    }

    @And("^Press \"([^\"]*)\" button$")
    public void loginPress(String buttonText) throws Throwable {
        WebElement button = selen.getElemXpath("//*[@id=\"check_passwd\"]");
        if (button != null) {
            button.click();
        } else {
            System.err.println("No " + buttonText);
        }
    }

    @Then("^User goes to librarian home page$")
    public void userGoesToLibrarianHomePage() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^User goes to reader home page$")
    public void userGoesToReaderHomePage() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
