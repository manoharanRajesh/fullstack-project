import { Injectable } from '@angular/core';
import {User} from '../model/User';
import * as moment from 'moment';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  http: HttpClient;
  endpoint = 'http://localhost:8080/api/users';

  constructor(http: HttpClient ) {
    this.http = http;
  }

  get(): Observable<User[]> {
    return this.http.get<User[]>(this.endpoint);
  }

  getUser(uid: string):  Observable<User>  {
    return this.http.get<User>(this.endpoint + '' + uid);
  }

  add(usr: User): Observable<any> {
    usr.firstName = usr.firstName.trim();
    usr.lastName = usr.lastName.trim();
    if (usr.empId !== null) {
      return this.http.post(this.endpoint,usr)
    } else {
      usr.empId = moment().valueOf() + '';
      return this.http.post(this.endpoint,usr);
    }
  }

  delete(usr: User): Observable<any>  {
    return this.http.post(this.endpoint+'/delete',usr.empId);
  }
}
