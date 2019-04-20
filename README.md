# event-registration
A java program that represents a seating chart to manage reservations for events. The seating chart can be initialized to any number of rows and columns and takes both individual and group reservations.

## Getting Started
This project is built with Maven for portability between development environments. To run the project, simply clone the project to your local machine and import it as an existing Maven project into your favorite IDE.
The event registration project currently does not have any dependencies and does not require any other setup to build and run.

### Running
Upon running the project, the program will be expecting a list of initial seat reservations, from stdin, in the form 'R1C1'. The program can take multiple initial reservations, and they should be separated by spaces. For example, an input of
'''
R1C4 R1C5 R2C6
'''
would reserve the seats in row 1 column 4, row 1 column 5, and row 2 column 6. You can skip entering any initial reservations by entering a newline character.

After taking a set of initial reservations, the program will then be ready to take group reservations. The program expects integers, so entering '4' would reserve four consecutive seats, if available. Group reservations cannot span more than one row.
If a group of the requested size has been reserved, the program will output the range of seats that have been reserved (e.g. 'R1C4 - R1C7'). If no group of the requested size is available, the program will output 'Not available'.

The seating chart, upon taking a group reservation request, uses an algorithm to always reserve the "best" group of consecutive seats. The quality of a seat location is determined by the distance from the front and center seat.

When finished inputting group reservations, entering an empty newline character will shutdown the program. The seating chart will output the number of remaining available seats before exiting.
