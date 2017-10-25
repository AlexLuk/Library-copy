Feature:  User login on site.

  Scenario:
    When  User enter login "user1" and password "password1"
    And Execute query to database
    Then  Then Login should execute

  Scenario:
    When  User enter login "user1" and password "password"
    And Execute query to database
    Then  Then Login should fail