- The project are created using gradle.
- Using UserAccount Java Object as storage for user.
- Password are stored as hashed hex string.
- All interaction with account balance will be encapsulated in  UserAccount.
- All user interface must implement the UserInterface. 
- Only the console user interface are implemented.
- Unit test for check balance, deposit , withdrawal and transfer are done by using JUnit test

**Possible Extension if time allowed**
- Create super user (admin)
- Implement the remainig UserInterface (ATM , Online etc.)
- Turn the application into proper server application with rest api (using spring boot)

**To run the unit test: ** 
gradlew test

**To assemble the jar: **
gradlew jar
