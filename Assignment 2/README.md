# Bharath Sukesh Rover Software System - README

### Purpose: 
* To control a Mars Rover

### Compiling:  

To compile and run do `ant` in the main directory (that contains build.xml) and then to run -- `java -jar dist/RoverApp.jar`. 

> Ref: build.xml adapted for Assignment, based from build.xml given for Practicals*

> To clean up current build files, do  `ant clean`


### Notes:

	This software is designed to be adapted to API Classes provided by another software development team, so will not function as desired
    as a stand-alone software. With the existing stub classes, it should be possible to test each of the commands working bar Driving, as well as 
    events dependent on the Rover transitioning from Driving state ---> Stopped State. 

    But for example you can still test the restrictions placed like not being able to do Soil Analysis whilst moving (and vice versa if performing 
    soil analysis then attempting to Turn or Drive).

    I'm also assuming we didn't need to have our stubs be able to perform absolutely everything, only for our personal testing before submission. 
	
	