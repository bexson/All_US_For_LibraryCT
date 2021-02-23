Feature: Login feature
  As a user I should be able to login to the library application.
  Verify that both students and librarians can login.
  Accounts are: librarian, student

  @login
  Scenario Outline: User logs in using appropriate credentials
    Given The user navigates to login page
    When The user enters "<email>" and "<password>"
    And The user clicks sign in button
    Then The user see "<page>" on this "<email>"
    Examples:
      | email               | password | page      |
      | student94@library   | KccVOoLA | Books     |
      | student95@library   | Vu2BiIwB | Books     |
      | student96@library   | HvGl6Nsy | Books     |
      | librarian16@library | 8BzUhhaU | Dashboard |

  @librarian
  Scenario: Login as a librarian
    When The user login as a librarian
    Then dashboard should be displayed
#  @student
#  Scenario: Login as a student
#    Given I am on the login page
#    When I login as a student
#    Then books should be displayed