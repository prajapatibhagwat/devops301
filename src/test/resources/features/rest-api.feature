@Github 
Feature: I want to search the Github Repositories so that can use them for my project purpose

@Keyword 
Scenario Outline: search github repositories using keyword 
	Given I search repository by "<keyword>" 
	When I make the rest call 
	Then response should contain: <code> 
	
	Examples: 
		| keyword  | code   |
		| cucumber | 200	|
		| Anuglar4 | 200	|
		|	Java   | 200	|