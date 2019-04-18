import {ChangeDetectorRef, Component, Inject, LOCALE_ID, OnInit, Output} from '@angular/core';
import {log} from 'util';
import {ActivatedRoute, Router} from '@angular/router';
import {Task} from '../model/Task';
import {DatePipe} from '@angular/common';
import {UserService} from '../services/user.service';
import {User} from '../model/User';
import {Project} from '../model/Project';
import {NgbModal, NgbModalConfig} from '@ng-bootstrap/ng-bootstrap';
import {ProjectService} from '../services/project.service';
import {TaskService} from '../services/task.service';

@Component({
  selector: 'app-task-add',
  templateUrl: './task-add.component.html',
  styleUrls: ['./task-add.component.css']
})
export class TaskAddComponent implements OnInit {
  users: User[];
  projects: Project[];
  empId: string;
  task: Task;
  project: Project;
  parentTask: Task;
  userName  = '';
  projectTitle = '';
  parentTitle = '';
  projId  = '';
  parentId  = '';
  tasks: Task[];

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
    this.empId = '';
    this.parentId = '';
    this.projId = '';
    this.task = new Task();
    this.task.priority = 1;
    this.task.title = '';
    this.task.parentTask = new Task();
    this.task.parentTask.title = '';


  }


  ngOnInit() {
    this.getAllProject();
    this.getAllUsers();
    this.getAllTask();
  }
  onSubmit() {
    this.taskService.addTask(this.task).then(
      value => {
        this.router.navigate(['./view']);
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

