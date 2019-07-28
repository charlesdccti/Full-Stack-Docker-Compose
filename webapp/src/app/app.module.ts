import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { UserComponent } from './user/user.component';
import { AddTaskComponent } from './add-task/add-task.component';
import { ViewTaskComponent } from './view-task/view-task.component';
import { ProjectComponent } from './project/project.component';
import { UserPipe } from './pipe/user.pipe';
import { TaskPipe } from './pipe/task.pipe';
import { ProjectPipe } from './pipe/project.pipe';
import { NavigationComponent } from './navigation/navigation.component';

import {BsDatepickerModule} from 'ngx-bootstrap/datepicker';
import {HttpClientModule} from '@angular/common/http';
import {ReactiveFormsModule, FormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import { OrderUserPipe } from './pipe/order-user.pipe';
import { EditTaskComponentComponent } from './edit-task-component/edit-task-component.component';
import { DisplayPipe } from './pipe/display.pipe';
import { OrderPipe } from './pipe/order.pipe';
import { ParentPipe } from './pipe/parent.pipe';

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    AddTaskComponent,
    ViewTaskComponent,
    ProjectComponent,
    UserPipe,
    TaskPipe,
    ProjectPipe,
    NavigationComponent,
    OrderUserPipe,
    EditTaskComponentComponent,
    DisplayPipe,
    OrderPipe,
    ParentPipe
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    BsDatepickerModule.forRoot(),
    RouterModule.forRoot([
      {
        path:'addProject',
        component:ProjectComponent
      },
      {
        path:'addTask',
        component:AddTaskComponent
      },
      {
        path:'addUser',
        component:UserComponent
      },
      {
        path:'addTask/:id',
        component:ViewTaskComponent
      },
      {
        path:'viewTask',
        component:EditTaskComponentComponent
      },
      {
        path:'**',
        redirectTo: 'viewTask'
      }
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
