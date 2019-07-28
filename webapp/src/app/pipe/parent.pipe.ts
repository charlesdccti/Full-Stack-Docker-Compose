import { Pipe, PipeTransform } from '@angular/core';


export interface IParent{

  parentTaskId: number;
  parentTask:string;

}

@Pipe({
  name: 'parent'
})
export class ParentPipe implements PipeTransform {

  transform
  (
    parents:IParent[],
    parentId?: number,
    parentTaskSearch?: string,

  ): IParent[] {
    
    if (!parents) return [];

    if (parentId)
    {
      parents = [...parents.filter(parent => parent.parentTaskId == parentId)];
    }

    if (parentTaskSearch)
    {
      parentTaskSearch = parentTaskSearch.toLocaleLowerCase();
      parents = [...parents.filter(parent => parent.parentTask.toLocaleLowerCase().includes(parentTaskSearch))];
    }

    return parents;
  }

}
