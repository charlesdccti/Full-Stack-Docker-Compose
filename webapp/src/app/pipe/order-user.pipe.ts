import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'orderUser'
})
export class OrderUserPipe implements PipeTransform {

  transform(items: any[], field: string): any[] {
    if (!items) return [];
    if (field != "priority"  && field != "taskCompleted") items.sort((a, b) => a[field].toLocaleLowerCase() > b[field].toLocaleLowerCase() ? 1 : -1);
    else if(field == "priority" || field == "taskCompleted")  items.sort((a, b) => a[field]> b[field] ? 1 : -1);
    else items.sort((a, b) => a > b ? 1 : -1);
    return items;
  }

}
