# LinkShortener
Schireson Interview question

This project creates a web application in order to produce a shortened URL for the input URL that will redirects to the same page as the original one. The project is created using Java and the Spring Boot/Spring MVC framework for making a web application.

This project is built following the instruction on David Kiss's blog at http://kaviddiss.com/2015/07/18/building-modern-web-applications-using-java-spring/. I need the instruction and the assistance since I am not familiar with Spring Boot or Tomcat to manage the project of this scale, and with the limited time I modeled my project according to the guide provided.

The project is built using the MVC principle, with one controller managing the requests coming in and mapping methods to serve requests in order to process the input, handle the redirection, and generate the shortened URL.

In order to run this project, clone the git folder to your computer and run it using the command 'mvn spring-boot:run'. I believe you need to have Maven installed in order for this to work.

Then, you can access the form to input the long URL in order to create the shortened version by accessing the page:

'http://localhost:8080/

The site and the shortened URL processing will run as long as you continue to have Spring Boot run on your computer. If you terminate Spring, the form will cease to exist.
