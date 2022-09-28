# Bharath Sukesh ElectriCity Model Readme

### Purpose: 
* To model a city's electricity usage

### Files in project: 

### Functionality:  

To compile and run do `ant` in the main directory (that contains build.xml) and then to run -- `java -jar dist/ElectriCity.jar [arguments]`
	*Ref: build.xml adapted for Assignment, based from build.xml given for Practicals*
	
### Arguments:

	After java -jar dist/ElectriCity.jar, it takes in two arguments as the first parameter: -r for reading from a file, or -g for generating a model.
	
	Once you provide one of those, you can provide -d or -w which represent displaying, and writing to file respectively.

	If you choose to provide -r or -w, it must be followed by a filename. 
	
	For ease of use with specifying filenames, I recommend placing any test files in the /resources folder, and running from the main directory as follows:
	
	`java -jar dist/ElectriCity.jar -r resources/<filename> -d`
	
	or
	
	`java -jar dist/ElectriCity.jar -g -w resources/<filename>`
	

	Valid combinations include:
	-g -d === Generate model, and display it.

	-g -w === Generate model, and write it to a file ~ location specified by your commandline arguments.

	-r -d === Read in from a file, and display it.

	-r -w === Read in from a file, and write to a file ~ location specified by your commandline arguments.