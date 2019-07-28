import { ParentTask } from "./parent-task";
import { Project } from "./project";
import { UserModel } from "./user-model";

export class Task {

    constructor(public taskId:number, public task:string,public priority:number, public status:String,public user:UserModel, public parentTask:ParentTask, public project:Project , public startDate:Date, public endDate:Date){

    }


}
