import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddProjectComponent } from './add-project/add-project.component';
import { AddUserComponent } from './add-user/add-user.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ProjectFilterPipe } from './project-filter.pipe';
import { FilterPipePipe } from './filter-pipe.pipe';
import {TaskUpdateComponent} from './task-update/task-update.component';
import {TaskAddComponent} from './task-add/task-add.component';
import {TaskViewComponent} from './task-view/task-view.component';



@NgModule({
  declarations: [
    AppComponent,
    AddProjectComponent,
    AddUserComponent,
    ProjectFilterPipe,
    TaskViewComponent,
    TaskAddComponent,
    TaskUpdateComponent,
    FilterPipePipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    AppRoutingModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
