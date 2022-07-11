The base of this framework is all the dependencies downloaded into the pom.xml.
This framework then starts with using a Singleton Design pattern to create a private Driver
constructor that then, is put into a method that if the driver is null it will go ahead
and create a new browser depending on what we define in our configuration properties file.
If we want chrome, firefox, safari, or headless browser to be started. The Driver class also
contains a method to quit the driver after its finished. All feature files(where scenario is written), pages (stores web elements),
steps(test script)and runner classes are connected without having to extend.

If you need to run a specific scenario then in the feature file that scenario should be given a tag name
so in the runner class we can just specify which tag we would like to run that's connected to 1 or more scenarios.

Running a specific suite With Maven commands, is using that specific tag and added it into this command below that is entered into the terminal
->mvn test -Dcucumber.options="—tags @Regression"
->mvn test -Dcucumber.options="—tags @Smoke"