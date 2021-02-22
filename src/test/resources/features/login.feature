Feature: Login feature
  As a user I should be able to login to the library application.
  Verify that both students and librarians can login.
  Accounts are: librarian, student

  @login
  Scenario Outline: User logs in using appropriate credentials
    Given The user navigates to login page
    When The user enters "<email>" and "<password>"
    Then "<role>" on "<page>"
    Examples:
      | email               | password | role   | page      |
      | student98@library   | A15Oposz | stu98  | Books     |
      | student99@library   | 8tIDMH5x | stu99  | Books     |
      | student100@library  | CUcGL8fe | stu100 | Books     |
      | librarian15@library | S5Ejblg1 | lib15  | Dashboard |
#  @librarian
#  Scenario: Login as a librarian
#    Given I am on the login page
#    When I login as a librarian
#    Then dashboard should be displayed
#  @student
#  Scenario: Login as a student
#    Given I am on the login page
#    When I login as a student
#    Then books should be displayed