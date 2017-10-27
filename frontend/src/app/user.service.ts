import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { User } from './user';

@Injectable()
export class UserService {
  private loginUrl:string = "http://localhost:8080/login/auth";

  constructor(private http:Http) { }

  login(user: User){
    this.http
      .post(this.loginUrl, { email: user.email, password: user.password })
      .toPromise()
      .then(response => response.json())
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Ocorreu um erro ao efetuar login', error);
    return Promise.reject(error.message || error);
  }
}
