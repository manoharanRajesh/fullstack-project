import {User} from './User';
import {Project} from './Project';

export class Task {
   id: string;
   title: string;
   priority: number;
   startDate: Date;
   endDate: Date;
   summary: string;
   parentTask: Task;
   user: User;
   project: Project;

}
