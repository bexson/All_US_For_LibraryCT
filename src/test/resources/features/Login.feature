Feature: As a user, I should be able to login to the library app.

  @login
  Scenario : verify students login
    Given the user login as a student
    Then the user landing on the books page

#    Scenario: verify librarians login
#      Given the user login as a librarian
#      Then the user landing on the dashboard page