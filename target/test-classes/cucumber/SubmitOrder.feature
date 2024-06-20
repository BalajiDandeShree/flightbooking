@tag
Feature: Purchase the order from Ecommerece website
 
	Background: 
	Given I landded on Ecommerce Page

 
  @Regression 
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to  cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed in ConfirmationPage
  Examples: 
  |					name							|				password 			|   productName			|
  |	shreeganesh@abc.com				|			Shree@1234 			|   ZARA COAT 3			|
 