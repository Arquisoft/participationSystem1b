Feature: LoginUser
	As a user o
	I want to log 
	So I can see my suggestions
	
Scenario: User can see all his suggestions
	Given the admin navigates to localhost:8080/
	When the user introduces username "nombre2@gmail.com" and password "password"
	Then the user successfully logs in

