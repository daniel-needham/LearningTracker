
# LearningTracker

This was a project that I completed as part of Hyperskill's Java Back End Developer track. [More info here
](https://hyperskill.org/projects/197?track=12)

Overall I found this to be a great project as I was able to use language features that I had learnt to test in a real project. I focused on using Java 8 streams where I could, while also using Comparators and many different collection types. 
Due to Hyperskill releasing requirements of the project in stages, I do feel that certain parts of the program are messy with regards to the relationships between certain classes. Given the opportunity again and knowing the full specification I would better plan the overall structure of the project. In addition each stage had to pass all the required tests which is why information is required to be inputted in certain cumbersome ways.

I have included the .jar if you would like to give the program a go. Below you can find examples of the functionality.


    dan:~/Projects/Learning Progress Tracker$ java -jar learning-progress-tracker.jar 
    Learning Progress Tracker
    ----------------------------------
    Please use the following commands: 
    add students
    add points
    list
    find
    statistics
    notify
    commands - to see this list again 
    exit

    add students
    Enter student credentials or 'back' to return:
	    daniel needham danielneedham@gmail.com
    The student has been added.
	    steve O'double-barrel steve@gmail.com
    The student has been added.
	    steve 'Odouble barrel- badsteve@gmail.com
    Incorrect last name.
	    back
    Total 2 students have been added

	    list
    Students:
    1001
    1002
    1003
    1004
    1005
    1006

	    find
    Enter an id or 'back' to return
	    1001
    1001 points: Java=0; DSA=0; Databases=0; Spring=0

	    add points
    Enter an id and points or 'back' to return
	Please enter points in the format id points points points points
	    1001 10 0 0 0 
    Points updated
	    1001 5 5 0 0
    Points updated
	    1002 10 5 0 0
    Points updated
	    1003 1 500 0 1
    Points updated

steve O'double-barrel steve@gmail.com

statistics
Type the name of a course to see details or 'back' to quit:
Most popular: Java
Least popular: Java
Highest activity: Java
Lowest activity: n/a
Easiest course: DSA
Hardest course: Java
