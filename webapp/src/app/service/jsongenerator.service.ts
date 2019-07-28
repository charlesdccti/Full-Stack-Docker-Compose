import { Injectable } from '@angular/core';

import {HttpClient} from '@angular/common/http';
import {RequestOptions , Headers} from '@angular/http';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class JsongeneratorService {

  USER_URL:string    =  'http://localhost:8080/taskManager/userAction';
  PROJECT_URL:string =  'http://localhost:8080/taskManager/projectAction';
  TASK_URL:string    =  'http://localhost:8080/taskManager/taskAction';
  PARENT_URL:string    =  'http://localhost:8080/taskManager/parentAction';

  constructor(private httpClient:HttpClient) { }

  insertUser(data:any):Observable<any>{
    let observables = this.httpClient.post(this.USER_URL+"/addUser",data)
    return observables;
  }

  getAllUser():Observable<any>
  {
    let observables=this.httpClient.get(this.USER_URL +"/getAllUser");
    return observables;
  }

  getAllParentTask():Observable<any>
  {
    let observables=this.httpClient.get(this.PARENT_URL +"/getAllParent");
    return observables;
  }
 
  insertParent(data:any):Observable<any>{
    let observables = this.httpClient.post(this.PARENT_URL+"/addParent",data)
    return observables;
  }

  getUserById(data:any):Observable<any>{
    let observables=this.httpClient.get(this.USER_URL + '/getUserById/' + data.userId);
    return observables;
  }
 
  updateEndDate(data:any):Observable<any>
  {
    let observables= this.httpClient.put(this.USER_URL+  '/updateUser' , data);
    return observables;    
  }
  
  deleteUser(data:any):Observable<any>
  {
    let observables= this.httpClient.post(this.USER_URL+  '/deleteUser', data);
    return observables;    
  }


  insertProject(data:any):Observable<any>{
    let observables = this.httpClient.post(this.PROJECT_URL+"/addProject",data)
    return observables;
  }

  getAllProject():Observable<any>
  {
    let observables=this.httpClient.get(this.PROJECT_URL +"/getAllProject");
    return observables;
  }
 
  getProjectById(data:any):Observable<any>{
    let observables=this.httpClient.get(this.PROJECT_URL + '/getProjectById/' + data.projectId);
    return observables;
  }
 
  updateProject(data:any):Observable<any>
  {
    let observables= this.httpClient.post(this.PROJECT_URL+  '/updateProject' , data);
    return observables;    
  }
  
  deleteProject(data:any):Observable<any>
  {
    let observables= this.httpClient.post(this.PROJECT_URL+  '/deleteProject', data);
    return observables;    
  }

  insertTask(data:any):Observable<any>{
    let observables = this.httpClient.post(this.TASK_URL+"/addTask",data)
    return observables;
  }

  getAllTask():Observable<any>
  {
    let observables=this.httpClient.get(this.TASK_URL +"/getAllTask");
    return observables;
  }
 
  getTaskById(data:any):Observable<any>{
    let observables=this.httpClient.get(this.TASK_URL + '/getTaskById/' + data);
    return observables;
  }
 
  updateTask(data:any):Observable<any>
  {
    let observables= this.httpClient.post(this.TASK_URL+  '/updateTask' , data);
    return observables;    
  }
  
  deleteTask(data:any):Observable<any>
  {
    let observables= this.httpClient.post(this.TASK_URL+  '/deleteTask', data);
    return observables;    
  }

}
