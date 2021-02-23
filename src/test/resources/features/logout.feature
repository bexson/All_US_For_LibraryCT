@logoutF
Feature: Logout feature
  As a user, I should be able to logout from the library app.
  Verify that both students and librarians can logout.

  @logout
  Scenario Outline: User logs out using appropriate credentials
    Given the user navigates to login page
    When the user enters "<email>" and "<password>"
    And the user clicks sign in button
    And The user logs out
    Then The user verifies title is "<title>"
    Examples:
      | email               | password | title           |
      | student94@library   | KccVOoLA | Login - Library |
      | student95@library   | Vu2BiIwB | Login - Library |
      | student96@library   | HvGl6Nsy | Login - Library |
      | librarian16@library | 8BzUhhaU | Login - Library |

