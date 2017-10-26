Feature:  User login on site.

  @database
  Scenario: Successful login
    When  User enter login "user1" and password "5f4dcc3b5aa765d61d8327deb882cf99"
    And Execute query to database
    Then  Then Login should execute
  @database
  Scenario: Bad login
    When  User enter login "user1" and password "password"
    And Execute query to database
    Then  Then Login should fail
  @interface
  Scenario Outline: Bad login
    Given User open login page
    When User set login to <username>
    And User set password <password>
    And Login to library
    Then User see the error message "<message>"
    @english
    Examples:
    |username |password |message                                                       |
    |user1    |         |Please provide a password.                                    |
    |         |user1    |Please provide a username.	                                   |
    |testuser |password |That username does not match anything in our records.         |
    |testuser1|password |The password provided does not match the username entered.    |

  @interface
  Scenario: Successful login
    Given User open login page
    When User set login to "user1"
    And User set password "5f4dcc3b5aa765d61d8327deb882cf99"
    And Login to library
    Then User goes to home page