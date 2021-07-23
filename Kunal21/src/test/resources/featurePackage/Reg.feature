Feature: practise1


Background: Launch browser
Given I open chrome browser



Scenario Outline: Verify Successful flow
Given I open website1
When I enter First name as "<FirstName>"
When I enter Last name as "<LastName>"
When I enter Address as "<Address>"
When I enter Email as "<Email>"
When I enter Phone Number as "<PhoneNumber>"
And click on LogOn button

Examples:
|FirstName|LastName|Address|Email|PhoneNumber|
|Amit|Sarode|Wakad Pune|amit@gmail.com|3456|

