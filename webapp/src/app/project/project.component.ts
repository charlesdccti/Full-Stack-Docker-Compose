import { Component, OnInit } from '@angular/core';

import {FormGroup, FormControl, Validators} from "@angular/forms";
import {ReactiveFormsModule, FormsModule} from "@angular/forms";
import { ToasterService } from '../service/toaster.service';
import { JsongeneratorService } from '../service/jsongenerator.service';
import { UserModel } from '../model/user-model';
import { Project } from '../model/project';
import { Displaydata } from '../model/displaydata';
import { Title} from '@angular/platform-browser';
 

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})

export class ProjectComponent implements OnInit {

  addProjectForm:FormGroup;
  data:any;
  dataUser:any;
  dataTask:any;
  buttonValue:string;
  flag:boolean = false;
  orderNormal:string="taskCompleted"; 
  popupManager:string;
  userAssociated:UserModel;
  isDisabled = true;
  displayData:Displaydata[]=[];
  innerOrder:string="startDate";
  rangeDefault=0;
  
  
  constructor(private jsonService:JsongeneratorService, private toasterService:ToasterService , private titleService:Title) { 
    
     this.buttonValue = "Add";
     this.addProjectForm=new FormGroup({
     projectId:new FormControl(''),
     project:new FormControl('',[Validators.required,Validators.pattern('[a-zA-Z0-9]{1,200}')]),
     priority: new FormControl(0,[Validators.required,Validators.pattern('[0-9]{1,2}')]),
     manager: new FormControl('',[Validators.required,Validators.pattern('[a-zA-Z0-9]{1,200}')]),
     enableDate:new FormControl(""),
     startDate:new FormControl({value:'',disabled:this.isDisabled}),
     endDate:new FormControl({value:'',disabled:this.isDisabled})
    })
    this.loadData();    
  }

  ngOnInit() {
  this.titleService.setTitle(" Add Project");
  }
  
  loadData()
  {

    let observable=this.jsonService.getAllProject();
    observable.subscribe
    (
      (data:any)=>
      {
          this.data=data;
          this.loadDisplayData();
      },
    )

    let observable2=this.jsonService.getAllUser();
    observable2.subscribe
    (
      (dataUser:UserModel[])=>
      {
          this.dataUser=dataUser;
      },
    )


    let observable3=this.jsonService.getAllTask();
    observable3.subscribe
    (
      (dataTask:any)=>
      {
          this.dataTask=dataTask;
          this.loadDisplayData();
      },
    )
  
    
  }
  
 
  loadDisplayData(){
    var  i,j;
    this.displayData=[];
    if(this.data && this.dataTask){
    for(i=0;i<this.data.length; i++)
    { 
       var completed = 0;
       var total = 0;      
       var displaydata = null;
      for(j=0;j<this.dataTask.length;j++)
      {
        if(this.dataTask[j].project &&  this.dataTask[j].project.projectId ==  this.data[i].projectId)
        {
           total++;
           if(this.dataTask[j].status == "complete")
           completed++;            
        }
       
      } 
      displaydata = new Displaydata(this.data[i],completed,total)
      this.displayData.push(displaydata); 
    }
  }
 
  }


  formHandler()
  {
    if(this.flag)
    {
      this.updateProject();
      this.flag = false;   
    }   
    else
      this.addProject();
  }

  suspendProject(data:any)
  { 
      var messageString = data.project.project;
      this.jsonService.deleteProject(data.project).subscribe(
        (data)=>{
                  this.toasterService.successMessages("Data : " + messageString  +" has been successfully deleted");
                  this.loadData();
                },
      (error)=>{
                 if(error.status = 500)
                 this.toasterService.deleteMessages("Project is Currently associated with the task .Hence cannot be deleted");
                  else
                  this.toasterService.deleteMessages("Error Deleting Project:" + messageString + "!!! Please Try again");
                } )
  
  }
   
  resetForm(){
    this.buttonValue = "Add";
    this.isDisabled = true;
    this.flag = false;   
    (<HTMLInputElement>document.getElementById("put")).value = "";
    this.addProjectForm.reset(); 
  }

