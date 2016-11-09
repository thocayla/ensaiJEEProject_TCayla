# ensaiJEEProject_TCayla
# Student Name : Thomas CAYLA

# undertow-sample

1. Excute in a terminal mvn clean install from the project.

2. Import in Eclipse or in IntelliJ.

3. To start the database server, just type in a terminal ./runServer.sh from this project.

4. Run the class ServletServer.java

5. Then in your favorite browser and type http://localhost:8080/myapp/index.html
   From this html web page, you can enter your name, your email address, the name of your R program, and the code of your R program.
   
   For example, you can write the following code :
   
   df <- data.frame(x=1:10, y=(1:10)+rnorm(n=10)) ;
   print(df) ;
   print(lm(y ~ x, df))
   
   Then you can run the program and/or save it.
   
6. When you run the program, you obtain the result on localhost:8080/myapp/rservlet
   You get back to the initial html page by clicking on the "Back" button.
   
7. When you save the program, you go on localhost:8080/myapp/load
   All your program are loaded on this html page.
   
   When you click on your specific R code, you go directly on the specific R program on http://localhost:8080/myapp/loadr/* where "*" represents your R program number. 
   
8. To start the database manager, just type in a new terminal ./runManager.sh from this project.
   You can connect to the database in chosing the third configuration which are proposed (HSQL Database Engine Server).
   Then click ok and write the following code to get the database with all your R code: 
   
   SELECT * FROM RProgram
   
9. I tried then to add a JaxRS stack in order to provide a restful webservice API for machine to machine to allow another application to execute an R script on my server and get the results but I failed to correct my mistakes and I haven't had enough time to fix it. So I add an other JaxRS stack to show you the concept of this language. 

	Note that I change the logical port from 8080 to 8081.
	
	Go to http://localhost:8081/status/test it says you "hello”.
	And http://localhost:8081/status/person 
	
	You can test the service using to get a person from your terminal:
	
	curl -H "Content-Type: application/json" -X GET http://localhost:8081/status/person
	
	And to insert a person from your terminal:
	
	curl -H "Content-Type: application/json" -X POST -d '{"name":"test","firstName":"t"}' http://localhost:8081/status/person
	
  
10. I add an other html page accessing on http://localhost:8080/myapp/comments.html where you can send a commentary

11. There is also the logo of ensai that you can load at http://localhost:8080/myapp/picture/ensai.png


