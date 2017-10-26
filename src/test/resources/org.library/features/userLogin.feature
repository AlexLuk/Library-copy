Feature:  User login on site.

  Scenario:
    When  User enter login "user1" and password "5f4dcc3b5aa765d61d8327deb882cf99"
    And Execute query to database
    Then  Then Login should execute

  Scenario:
    When  User enter login "user1" and password "password"
    And Execute query to database
    Then  Then Login should fail