  editProject(data:any)
  {
    this.toasterService.infoMessages("Please uncheck and check the Set Start Date and End Date checbox to enable editing of date fields");
    (<HTMLInputElement>document.getElementById("priority")).value = data.project.priority ;
    this.addProjectForm.get('priority').setValue(data.project.priority);
    this.isDisabled = false; 
    this.flag=true;
    this.buttonValue = "Update";
    this.addProjectForm=new FormGroup({
    projectId:new FormControl(data.project.projectId),
    project:new FormControl(data.project.project,[Validators.required,Validators.pattern('[a-zA-Z0-9]{1,200}')]),
    priority: new FormControl(data.project.priority,[Validators.required,Validators.pattern('[0-9]{1,2}')]),
    manager: new FormControl(data.project.user.employeeId,[Validators.required,Validators.pattern('[a-zA-Z0-9]{1,200}')]),
    enableDate:new FormControl(""),
    startDate:new FormControl({value:data.project.startDate,disabled:this.isDisabled}),
    endDate:new FormControl({value:data.project.endDate,disabled:this.isDisabled})
   })
   this.userAssociated = data.project.user;
   this.enableDateField();
   
}

updateProject(){
   
  var data:any = this.addProjectForm.value;
  
  if(!data.endDate || data.endDate == "" || data.endDate == null)
      data.endDate = new Date(this.generateFutureDate());

  if(!data.startDate || data.startDate == "" || data.startDate == null)
  data.endDate = new Date();


  if(data.endDate <data.startDate)
      this.toasterService.deleteMessages("Process Aborted !!!End Date before the start date");
  else{
      var projectNew = new Project(data.projectId, data.project,null, this.userAssociated, this.convertDateFormat(data.startDate), this.convertDateFormat(data.endDate),data.priority);
      this.resetForm();
      this.jsonService.updateProject(projectNew).subscribe(
        (data)=>{
          this.toasterService.successMessages("Project Details for *" + data.project  +"* has been successfully updated");
          this.loadData(); 
        },
        (error)=>{
          this.toasterService.deleteMessages(" Updating of Project failed with Error Code:", error.status);
        } )
      }
      (<HTMLInputElement>document.getElementById("priority")).value = "" ;
}

  addProject()
  {
      var data:any = this.addProjectForm.value;

      if(!data.endDate || data.endDate == "" || data.endDate == null)
          data.endDate = new Date(this.generateFutureDate());
      
      if(!data.startDate || data.startDate == "" || data.startDate == null)
      data.startDate= new Date();  

      if(data.endDate < data.startDate)
            this.toasterService.deleteMessages("Process Aborted !!!End Date before the start date");
      else{
            var projectNew = new Project(data.projectId, data.project,null, this.userAssociated, this.convertDateFormat(data.startDate), this.convertDateFormat(data.endDate),data.priority);
            this.resetForm();
            this.jsonService.insertProject(projectNew).subscribe(
                (data)=>{
                  this.toasterService.successMessages("Project Details for *" + data.project  +"* has been successfully registered");
                  this.loadData(); 
                },
                (error)=>{
                  this.toasterService.deleteMessages(" Addition of Project failed with Error Code:", error.status);
                } )
        }

  }

  sortOrderFilter(value:string)
  {
      this.innerOrder =  value;
  }

  
  setOutput(object:any)
  {
        (<HTMLInputElement>document.getElementById("put")).value = object.value;
  }

  selectManager(data:any)
  {
      this.userAssociated = data;
      this.popupManager = data.employeeId;
      this.toasterService.infoMessages("Manager:" + data.employeeId  + "added Successful!! Kindly close the popup to proceed with project creation");
  }

  toggleCheckBoxValue()
  {
      this.isDisabled = !this.isDisabled;
      this.enableDateField();
  }

  enableDateField()
  {  
      if(this.isDisabled)
      {
          this.addProjectForm.get('startDate').disable();
          this.addProjectForm.get('endDate').disable();
          this.addProjectForm.get('startDate').setValue("");
          this.addProjectForm.get('endDate').setValue("");
        }
        else if(!this.addProjectForm.get('startDate') || this.addProjectForm.get('startDate').value =="" ||  this.addProjectForm.get('startDate').value == null){
        this.addProjectForm.get('startDate').enable();
        this.addProjectForm.get('startDate').setValue(new Date());
        this.addProjectForm.get('endDate').enable();
        var tommorowString = this.generateFutureDate();
        this.addProjectForm.get('endDate').setValue(new Date(tommorowString));  
      }
      else
      { 
        this.addProjectForm.get('startDate').setValue(new Date(this.addProjectForm.get('startDate').value));
        this.addProjectForm.get('endDate').setValue(new Date(this.addProjectForm.get('endDate').value));
      
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