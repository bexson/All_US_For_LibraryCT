Feature:  Several modules feature.

  @studentOnBooks
  Scenario:  Students should have access to 2 modules.
    Given the student on the home page
    Then the user should see following modules
      | Books           |
      | Borrowing Books |

  @librarianOnDashb
  Scenario: Librarians should have access to 3 modules
    Given the librarian on the home page
    Then the librarian should see following modules
      | Dashboard |
      | Users     |
      | Books     |
