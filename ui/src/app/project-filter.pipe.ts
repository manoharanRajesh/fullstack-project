import { Pipe, PipeTransform } from '@angular/core';
import {Project} from './model/Project';

@Pipe({
  name: 'projectFilter'
})
export class ProjectFilterPipe implements PipeTransform {

  transform(projects: Array<Project>, filterByProject?: string) {
    if (filterByProject) {
      projects = projects.filter(project =>
        project.title.toLowerCase().startsWith(filterByProject.toLowerCase()));
    }
    return projects;
  }


}
