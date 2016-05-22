Mastermind game implementation in Java.


Requirements to deploy this web api:

1-Have Java at least version 7 installed in your machine. You can download java installer here: http://www.oracle.com/technetwork/pt/java/javase/downloads/index.html

2-Have a Java EE 7 container installed. For the implementation I used JBoss EAP 6.3.0.GA. You can download Jboss here: http://www.jboss.org/products/eap/download/

3-Extract the Jboss file downloaded, cd inside the installation folder and copy the mastermind.war file submitted into <JBOSS_FOLDER>/standalone/deployments.

4-Start the Jboss server running <JBOSS_FOLDER>/bin/standalone

5-After the server is started you can play along with the api in the following url: http://localhost:8080/mastermind

6-There are two endpoints for mastermind. The first one is to create a new game: http://localhost:8080/mastermind/new_game.
This call must be made in a post method and the payload must be a Json like the one showed below:
{"name":"Alex Zimmerman"}


7-The other endpoint is for the users to make a guess trying to decipher the code generated: http://localhost:8080/mastermind/guess. This call is done in a POST method with JSON in the following format: 
{ 
    "code": "BPPRCPRO", 
    "game_key": "7af60f55-8ee1-45e3-804b-81b4c85dba68" 
}

You must used the game_key returned to you when you created the game.