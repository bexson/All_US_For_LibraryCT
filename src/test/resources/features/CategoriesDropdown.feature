@categories
Feature: Search books with different categories.
  7. As a students, I should be able to search books with different categories.

  Background: Student is already logged in
    Given Student is on the landing page

  Scenario: Default option is "All" by default
    When Student clicks categories dropdown
    Then Student should see all the options below
      | ALL                     |
      | Action and Adventure    |
      | Anthology               |
      | Classic                 |
      | Comic and Graphic Novel |
      | Crime and Detective     |
      | Drama                   |
      | Fable                   |
      | Fairy Tale              |
      | Fan-Fiction             |
      | Fantasy                 |
      | Historical Fiction      |
      | Horror                  |
      | Science Fiction         |
      | Biography/Autobiography |
      | Humor                   |
      | Romance                 |
      | Short Story             |
      | Essay                   |
      | Memoir                  |
      | Poetry                  |

    Scenario: Student search books with different categories
      When Student selects all the options like below:
        | ALL                     |
        | Action and Adventure    |
        | Anthology               |
        | Classic                 |
        | Comic and Graphic Novel |
        | Crime and Detective     |
        | Drama                   |
        | Fable                   |
        | Fairy Tale              |
        | Fan-Fiction             |
        | Fantasy                 |
        | Historical Fiction      |
        | Horror                  |
        | Science Fiction         |
        | Biography/Autobiography |
        | Humor                   |
        | Romance                 |
        | Short Story             |
        | Essay                   |
        | Memoir                  |
        | Poetry                  |
      Then Student verifies searchBox is displayed