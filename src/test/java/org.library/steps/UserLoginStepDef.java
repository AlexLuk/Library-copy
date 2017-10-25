package org.library.steps;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.library.db.dao.DAOFactory;
import org.library.db.dao.impl.ReaderDAO;
import org.library.db.models.Reader;

import java.util.Optional;


public class UserLoginStepDef {
    private String userLogin;
    private String userPassword;
    private String userPasswordFromDatabase;
    private DAOFactory daoFactory;


    /**
     * Establish connection to database before each
     */
    @Before
    public void establishDatabaseConnection(){
        daoFactory = DAOFactory.getInstance();
    }

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
        if (userPasswordFromDatabase.equals(userPassword)) {
            throw new PendingException();
        }
    }

    @And("^Execute query to database$")
    public void executeQueryToDatabase() throws Throwable {
        if (daoFactory.isConnected()) {
            Optional<ReaderDAO> dao =
                    (Optional<ReaderDAO>) daoFactory.getModel(ReaderDAO.class);
            dao.ifPresent(daoObj -> {
                System.out.println(daoObj.getById(2, Reader.class).getName());
                /*daoObj.getByTitle("Керри").ifPresent(obj -> {
                    System.out.println(obj.getTitle());
                    System.out.println(obj.getGenre().getName());
                    System.out.println(obj.getIsRare());
                });
                */
            });
        }
        userPasswordFromDatabase = "password1";
    }

    /**
     * Closing database connection after each run of scenario
     */
    @After
    public void closeDatabaseConnection(){
        daoFactory.closeConn();
    }


    /**Method provide user password as it is stored in database
     * @param userPassword - clear user password from input
     * @return userPassword converted with salt and md5
     */
    private String getSaltedMD5(String userPassword) {
        //perform salting and md5 conversion of password
        //String md5UserPassword =
        return userPassword;
    }
}
