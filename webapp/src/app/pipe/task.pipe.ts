import { Pipe, PipeTransform } from '@angular/core';
import { ParentTask } from '../model/parent-task';
import { Project } from '../model/project';

export interface ITask {
  task: string;
  priority: number;
  startDate: string;
  endDate: string;
  project:any;
}

@Pipe({
  name: 'task'
})

export class TaskPipe implements PipeTransform {  
  transform
  (
    tasks: ITask[],
    taskSearch?: string,
    priorityFromSearch?: number,
    priorityToSearch?: number,
    startDateSearch?: Date,
    endDateSearch?:Date,
    popupProject?:string
  ): ITask[] {

    if (!tasks) return [];

    if (taskSearch)
    {
      taskSearch = taskSearch.toLocaleLowerCase();
      tasks = [...tasks.filter(task => task.task.toLocaleLowerCase().includes(taskSearch))];
    }


    if (startDateSearch)
    {
      tasks = [...tasks.filter(task => task.startDate.includes(startDateSearch.toISOString().slice(0,10)))];
    }

    if (endDateSearch)
    {
      tasks = [...tasks.filter(task => task.endDate.includes(endDateSearch.toISOString().slice(0,10)))];
    }

    if (priorityFromSearch)
    {
      tasks = [...tasks.filter(task => task.priority >= priorityFromSearch)];
    }

    if (priorityToSearch)
    {
      tasks = [...tasks.filter(task => task.priority <= priorityToSearch)];
    }
    
    if (popupProject)
    {
      popupProject = popupProject.toLocaleLowerCase();
      tasks = [...tasks.filter(task => task.project.project.toLocaleLowerCase() == popupProject)];
    } 
    

    return tasks;
  }

}
