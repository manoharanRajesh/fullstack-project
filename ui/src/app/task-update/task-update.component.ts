import {ChangeDetectorRef, Component, Inject, LOCALE_ID, OnInit} from '@angular/core';
import {Task} from '../model/Task';
import {ActivatedRoute, Router} from '@angular/router';
import {log} from 'util';
import {TaskService} from '../services/task.service';
import {DatePipe} from '@angular/common';
import {Project} from '../model/Project';
import {User} from '../model/User';
import {UserService} from '../services/user.service';
import {ProjectService} from '../services/project.service';
import {NgbModal, NgbModalConfig} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-task-update',
  templateUrl: './task-update.component.html',
  styleUrls: ['./task-update.component.css']
})
export class TaskUpdateComponent implements OnInit {
  task: Task;
  user: User;
  users: User[];
  projects: Project[];
  tasks: Task[];
  empId: string;
  project: Project;
  parentTask: Task;
  userName  = '';
  projectTitle = '';
  parentTitle = '';
  projId  = '';
  parentId  = '';



  constructor(
    private ref: ChangeDetectorRef,
    private route: ActivatedRoute,
    private router: Router,
    private taskService: TaskService,
    private userService: UserService,
    private projectService: ProjectService,
    @Inject(LOCALE_ID) private locale: string,
    private config: NgbModalConfig,
    private modalService: NgbModal
  ) {
    // customize default values of modals used by this component tree
    config.backdrop = 'static';
    config.keyboard = false;
  }

  ngOnInit() {
    this.getAllProject();
    this.getAllUsers();
    this.taskService.getTask(this.route.snapshot.paramMap.get('id')).subscribe(
      v => {
        this.task = v;
        this.parentTask = this.task.parentTask;
        this.project = this.task.project;
        this.user = this.task.user;
        this.empId = this.user.empId;
        this.projId = this.project.id;
        this.userName = this.user.firstName + '' + this.user.lastName;
        this.parentTitle = this.parentTask && this.parentTask.title ? this.parentTask.title : '' ;
        this.projectTitle = this.project.title;
      }
    );

  }

  getAllUsers()  {
    this.userService.get().subscribe( v => {
        this.users = v;
        this.ref.detectChanges();
      }
    );
  }



  getAllTask()  {
    this.taskService.get().subscribe( v => {
        console.log(this.tasks);
        this.tasks = v;
        this.ref.detectChanges();
      }
    );
  }


  getAllProject()  {
    this.projectService.get().subscribe( v => {
        console.log(this.projects);
        this.projects = v;
        this.ref.detectChanges();
      }
    );
  }

  onSubmit() {
    this.taskService.update(this.task)
      .subscribe(
       value => { this.router.navigate(['./view']);
       }
      );
  }

  openProject(content) {
    this.projectService.get().subscribe( p => {
        this.projects = p;
        this.modalService.open(content);
        this.ref.detectChanges();
      }
    );
  }

  saveProject() {
    this.task.project = this.projects
      .filter(p => { if (p.id === this.projId) { return p; } }).map(p => p)[0];
    if (this.modalService.hasOpenModals()) { this.modalService.dismissAll(); }
    this.projectTitle = this.task.project.title;
    return true;
  }


  openUser(content) {
    this.userService.get().subscribe( v => {
        this.users = v;
        this.modalService.open(content);
        this.ref.detectChanges();
      }
    );
  }

  saveUser() {
    this.task.user = this.users
      .filter(u => { if (u.empId === this.empId) { return u; } }).map(u => u)[0];
    if (this.modalService.hasOpenModals()) { this.modalService.dismissAll(); }
    this.userName = this.task.user.firstName + this.task.user.lastName;
    return true;
  }



  openParentTask(content) {
    this.taskService.get().subscribe( v => {
        this.tasks = v;
        this.modalService.open(content);
        this.ref.detectChanges();
      }
    );
  }

  saveParentTask() {
    this.task.parentTask = this.tasks
      .filter(u => { if (u.id === this.parentId) { return u; } }).map(u => u)[0];
    if (this.modalService.hasOpenModals()) { this.modalService.dismissAll(); }
    this.parentTitle = this.task.parentTask.title;
    return true;
  }

}
