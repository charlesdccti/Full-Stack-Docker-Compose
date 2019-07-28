# Section: Back End application

* **Technical Specification:** 

  ```html
    * Java 8 
    * Maven 3.1.1
    * Springboot 2.1.4.RELEASE
    * Hibernate 5.3.9.Final
   ``` 

* **Routes Defintion:**
   
   ```html

   * Create Parent Task  : ${hostname}/taskManager/parentAction/addParent
   * Get All Parent Task : ${hostname}/taskManager/parentAction/getAllParent
   * Add User            : ${hostname}/taskManager/userAction/addUser
   * Update User         : ${hostname}/taskManager/userAction/updateUser
   * Get All User        : ${hostname}/taskManager/userAction/getAllUser
   * Get User By Id      : ${hostname}/taskManager/userAction/getUserById/{userId}
   * Delete User         : ${hostname}/taskManager/userAction/deleteUser
   * Add Project         : ${hostname}/taskManager/projectAction/addProject
   * Update Project      : ${hostname}/taskManager/projectAction/updateProject
   * Get All Project     : ${hostname}/taskManager/projectAction/getAllProject
   * Get Project By Id   : ${hostname}/taskManager/projectAction/getProjectById/{projectId}
   * Delete Project      : ${hostname}/taskManager/projectAction/deleteProject
   * Add Task            : ${hostname}/taskManager/taskAction/addTask
   * Update Task         : ${hostname}/taskManager/taskAction/updateTask
   * Get All Task        : ${hostname}/taskManager/taskAction/getAllTask
   * Get Task By Id      : ${hostname}/taskManager/taskAction/getTaskById/{taskId}
   * Delete Task         : ${hostname}/taskManager/taskAction/deleteTask



* Note: {$hostname} -> localhost:8080 currently

        
*  **Run the application without Docker and Jenkins**

    *  Use command **mvn spring-boot:run** inside the backend folder of this GitHub Repository    
