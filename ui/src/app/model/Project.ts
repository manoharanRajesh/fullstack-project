import {User} from './User';

export class Project {
   id: string;
   title: string;
   startDate: string;
   endDate: string;
   priority: number;
   manager: User;


  constructor(title: string,  startDate: string, endDate: string,
              priority: number, manager: User) {
    this.title = title;
    this.startDate = startDate;
    this.endDate = endDate;
    this.priority = priority;
    this.manager = manager;
  }




}
