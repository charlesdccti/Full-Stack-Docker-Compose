# Docker Commands


 #
* **Section #1: The MySQL container build and run:**
# 

    *  Step #1: Pull SQL Image from docker hub , Command: sudo docker pull mysql:latest
    *  Step #2: Run SQL Container , Command: sudo docker run --name taskmanagerdb -e MYSQL_ROOT_PASSWORD=password -e  MYSQL_DATABASE=taskmanager -e MYSQL_USER=fsduser -e MYSQL_PASSWORD=password -d mysql:latest 

 #
* **Section #2: The Backend container build and run:**
# 
   
    * Step #1: Build the backend image , Command: sudo docker build ./backend -t taskmanagerbackend
    * Step #2: Deploy the image on container , Command:sudo docker run -p 8080:8080 --name taskmanagerbackend --link taskmanagerdb:mysql -d taskmanagerbackend 
    

 #
* **Section #3: The Frontend container build and run:**
#   
    
    * Step #1: Build the frontend image , Command: sudo docker build -t taskmanagerweb  ./webapp
    * Step #2: Deploy the image on container , Command:sudo docker run -d --name taskmanagerweb -p 4200:4200 taskmanagerweb:latest

    
  
    
      
     
