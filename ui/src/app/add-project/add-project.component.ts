import {ChangeDetectorRef, Component, EventEmitter, Inject, LOCALE_ID, OnInit} from '@angular/core';
import {Project} from '../model/Project';
import {UserService} from '../services/user.service';
import {User} from '../model/User';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {ProjectService} from '../services/project.service';
import * as moment from 'moment';
import {formatDate} from '@angular/common';
import {ProjectEvent} from './ProjectEvent';


@Component({
  selector: 'app-add-project',
  templateUrl: './add-project.component.html',
  styleUrls: ['./add-project.component.css']
})
export class AddProjectComponent implements OnInit {
  public mode: EventEmitter<ProjectEvent> = new EventEmitter<ProjectEvent>();
  project: Project;
  filteredPrj: Project[];
  projects: Project[];
  isDateChecked: boolean;
  btnTitle = 'Add';
  empId: string;
  users: User[];
  filterStr: string;
  userName  = '';

  constructor(private ref: ChangeDetectorRef, private userService: UserService, private projectService: ProjectService,
              config: NgbModalConfig, private modalService: NgbModal, @Inject(LOCALE_ID) private locale: string) {
    this.empId = '';
    this.project = new Project('', '' , '' , 1 , null);
    // customize default values of modals used by this component tree
    config.backdrop = 'static';
    config.keyboard = false;
    this.mode.subscribe(
      {
        next: (event: ProjectEvent) => {
          console.log(`Received message #${event.mode}: ${event.message}`);
          if (event.mode === 'RESET') {
            this.btnTitle = 'Add';
          }
          if (event.mode === 'ADD') {
            this.btnTitle = 'Add';
          }
          if (event.mode === 'UPDATE') {
            this.btnTitle = 'update';
          }
        }
      }
    );

  }


  ngOnInit() {
    this.getAllProject();
    this.getAllUsers();
  }

  ngOnDestroy() {
    this.mode.unsubscribe();
  }

   getAllProject()  {
    this.projectService.get().subscribe( v => {
        console.log(this.projects);
        this.projects = v;
        this.filteredPrj = this.projects;
        this.ref.detectChanges();
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



  onSubmit() {
    console.log(this.project);
    this.projectService.add(this.project)
      .subscribe(
        v => {
          this.getAllProject();
          this.project = new Project('', '', '', 1, null);
        }
      );
  }


  loadUsers() {
    this.userService.getUser(this.empId);

  }

  open(content) {
    this.userService.get().subscribe( v => {
        this.users = v;
        this.modalService.open(content);
        this.ref.detectChanges();
      }
    );
  }

  saveUser() {
    this.project.manager = this.users
      .filter(u => { if (u.empId === this.empId) { return u; } }).map(u => u)[0];
    if (this.modalService.hasOpenModals()) { this.modalService.dismissAll(); }
    this.userName = this.project.manager.firstName + this.project.manager.lastName;
    return true;
  }

  onSelectUser(usr: User) {
    this.empId = usr.empId;
  }


  // Sorting in ascending order by Start Date
  sortByStartDate() {
    this.filteredPrj = this.projects.sort ((a: any, b: any) =>
      new Date(a.startDate).getTime() - new Date(b.startDate).getTime()
    );
  }
  // Sorting in ascending order by End Date
  sortByEndDate() {
    this.filteredPrj = this.projects.sort ((a: any, b: any) =>
      new Date(a.endDate).getTime() - new Date(b.endDate).getTime()
    );
  }
  // Sorting in descending order by Prority
  sortByPriority() {
    this.filteredPrj = this.projects.sort ((a: any, b: any) =>
      b.priority - a.priority
    );
  }
  // Sorting in descending order by Completed (end date in desc order)
  sortByCompleted() {
    this.filteredPrj = this.projects.filter(p => this.isProjectExpired(p));
    this.filteredPrj = this.filteredPrj.sort ((a: any, b: any) =>
      new Date(b.endDate).getTime() - new Date(a.endDate).getTime()
    );
  }

  isProjectExpired(p: Project): boolean {
    return moment(p.endDate).isBefore(moment());
  }

  async resetFilter() {
    await this.getAllProject();
    this.filteredPrj = this.projects;
  }

  reset() {
    this.project = new Project('', '', '', 1, null);
    this.mode.emit(new ProjectEvent( '', 'ADD'));
  }

  update(p: Project ): void {
    this.project.title = p.title;
    this.project.startDate = p.startDate;
    this.project.endDate = p.endDate;
    this.project.priority = p.priority;
    this.project.manager = p.manager;
    this.project.id = p.id;
    this.mode.emit(new ProjectEvent( '', 'UPDATE'));
  }

  endProject(p: Project): void {
    p.endDate = formatDate(new Date(), 'yyyy-MM-dd', this.locale);
    this.projectService.update(p)
      .subscribe(
        value => {
          this.getAllProject();
          this.filteredPrj = this.projects;
        }
      );
  }


  changeChkBox(event) {
    if (event.target.checked) {
      this.isDateChecked = true;
      const today = new Date();
      const endDate = new Date().setDate(today.getDate() + 1);

      this.project.startDate = formatDate(today, 'yyyy-MM-dd', this.locale);
      this.project.endDate = formatDate(endDate, 'yyyy-MM-dd', this.locale);
    } else {
      this.isDateChecked = false;
      this.project.startDate = undefined;
      this.project.endDate = undefined;
    }
  }

}
