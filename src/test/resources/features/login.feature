Feature: User login as student and librarian

  @login
  Scenario : verify students login
    Given the user login as student
    Then the user landing on the books page