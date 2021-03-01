Feature: Login feature
  As a user I should be able to login to the library application.
  Verify that both students and librarians can login.

  @loginAll
  Scenario: User login using appropriate credentials from excel
    Given The user goes to login page
    When The user enters email and password from excel
    Then The user should see expected URL from excel