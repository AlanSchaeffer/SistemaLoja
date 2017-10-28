import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { User } from './user';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

@Injectable()
export class UserService {
  private loginUrl:string = "http://localhost:8080/services/usuario/login";

  constructor(private http:Http) { }

  login(user: User): Observable<boolean>{
    return this.http
      .post(this.loginUrl, JSON.stringify({ usernameOrEmail: user.email, password: user.password }))
      .map((response) =>
      {
        console.log(response);
        let token = response.headers.get("authorization");
        if (token) {
          localStorage.setItem('currentUser', JSON.stringify({ username: user.email, token: token }));
          return true;
        } else {
          return false;
        }
      }).catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }
}
