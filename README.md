# f-task
Java task

## How to run
This program is built with Maven.
`mvn exec:java -Dexec.mainClass="ee.tkasekamp.ftask.App"` to run the main method. Alternatevly import it into an IDE.
Main method is in class App. 

Tests can be run with `mvn test`. There are test for the Service classes.

## The problem
Develop a program that rents film. The user must be able to rent films and see the price of the rent depending on the type of the film. The user can pay with bonus points. The user must pay more if the film is returned late.
 
## The approach
The application is structured like MVC server without the actual server. All data to and from the application is passed with DTOs. The controller takes in the DTO and passes it to the services which contain the actual logic. The controller also prints out the response. The data is stored in the Repository class.

Why this approach? Because it offered more flexibility and modularity compared to just printing out everything in one big class.

There is no demonstration of the Film adding or removing, but all those methods are in FilmService and they work.

The films are rented by LocalDate as this allow to keep track of late returns.
## Functionality
The main method demonstrates all the functionality of the application:
* Renting a film
* Renting a film with bonus points (The price can be paid partially)
* Returning a film normally
* Returning a film late

The application allows to 
* Calculate price based on film type
* Add bonus points according to film type
* Partial or full payment with bonus points
* Calculate late charges