import { Component, OnInit } from '@angular/core';
import { Title} from '@angular/platform-browser';
import {FormGroup, FormControl, Validators} from "@angular/forms";
import {ReactiveFormsModule, FormsModule} from "@angular/forms";
import { ToasterService } from '../service/toaster.service';
import { JsongeneratorService } from '../service/jsongenerator.service';
import { UserModel } from '../model/user-model';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  addUserForm:FormGroup;
  data:any;
  buttonValue:string;
  flag:boolean = false;
  order:string ="firstName"; 

  constructor(private jsonService:JsongeneratorService, private toasterService:ToasterService, private titleService:Title) { 
    
    this.buttonValue = "Add";

    this.addUserForm=new FormGroup({
      userId:new FormControl(''),
      firstName:new FormControl('',[Validators.required,Validators.pattern('[a-zA-Z0-9]{1,200}')]),
      lastName: new FormControl('',[Validators.required,Validators.pattern('[a-zA-Z0-9]{1,200}')]),
      employeeId:new FormControl('',[Validators.required,Validators.pattern('[a-zA-Z0-9]{1,200}')])
     
    })
    this.loadData();    
  }

  ngOnInit() {
      this.titleService.setTitle('Add User');
  }
  
  loadData()
  {
    let observable=this.jsonService.getAllUser();
    observable.subscribe
    (
      (data:UserModel[])=>
      {
          this.data=data;
      },
    )
    
  }
  

  formHandler(){

    if(this.flag)
    {
      this.updateUser();
      this.flag = false;   
      }   
      else
       this.addUser();
    }

    deleteUser(data:any)
    { 
      this.jsonService.deleteUser(data).subscribe(
        (data)=>{
                  this.toasterService.successMessages("User : " + data.employeeId  +" has been successfully deleted");
                  this.loadData();
                },
      (error)=>{
                    if(error.status = 500)
                    this.toasterService.deleteMessages("User is Currently associated with the Project or Task .Hence cannot be deleted");
                  else
                  this.toasterService.deleteMessages("Error Deleting Task:" + data.employeeId + "!!! Please Try again");
                } )
  
    }
   
  resetForm(){
    this.addUserForm.reset(); 
    this.buttonValue = "Add";
  }

  editUser(data:any)
  {
    this.flag=true;
    this.buttonValue = "Update";
    this.addUserForm=new FormGroup({
    userId:new FormControl(data.userId),
    firstName:new FormControl(data.firstName,[Validators.required,Validators.pattern('[a-zA-Z0-9]{1,200}')]),
    lastName: new FormControl(data.lastName,[Validators.required,Validators.pattern('[a-zA-Z0-9]{1,200}')]),
    employeeId:new FormControl(data.employeeId,[Validators.required,Validators.pattern('[a-zA-Z0-9]{1,200}')])   
  })
  
}

updateUser(){
   
  var data:any = this.addUserForm.value;
  this.resetForm();
  this.jsonService.insertUser(data).subscribe(
    (data)=>{
      this.toasterService.successMessages("User Details for *" + data.employeeId  +"* has been successfully updated");
      this.loadData(); 
    },
    (error)=>{
      this.toasterService.deleteMessages(" Updating of User failed with Error Code:", error.status);
    } )


}

addUser()
{
  var data:any = this.addUserForm.value;
    this.resetForm();
    this.jsonService.insertUser(data).subscribe(
      (data)=>{
        this.toasterService.successMessages("User Details for *" + data.employeeId  +"* has been successfully registered");
        this.loadData(); 
      },
      (error)=>{
        this.toasterService.deleteMessages(" Addition of User failed with Error Code:", error.status);
      } )

}

sortFilter(value:string)
{
  this.order =  value;
}

}