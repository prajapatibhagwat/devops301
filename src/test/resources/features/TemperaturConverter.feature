@TemperatureConverter 
Feature: I want to convert the given centigrade temperature value into
Fahrenheit value through a standard java program and an online converter application
		
@ConverterViaStandardJavaPrgram
Scenario Outline: Centigrade to Fahrenheit Converter through standard java program
	Given open convertor and give <centigradeValue>
	When convert the Centigrade value into Fahrenheit
	Then check <FahrenheitValue> in output
	
	Examples:
	|	centigradeValue	|	FahrenheitValue	|
	|	45.0			|		113.0		|
	|	22.0			|		71.6		|
	|	40.0			|		104.0		|

@ConverterApplication
Scenario Outline: Centigrade to Fahrenheit Converter through Application
	Given lanuch "<convertorApplication>" Application
	Then page should contains title
	"""
	RapidTables
	Celsius to Fahrenheit conversion
	""" 
	When provide <centigradeValue> value
	And click on convert button
	Then check <FahrenheitValue> in output box
	
	Examples:
	|	convertorApplication 														|	centigradeValue	|	FahrenheitValue	|
	|	https://www.rapidtables.com/convert/temperature/celsius-to-fahrenheit.html	|	45.0			|		113.0		|
	|	https://www.rapidtables.com/convert/temperature/celsius-to-fahrenheit.html	|	22.0			|		71.6		|
	|	https://www.rapidtables.com/convert/temperature/celsius-to-fahrenheit.html	|	40.0			|		104.0		|
