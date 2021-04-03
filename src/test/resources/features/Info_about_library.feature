@info_library
Feature: Librarian able to see the info about library

	#*User Story*:
	#Librarian able to see the info about library #1
	#
	#*Acceptance Criteria:*
	# Verify that only authorized librarian can see total number of users (Note : Authorized user : librarian)
	# Verify that only authorized librarian can see total number of books (Note : Authorized user : librarian)
	# Verify that only authorized librarian can see total number of borrowed books (Note : Authorized user : librarian)

	@G17-146
	Scenario: Librarian able to see the total number of users #1.1
		Given librarian logged in
		Then librarian should see the total number of users

	@G17-147
	Scenario:  Librarian able to see the total number of books. #1.2
		Given The librarian logged in
		When The librarian is on the landing page
		Then librarian should see the total number of books

	@G17-148
	Scenario: Librarian able to see the total number of borrowed books. #1.3
		Given Librarian logged in
		When Librarian is on the landing page
		Then Librarian should see the total number of borrowed books

