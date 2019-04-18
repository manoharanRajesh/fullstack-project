import { ProjectFilterPipe } from './project-filter.pipe';
import {async, TestBed} from '@angular/core/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {AppComponent} from './app.component';
import {Project} from './model/Project';

describe('ProjectFilterPipe', () => {
  let pipe: ProjectFilterPipe;

  beforeEach(async(() => {
    pipe = new ProjectFilterPipe();
  }));


  it('create an instance', () => {
    expect(pipe).toBeTruthy();
  });

  it('filter by title', () => {
    let projs: Array<Project> = [];
    projs.push(new Project('t1', '', '', 1, null));
    projs.push(new Project('t3', '', '', 1, null));
    projs.push(new Project('s1', '', '', 1, null));
    projs.push(new Project('zs', '', '', 1, null));
    expect(pipe.transform(projs, 't').length).toEqual(2);
    expect(pipe.transform(projs, 's').length).toEqual(1);
  });

});
