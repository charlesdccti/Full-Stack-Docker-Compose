import { Pipe, PipeTransform } from '@angular/core';


export interface IProject{

  project: string;
  priority: number;
  startDate: string;
  endDate: string;
  popupProject:string;

}


@Pipe({
  name: 'project'
})

export class ProjectPipe implements PipeTransform {

  transform
  (
    projects: IProject[],
    projectSearch?: string,
    priorityFromSearch?: number,
    priorityToSearch?: number,
    startDateSearch?: Date,
    endDateSearch?:Date,
  ): IProject[] {

    if (!projects) return [];

    if (projectSearch)
    {
      projectSearch = projectSearch.toLocaleLowerCase();
      projects = [...projects.filter(project => project.project.toLocaleLowerCase().includes(projectSearch))];
    }
   
    if (startDateSearch)
    {
      projects = [...projects.filter(project => project.startDate.includes(startDateSearch.toISOString().slice(0,10)))];
    }

    if (endDateSearch)
    {
      projects = [...projects.filter(project => project.endDate.includes(endDateSearch.toISOString().slice(0,10)))];
    }

    if (priorityFromSearch)
    {
      projects = [...projects.filter(project => project.priority >= priorityFromSearch)];
    }

    if (priorityToSearch)
    {
      projects = [...projects.filter(project => project.priority <= priorityToSearch)];
    }

    return projects;
  }

}
