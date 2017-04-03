Feature: Cucumber
	As a user o
	I want to log 
	So I can see my suggestions
	
Scenario: User can see all his suggestions
	Given the user navigates to "http://localhost:8080/"
	When the user introduces username "nombre@gmail.com" and password "password"	
	Then the user successfully logs in

