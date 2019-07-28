import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'order'
})
export class OrderPipe implements PipeTransform {
  
  transform(items: any[], field: string): any[] {
    if (!items) return [];
    if (field != "taskCompleted") items.sort((a, b) => a[field] > b[field] ? 1 : -1);
    else if(field != "taskCompleted")  items.sort((a, b) => a.project[field]> b.project[field] ? 1 : -1);
    else items.sort((a, b) => a > b ? 1 : -1);
    return items;
  }

}
