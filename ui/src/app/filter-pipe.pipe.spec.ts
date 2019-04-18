import { FilterPipePipe } from './filter-pipe.pipe';
import {async} from '@angular/core/testing';
import {Task} from './model/Task';
import * as moment from 'moment';

function createTask(title: string, p: number , d: number): Task {
  const t: Task = new Task();
  t.title = title;
  t.parentTask = null;
  t.priority = p;
  t.startDate = moment().toDate();
  t.endDate = moment().add(d, 'days').toDate();
  return t;
}

describe('FilterPipePipe', () => {
  let p ;
  beforeEach(async(() => {
    p = new FilterPipePipe();
  }));


  it('create an instance', () => {
    expect(p).toBeTruthy();
  });

  it('search is not filled then skip filter', () => {
    const  tasks: Array<Task>  = [];
    tasks.push(createTask('t1', 1, 5));
    tasks.push(createTask('t2', 1, 5));
    tasks.push(createTask('t3', 1, 5));
    tasks.push(createTask('s3', 1, 5));

    const filterTsk: Task = createTask('t', 1, 3);

    expect(p).toBeTruthy();
  });


});
