# TransportGUI

This is the satrt of a transportaion program. The program will read in inforamtion from a SQL database, display it, allow the user to add
information such as new jobs and drivers and will show the details of the jobs added to the databse. I wanted to add a feature to connect 
this will Google Maps, but was unable to do so due to time constraints.


The program runs just fine, the main things to know and worry about are as follows:

1. The main window shows the driver ID's and their respective starting locations.
2. you can add, delete drivers and add a new job if you so desire. If you are enering a new job you need to enter a date first.
3. When you enter a date it needs to be in the MM-dd-YYYY format, otherwise the SQL queries won't work.
4. Enter a date between 4-20-2016 and 4-22-2016 to see driver records that I have entered in (489588 is the only one with info from 4-22).
5. Once you select a driver press the "select driver" button to go to the next screen, if you haven't enered a date and selected it you will 
recieve a pop up message.
6. On the next screen you can select an order and see the details, if you see no orders displayed no information exists in the 
database and you can enter a new job for that driver ID if you like(just make sure to enter a date before you add a job).

Known Issue:

1. The table sometimes doesn't show up right away, if you exapand the window it will show up.
2. You have to press the "select driver" button twice to move to the next screen.(You only need to do this the first time you run the program)

