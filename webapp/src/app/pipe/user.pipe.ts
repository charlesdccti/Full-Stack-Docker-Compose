import { Pipe, PipeTransform } from '@angular/core';

export interface IUser {
  firstName : string;
  lastName  : string;
  employeeId: string;
}

@Pipe({
  name: 'user'
})
export class UserPipe implements PipeTransform {

  transform
  (
    users: IUser[],
    firstNameSearch ?: string,
    lastNameSearch  ?: string,
    employeeIdSearch?: string
  ): IUser[] {

    if (!users) return [];

    if (firstNameSearch)
    {
      firstNameSearch = firstNameSearch.toLocaleLowerCase();
      users = [...users.filter(user => user.firstName.toLocaleLowerCase().includes(firstNameSearch))];
    }
    if (lastNameSearch)
    {
      lastNameSearch =lastNameSearch.toLocaleLowerCase();
      users = [...users.filter(user => user.lastName.toLocaleLowerCase().includes(lastNameSearch))];
    }
    if (employeeIdSearch)
    {
      employeeIdSearch =employeeIdSearch.toLocaleLowerCase();
      users = [...users.filter(user => user.employeeId.toLocaleLowerCase().includes(employeeIdSearch))];
    }

    return users;
  }


}
