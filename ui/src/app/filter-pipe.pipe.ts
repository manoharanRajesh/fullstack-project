import { Pipe, PipeTransform } from '@angular/core';
import {Task} from './model/Task';
import * as moment from 'moment';


@Pipe({
  name: 'filterPipe',
  pure: false
})
export class FilterPipePipe implements PipeTransform {
  // https://long2know.com/2016/11/angular2-filter-pipes/
  transform(values: Task[],
            filterTask: Task,
            min: number,
            max: number
  ): any {

    if (!Array.isArray(values)) {
      return values;
    } else {
      let vals = values;
      if (filterTask.title) {
        vals = vals.filter(
          v => {
            if (filterTask.title && v.title.includes(filterTask.title + '')) {
              return true;
            } else {
              return false;
            }
          }
        );
      }
      if (filterTask.parentTask && filterTask.parentTask.title) {
        vals = vals.filter(
          v => {
            if (filterTask.parentTask.title && v.parentTask.title.includes(filterTask.parentTask.title + '')) {
              return true;
            } else {
              return false;
            }
          }
        );
      }

      if (min >= 0) {
        vals = vals.filter(
          v => {
            if (v.priority >= min ) {
              return true;
            } else {
              return false;
            }
          }
        );
      }

      if (max <= 5) {
        vals = vals.filter(
          v => {
            if (v.priority <= max ) {
              return true;
            } else {
              return false;
            }
          }
        );
      }

      if (filterTask && filterTask.startDate) {
        vals = vals.filter(
          v => {
            return moment(v.startDate).isSameOrAfter(filterTask.startDate);
          }
        );
      }

      if (filterTask && filterTask.endDate) {
        vals = vals.filter(
          v => {
            return moment(v.endDate).isSameOrBefore(filterTask.endDate); }
        );
      }

      return vals;
    }


  }

}
