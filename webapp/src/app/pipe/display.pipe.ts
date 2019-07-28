import { Pipe, PipeTransform } from '@angular/core';
import { Project } from '../model/project';


export interface IDisplay{
project:any;
inprogress:number;
completed:number;
}


@Pipe({
  name: 'display'
})
export class DisplayPipe implements PipeTransform {

  transform
  (
    displays: IDisplay[],
    projectSearch?: string,
    priorityFromSearch?: number,
    priorityToSearch?: number,
    startDateSearch?: Date,
    endDateSearch?:Date,
  ): IDisplay[] {

    if (!displays) return [];

    if (projectSearch)
    {
      projectSearch = projectSearch.toLocaleLowerCase();
      displays = [...displays.filter(display => display.project.project.toLocaleLowerCase().includes(projectSearch))];
    }
   
    if (startDateSearch)
    {
      displays = [...displays.filter(display => display.project.startDate.includes(startDateSearch.toISOString().slice(0,10)))];
    }

    if (endDateSearch)
    {
      displays = [...displays.filter(display =>display.project.endDate.includes(endDateSearch.toISOString().slice(0,10)))];
    }

    if (priorityFromSearch)
    {
      displays = [...displays.filter(display => display.project.priority >= priorityFromSearch)];
    }

    if (priorityToSearch)
    {
      displays = [...displays.filter(display => display.project.priority <= priorityToSearch)];
    }

    return displays;
  }

}
