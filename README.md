# CIS183_HW03_CRUD

Blake Taylor
CIS 183 - Homeowrk_03 - Student Registration System

This application is a simple student registration program. The user (admin) can manage a database
of Students and Majors using the simple functions on each page. The main screen of the application
shows the user a list of current students in the database from which they can select a student to
view and/or update the details about the selected student. A student can be deleted from the database
by long-clicking the selected student on the main listview. The user also has the ability to add 
new students and new majors to the database by selecting the appropriate button from the main page. 
Lastly the user can search for specific users or a range of users by entering any or all of the search
criteria on the search page. 

The hardest part of this assignment for me was the search function. I built the rest of the application
relatively quickly and easily until I got to the search page. When I started it I was trying to construct
a very complex logic and series of functions to try and search by given criteria using a series of if 
statements. This proved very messy and ineffective. The example from lecture using the series of if 
statements to construct the QUERY made a lot more sense once I saw it and once that clicked the 
rest of the app came together pretty quickly. The only other issue I had hear was trying to figure 
out how to give a "null" selection option for the major spinner. I solved that buy adding a line to
the the major initialization that created a "No Selection" option at the top of the spinner. From
there in my if statement for the Query build rather than if(major.isEmpty()) I used 
if(Objects.equals(m, "No Selection")) to generate the "major is not null" part of the Query.
