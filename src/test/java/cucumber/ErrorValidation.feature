@tag
Feature: Error validation
 
	
 
  @ErrorValidation
  Scenario Outline: Negative Test of Submitting the userName and password
   Given I landded on Ecommerce Page
   When Logged in with username <name> and password <password>
   Then "Incorrect email or password." message is displayed
    Examples: 
  |					name							|				password 			|   productName			|
  |	shreeganeasash@abc.com		|			Shasree@1234 		|   ZARA COAT 3			|