import { Task } from "./task";
import { UserModel } from "./user-model";

export class Project {

    constructor(public projectId:number, public project:string, public task:Task ,public user:UserModel, public startDate:Date, public endDate:Date,public priority:number){

    }

}
