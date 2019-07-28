# Project Manager Application


 This Section will give a complete overview on functional aspects and some modifications w.r.to business requirement for better user experience


 #
* **Section #1: The Add User Component:**
# 

  ```html
     
      * Add User link in the navbar gets highlighted when a user clicks on the "Add User" link in the navigation bar
      * The Browser Title(header) updates to the "Add User" as shown in below screenshots
      * Each field of the add user form allows only alphanumeric value
      * There are two search options in the Add User component , one is direct search using employee Id
      * Other Search(Advance Search) would allow search a user using FirstName , Last Name and Employee Id
      * Sorting of the users on First Name, Last Name and Employee Id is supported in this application 
      * In addition to this we also have an validation in place which would restrict deleting of an user associated with a active project or task 
      * Please find below the screenshot for the implementation
  
  ```

   * Initial View :
      ![image](https://drive.google.com/uc?export=view&id=1Pbsvg_nuU5zwiQWcgcq414z7k5VW6iIA)
      
   * Add User :   
    ![image](https://drive.google.com/uc?export=view&id=1bToa1wLz97IZnJ4aJAFC8w--nyQD0tTC)
    
   * Search User : 
    ![image](https://drive.google.com/uc?export=view&id=1b2Y_iL_kD3zQbEWpls8kIDqGSQveVnyd)
    
    
   * Update User
    ![image](https://drive.google.com/uc?export=view&id=141R6aAoYHOStayEZdHI0EgZ0K9JJcS8N)
    
    
  #
* **Section #2: The Add Project Component:**
# 

  ```html
     
      * Add Project link in the navbar gets highlighted when a user clicks on the "Add Project" link in the navigation bar
      * The Browser Title(header) updates to the "Add Project" as shown in below screenshots
      * Project field is an alphanumeric and prirority is a range field
      * Start Date and End Date cannot be keyed, whenever user tries a calendar pop up will appear. This is to avoid errorneous input from user
      * Advance Search would allow you search a project using Start Date,End Date, Project and Priority
      * Sorting of the users based on Start Date,End Date, number of completed tasks and Priority is supported in this application 
      * In addition to this we also have an validation in place which would restrict deleting of an project associated with a active task 
      * Manager can be added using the pop up window with all the users in the system and the same has advance search option 
      * Please find below the screenshot for the implementation
  
  ```

   * Initial View 1:
      ![image](https://drive.google.com/uc?export=view&id=1xuMO2kZv4ofEkGCfLweGb2qSh6lQ8j_t)
      
   
   * Initial View 2:
      ![image](https://drive.google.com/uc?export=view&id=1CVvP1cESksRR3wQcg-5Q0WpHHwey6tdW)
     
   * Add Project:   
    ![image](https://drive.google.com/uc?export=view&id=1VOEe9Pqc7R-6kRQcvTxiQT7EW-45LeCd)
    
   * Search Project : 
    ![image](https://drive.google.com/uc?export=view&id=1ET1Bz3LE24kty6EPr5_S0dtemLSp3h4s)
  
   * User Popup Window :
      ![image](https://drive.google.com/uc?export=view&id=1Rb3ykckjxNQE1XoEvto_T6ijttlrTQp9)
  
   * Update Project:
    ![image](https://drive.google.com/uc?export=view&id=1Ew1eH6htQYrSXYoEoF5os-YpdcyROL6u)
    
    
    
        
  #
* **Section #3: The Add Task Component:**
# 

  ```html
     
      * Add Task link in the navbar gets highlighted when a user clicks on the "Add Task" link in the navigation bar
      * The Browser Title(header) updates to the "Add Task" as shown in below screenshots
      * Project field is an alphanumeric and prirority is a range field
      * Start Date and End Date cannot be keyed, whenever user tries a calendar pop up will appear. This is to avoid errorneous input from user
      * Project can be added using the pop up window with all the project(Click Search button next to Project) in the system and the same has advance search option 
      * User can be added using the pop up window(Click Search button next to USer) with all the users in the system and the same has advance search option 
      * Parent Task can be added using the pop up window(Click Search button next to Parent Task) with all the users in the system and the same has advance search option 
      * When Set Parent Task is clicked, search button for user will disappear(user becomes read only) also prirority,start date and end date become read only and Task with task name will be as Parent Task.
      * Please find below the screenshot for the implementation
  
  ```

   * Initial View:
      ![image](https://drive.google.com/uc?export=view&id=1CG-4FNGqkGbrPjM-MU_JnXr3p0r16ojw)
   
   * Add Parent Task:   
    ![image](https://drive.google.com/uc?export=view&id=1mu04fkgpiDN2ADa5p_YEY1L8fqiS-fHt)

   * Add Task : 
    ![image](https://drive.google.com/uc?export=view&id=1a5WPVEVcLGss29IR5KYp9lvdTRPrbu69)
    
  
   * User Popup Window :
      ![image](https://drive.google.com/uc?export=view&id=1H2aEaUivhV4qZZCYPvBF2HndzUTGHh86)
  
   * Project Popup Window:
    ![image](https://drive.google.com/uc?export=view&id=1GnvJmEfoeQy4KBzKZ8WjU0DsHa2fiA2c)
 
 
   #
* **Section #4: The View Task Component:**
# 

  ```html
     
      * View Task link in the navbar gets highlighted when a user clicks on the "View Task" link in the navigation bar
      * The Browser Title(header) updates to the "View Task" as shown in below screenshots
      * Tasks can be sorted based on Start Date, End Date, Priority and Completed
      * Clicking on Edit button would redirect the user to update task component
      * Other Search(Advance Search) would allow search a user using FirstName , Last Name and Employee Id
      * Clicking on the end task will make the task read only
      * Using Project Search one can search a task specific to project
      * Please find below the screenshot for the implementation
  
  ```

   * Initial View :
       ![image](https://drive.google.com/uc?export=view&id=19jrTvRT0hqfH4jgpMuwIoYArZpCcV-ca)  
       
   * View with data :
      ![image](https://drive.google.com/uc?export=view&id=1Ui4Pkse8i1pKe7BxhfJkwshr7UkzKY8C)
      
   * Completed - Task become read only :   
    ![image](https://drive.google.com/uc?export=view&id=1EycdviMl2anYFgcW5385Qh5Oj_5csfTe)
    
    
    
    
      #
* **Section #5: The Update Task Component:**
# 

  ```html
     
      * Add Task link in the navbar gets highlighted when a user clicks on the "Edit Task" button in the view task
      * The Browser Title(header) updates to the "UpdateTask" as shown in below screenshots
      * As Project is to be read only field,project Search button would disappear for this view
      * Parent Task status is set parent task checkbox will be read only
      * Task field will be read only
      * Clicking on Cancel Reset(if not clicked on Update), would display the original values
      * User field is disabled, hence user search button is hidden
      * **Clicking on Back button, would redirect you to View Task. This was enhancement added for better user experience**
      * Please find below the screenshot for the implementation
  
  ```

   *  View :
       ![image](https://drive.google.com/uc?export=view&id=1ZycPoLaqq3eoHksnY6C6UzK5N0eUAAGL)  
       
       
   * Parent Task Pop up :
      ![image](https://drive.google.com/uc?export=view&id=1ZycPoLaqq3eoHksnY6C6UzK5N0eUAAGL)
      


      
    

    
  
    
      
     
