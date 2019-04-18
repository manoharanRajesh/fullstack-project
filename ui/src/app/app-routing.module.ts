import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AddProjectComponent} from './add-project/add-project.component';
import {AddUserComponent} from './add-user/add-user.component';
import {TaskViewComponent} from './task-view/task-view.component';
import {TaskUpdateComponent} from './task-update/task-update.component';
import {TaskAddComponent} from './task-add/task-add.component';


const routes: Routes = [
  { path: 'view', component: TaskViewComponent },
  { path: 'add', component: TaskAddComponent },
  { path: 'update', component: TaskUpdateComponent },
  { path: 'update/:id', component: TaskUpdateComponent},
  { path: 'project', component: AddProjectComponent },
  { path: 'user', component: AddUserComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
