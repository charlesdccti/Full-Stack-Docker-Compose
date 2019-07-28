
  import { Component, OnInit } from '@angular/core';
  import { Task } from "../model/task";
  import {FormGroup, FormControl, Validators} from "@angular/forms";
  import {ReactiveFormsModule, FormsModule} from "@angular/forms";
  import { ToasterService } from '../service/toaster.service';
  import { JsongeneratorService } from '../service/jsongenerator.service';
  import { Title} from '@angular/platform-browser';
  import { ParentTask } from '../model/parent-task';
  import {switchMap,tap} from 'rxjs/operators';
  import {ActivatedRoute, ParamMap} from "@angular/router";
import { Project } from '../model/project';

@Component({
  selector: 'app-view-task',
  templateUrl: './view-task.component.html',
  styleUrls: ['./view-task.component.css']
})

export class ViewTaskComponent implements OnInit {
    
    updateTaskForm:FormGroup;
    data:any;
    flag:boolean = false;
    order:string; 
    popupManager:string;
    popupProject:string;
    isDisabled = false;
    parentTaskAssociated:ParentTask;
    parentTask:string;
    dataTask:any=[];
    task:string; 
    project:Project;

    constructor(private activatedRoute:ActivatedRoute,private jsonService:JsongeneratorService, private toasterService:ToasterService , private titleService:Title) {
      
      let observable1=this.jsonService.getAllParentTask();
      observable1.subscribe
      (
        (data:any)=>
        {
            this.dataTask=data;
        },
      )

      this.updateTaskForm=new FormGroup({
        taskId:new FormControl(""),
        project:new FormControl({value:"",disabled:true},[Validators.required,Validators.pattern('[a-zA-Z0-9]{1,200}')]),
        task:new FormControl({value:"",disabled:true},[Validators.required,Validators.pattern('[a-zA-Z0-9]{1,200}')]),
        priority: new FormControl({value:0,disabled:this.isDisabled},[Validators.required,Validators.pattern('[0-9]{1,2}')]),
        user: new FormControl("",[Validators.required,Validators.pattern('[a-zA-Z0-9]{1,200}')]),
        enableDate:new FormControl(""),
        parentTask:new FormControl(""),
        startDate:new FormControl({value:new Date(""),disabled:this.isDisabled}),
        endDate:new FormControl({value:new Date(""),disabled:this.isDisabled})
       })

      this.activatedRoute.paramMap.pipe(
        switchMap((params: ParamMap) =>
              this.jsonService.getTaskById(params.get('id')))
       ).subscribe((data:any)=>{ 
      this.task = data.task;
      this.project= data.project;      
      this.loadData(data);    
       }
       );
    }
  
    ngOnInit() {
    this.titleService.setTitle("Update Task");
    }
  
  initializeForm(data)  
  {
       if(data){
        this.toasterService.warningMessages("Clicking on Reset will revert back to original value");
       this.updateTaskForm=new FormGroup({
       taskId:new FormControl(data.taskId),
       project:new FormControl({value:data.project.project,disabled:true },[Validators.required,Validators.pattern('[a-zA-Z0-9]{1,200}')]),
       task:new FormControl({value:data.task,disabled:true},[Validators.required,Validators.pattern('[a-zA-Z0-9]{1,200}')]),
       priority: new FormControl({value:0,disabled:this.isDisabled},[Validators.required,Validators.pattern('[0-9]{1,2}')]),
       user: new FormControl(data.user.employeeId,[Validators.required,Validators.pattern('[a-zA-Z0-9]{1,200}')]),
       enableDate:new FormControl(""),
       parentTask:new FormControl(data.parentTask.parentTask),
       startDate:new FormControl({value:new Date(data.startDate),disabled:this.isDisabled}),
       endDate:new FormControl({value:new Date(data.endDate),disabled:this.isDisabled})
      })
    }
  
  }
  
    loadData(data)
    {
      this.isDisabled=false;
      this.data=data;
       this.initializeForm(data);
       this.parentTaskAssociated = data.parentTask;
            
    }
    
  
    formHandler(){
         this.updateTask();
        }
  
   resetForm(){
      var data = this.data;
      this.initializeForm(data); 
      (<HTMLInputElement>document.getElementById("put")).value = "0";
      this.isDisabled=  false;
       this.enableDateField();
    }
    
  updateTask()
  { 
    var data:any = this.updateTaskForm.value;
    var ind:any ="";
    var indicator:boolean = true; 

    if(!this.parentTaskAssociated ||  this.parentTaskAssociated  == null)
        this.parentTaskAssociated = new ParentTask( ind, "This Task has no parent");
    
      if(this.isDisabled)
      {
          this.jsonService.insertParent(this.parentTaskAssociated).subscribe(
            (response)=>{
              this.toasterService.deleteMessages("Parent Task:" + this.parentTaskAssociated.parentTask+ "Added Successfully");
            },
            (error) => {
              this.toasterService.deleteMessages("Addition of Parent Task failed");
            }
            );

          indicator = false;
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
          this.toasterService.deleteMessages(" Updation of Task Aborted!! Start Date cannot be greater than End date");           
        }

    if(indicator){

    if(typeof data.endDate == "string") 
    data.endDate = new Date(data.endDate);

    var taskNew = new Task(data.taskId, this.task, data.priority, "Started" , this.data.user, this.parentTaskAssociated, this.project, this.convertDateFormat(data.startDate),this.convertDateFormat(data.endDate));
    this.jsonService.updateTask(taskNew).subscribe(
        (data)=>{
          this.toasterService.successMessages("Task Details for *" + data.task  +"* has been successfully registered");
          this.loadData(data); 
        },
        (error)=>{
          this.toasterService.deleteMessages(" Addition of Task failed with Error Code:", error.status);
        } ) }
  
  }
  
  setOutput(object:any)
  {
       (<HTMLInputElement>document.getElementById("put")).value = object.value;
  }
  
  
  selectTask(data:any)
  {
    this.parentTaskAssociated = data;
    this.parentTask = data.parentTask;
    this.toasterService.infoMessages("Parent Task:" +data.parentTask  + "added Successful!! Kindly close the popup to proceed with Task creation");
  }
  
  toggleCheckBoxValue()
  {
      this.isDisabled = !this.isDisabled;
      this.enableDateField();
  }

  enableDateField()
  {
    
    if(!this.isDisabled)
    {
      this.updateTaskForm.get('priority').enable();
      this.updateTaskForm.get('startDate').enable();
      this.updateTaskForm.get('startDate').setValue(new Date());
      this.updateTaskForm.get('endDate').enable();
      var addDate =  new Date().getTime() +(1000*60*60*24);
      var tommorowDate = new Date(addDate);
      var tommorowString = (tommorowDate.getMonth() + 1)  + '/' + tommorowDate.getDate()  + '/' + (tommorowDate.getFullYear() ) 
      this.updateTaskForm.get('endDate').setValue(new Date(tommorowString));
    }
    else
    {
      this.updateTaskForm.get('startDate').disable();
      this.updateTaskForm.get('priority').disable();
      this.updateTaskForm.get('endDate').disable();
      this.updateTaskForm.get('startDate').setValue("");
      this.updateTaskForm.get('endDate').setValue("");
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
