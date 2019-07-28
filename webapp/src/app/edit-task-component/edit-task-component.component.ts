import { Component, OnInit } from '@angular/core';
import { Task } from "../model/task";
import { ToasterService } from '../service/toaster.service';
import { JsongeneratorService } from '../service/jsongenerator.service';
import { Title} from '@angular/platform-browser';
import {FormGroup, FormControl, Validators} from "@angular/forms";
import { Project } from '../model/project';


@Component({
  selector: 'app-edit-task-component',
  templateUrl: './edit-task-component.component.html',
  styleUrls: ['./edit-task-component.component.css']
})
export class EditTaskComponentComponent implements OnInit {

  data:Task[]=[];
  popupProject:string;
  dataProject:Project[]=[];
  order:string = "startDate";

  constructor(private jsonService:JsongeneratorService, private toasterService:ToasterService, private titleService:Title){}

  ngOnInit() {
    this.loadData();
    this.titleService.setTitle('View Task');
  }

  loadData()
  {
    let observable=this.jsonService.getAllTask();
    observable.subscribe
    (
      (data:Task[])=>
      {
          this.data=data;
      },
    )
     
    let observable2=this.jsonService.getAllProject();
    observable2.subscribe
    (
      (dataProject:any)=>
      {
          this.dataProject=dataProject;
      },
    )


  }

  endTask(object:any)
  { 
      object.status ="complete";
      console.dir(object);
      this.jsonService.updateTask(object).subscribe(
      (data)=>{
                    this.toasterService.successMessages("Task : " + object.task  +" has been successfully completed");
                    this.data = [];
                    this.loadData();
              },
      (error)=>{
                    this.toasterService.deleteMessages("Error Ending Task:" + object.task + "!!! Please Try again");
                 } )
    }

selectProject(data:any)
{
  this.popupProject = data.project;
}


sortFilter(value:string)
{
  this.order =  value;
}

  
}



