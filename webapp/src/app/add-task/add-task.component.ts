import { Component, OnInit } from '@angular/core';
import { Task } from "../model/task";
import {FormGroup, FormControl, Validators} from "@angular/forms";
import {ReactiveFormsModule, FormsModule} from "@angular/forms";
import { ToasterService } from '../service/toaster.service';
import { JsongeneratorService } from '../service/jsongenerator.service';
import { UserModel } from '../model/user-model';
import { Project } from '../model/project';
import { Title} from '@angular/platform-browser';
import { ParentTask } from '../model/parent-task';
 

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent implements OnInit {

  addTaskForm:FormGroup;
  data:any;
  dataProject:any;
  dataUser:any;
  buttonValue:string;
  flag:boolean = false;
  order:string; 
  popupManager:string;
  userAssociated:UserModel;
  projectAssociated:Project;
  popupProject:string;
  isDisabled = false;
  parentTaskAssociated:ParentTask;
  parentTask:string;

  constructor(private jsonService:JsongeneratorService, private toasterService:ToasterService , private titleService:Title) { 
    
    this.buttonValue = "Add Task";

    var addDate =  new Date().getTime() +(1000*60*60*24);
    var tommorowDate = new Date(addDate);
    var tommorowString = (tommorowDate.getMonth() + 1)  + '/' + tommorowDate.getDate()  + '/' + (tommorowDate.getFullYear() ) 

    this.addTaskForm=new FormGroup({
     taskId:new FormControl(''),
     project:new FormControl('',[Validators.required,Validators.pattern('[a-zA-Z0-9]{1,200}')]),
     task:new FormControl('',[Validators.required,Validators.pattern('[a-zA-Z0-9]{1,200}')]),
     priority: new FormControl({value:0,disabled:this.isDisabled},[Validators.required,Validators.pattern('[0-9]{1,2}')]),
     user: new FormControl(""),
     enableDate:new FormControl(""),
     parentTask:new FormControl(""),
     startDate:new FormControl({value:new Date(),disabled:this.isDisabled}),
     endDate:new FormControl({value:tommorowString ,disabled:this.isDisabled})
    })
    this.loadData();    
  }

  ngOnInit() {
  this.titleService.setTitle(" Add Task");
  }
  
  loadData()
  {
    this.isDisabled=false;

    let observable1=this.jsonService.getAllParentTask();
    observable1.subscribe
    (
      (data:any)=>
      {
          this.data=data;
      },
    )

    let observable2=this.jsonService.getAllProject();
    observable2.subscribe
    (
      (data:any)=>
      {
          this.dataProject=data;
      },
    )

    let observable3=this.jsonService.getAllUser();
    observable3.subscribe
    (
      (dataUser:UserModel[])=>
      {
          this.dataUser=dataUser;
      },
    )
    
  }
  

  formHandler(){
       this.addTask();
      }

    
   
  resetForm(){
    this.addTaskForm.reset(); 
    this.addTaskForm.get('priority').setValue(0);
    this.buttonValue = "Add Task";
    (<HTMLInputElement>document.getElementById("put")).value = "0";
    this.isDisabled=  false;
    this.enableDateField();
  }


addTask()
{
  var data:any = this.addTaskForm.value;
  var ind:any ="";
  var indicator:boolean = true; 
  if(!this.parentTaskAssociated)
      this.parentTaskAssociated = new ParentTask( ind, "This Task has no parent");

  if(this.isDisabled)
    {
          var putData:any = ""; 
         var  dataAdd = new ParentTask(putData, data.task);
          this.jsonService.insertParent(dataAdd).subscribe(
            (dataAdd)=>{
              this.toasterService.successMessages("Parent Task:" + dataAdd.parentTask + " Added Successfully");
            },
            (error) => {
              this.toasterService.deleteMessages("Addition of Parent Task failed");
            }
            );

          indicator = false;
          this.resetForm();
    } 

  else if(!data.startDate || data.startDate == null || data.startDate == "")
  {
    indicator = false;
    this.toasterService.deleteMessages("Invalid Start Date: Provided the start date in MM/dd/YYYY format");
  }
  else if(!data.startDate || data.endDate == null || data.endDate == "")
  {
    indicator = false;
    this.toasterService.deleteMessages("Invalid End Date: Provided the end date in MM/dd/YYYY format");
  }
  else if(data.startDate > data.endDate){    
    indicator = false;  
    this.toasterService.deleteMessages(" Addition of Task Aborted!! Start Date cannot be greater than End date");           
  }

  if(indicator)
  {

    if(typeof data.endDate == "string") 
        data.endDate = new Date(data.endDate);
          
  var taskNew = new Task(data.taskId, data.task, data.priority, "Started" , this.userAssociated, this.parentTaskAssociated, this.projectAssociated,this.convertDateFormat(data.startDate),this.convertDateFormat(data.endDate));
  this.resetForm();

  this.jsonService.insertTask(taskNew).subscribe(
      (data)=>{
        this.toasterService.successMessages("Task Details for *" + data.task  +"* has been successfully registered");
        this.loadData(); 
      },
      (error)=>{
        this.toasterService.deleteMessages(" Addition of Task failed with Error Code:", error.status);
      } ) }

}

toggleCheckBoxValue()
  {
      this.isDisabled = !this.isDisabled;
      this.enableDateField();
  }

setOutput(object:any)
{
     (<HTMLInputElement>document.getElementById("put")).value = object.value;
}

selectManager(data:any)
{
  this.userAssociated = data;
  this.popupManager = data.employeeId;
  this.toasterService.infoMessages("Manager:" + data.employeeId  + "added Successful!! Kindly close the popup to proceed with Task creation");
}

selectTask(data:any)
{
  var dataAny :any = " ";
  this.parentTaskAssociated = new ParentTask(dataAny , data.parentTask);
  this.parentTask = data.parentTask;
  this.toasterService.infoMessages("Parent Task:" +data.parentTask  + "added Successful!! Kindly close the popup to proceed with Task creation");
}

selectProject(data:any)
{
  this.projectAssociated = data;
  this.popupProject = data.project;
  this.toasterService.infoMessages("Project:" + data.project  + "linked Successfully!! Kindly close the popup to proceed with Task creation");
}

enableDateField()
{
  
  if(this.isDisabled)
  {
   
    this.addTaskForm.get('startDate').disable();
    this.addTaskForm.get('startDate').setValue("");
    this.addTaskForm.get('endDate').disable();
    this.addTaskForm.get('endDate').setValue("");
    this.addTaskForm.get('priority').disable();
  }
  else if(!this.addTaskForm.get('startDate') || this.addTaskForm.get('startDate').value =="" ||  this.addTaskForm.get('startDate').value == null){
    this.addTaskForm.get('startDate').enable();
    this.addTaskForm.get('priority').enable();
    this.addTaskForm.get('startDate').setValue(new Date());
    this.addTaskForm.get('endDate').enable();
    var tommorowString = this.generateFutureDate();
    this.addTaskForm.get('endDate').setValue(new Date(tommorowString));  
  }
  else
  { 
    this.addTaskForm.get('startDate').setValue(new Date(this.addTaskForm.get('startDate').value));
    this.addTaskForm.get('endDate').setValue(new Date(this.addTaskForm.get('endDate').value));
  
  }
}

    generateFutureDate(){
      var addDate =  new Date().getTime() +(1000*60*60*24);
      var tommorowDate = new Date(addDate);
      var tommorowString = (tommorowDate.getMonth() + 1)  + '/' + tommorowDate.getDate()  + '/' + (tommorowDate.getFullYear() ) 
      return tommorowString;
    }

    convertDateFormat(dateproject:Date){

    var month:any =  dateproject.getMonth() + 1;
    var date:any = dateproject.getDate();
    if(month < 10)
      month = '0' + month;
    if(date < 10)
      date = '0' + date;

    var tommorowString :any = dateproject.getFullYear() + '-' + month + '-' + date;
    return tommorowString;
    }    

}
