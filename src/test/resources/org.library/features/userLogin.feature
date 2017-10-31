Feature:  User login on site.


  @interface
  Scenario: Successful reader login
    Given User open login page "http://localhost:8080/"
    And user press Reader button
    When User set login to "Madonna@mail.ru"
    And User set password "password"
    And Press Log in button
    Then User goes to reader home page

  @interface
  Scenario: Successful librarian login
    Given User open login page "http://localhost:8080/"
    And user press Librarian button
    When User set login to "admin"
    And User set password "admin"
    And Press Log in button
    Then User goes to librarian home page

  @ignored
  Scenario Outline: Bad login
    Given User open login page "http://localhost:8080/"
    And user press Reader button
    When User set login to <username>
    And User set password <password>
    And Press Log in button
    Then user see <message>
    Examples:
      |username |password |message                                                       |
      |user1    |         |Please provide a password.                                    |
      |         |user1    |Please provide a username.	                                 |
      |testuser |password |That username does not match anything in our records.         |
      |testuser1|password |The password provided does not match the username entered.    |
