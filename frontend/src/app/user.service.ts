import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';
import { User } from './user';
import { ServerResponse } from './server.response';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

@Injectable()
export class UserService {
  private loginUrl:string = "http://localhost:8080/services/usuario/login";
  private loginAdminUrl:string = "http://localhost:8080/services/admin/login";
  private registerUrl:string = "http://localhost:8080/services/usuario/cadastrar";

  constructor(private http:Http) { }

  login(user: User, admin: boolean): Observable<boolean>{
    return this.http
      .post(admin ? this.loginAdminUrl : this.loginUrl, { usernameOrEmail: user.email, password: user.password })
      .map((response) =>
      {        
        let token = response.headers.get("authorization");
        if (token) {
          localStorage.setItem('currentUser', JSON.stringify({ username: user.email, token: token }));
          return true;
        } else {
          return false;
        }
      }).catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  register(user: User): Observable<ServerResponse>{    
    return this.http
      .post(this.registerUrl, { email: user.email, senha: user.password, nome: user.name })
      .map((response) =>
      {
        var jsonResponse = response.json();
        var serverResponse = new ServerResponse();
        serverResponse.success = jsonResponse.success;
        serverResponse.message = jsonResponse.message;
        return serverResponse;
      }).catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  isAdmin(): boolean {
    return this.currentUser().username === "administraSys";
  }

  getHeaders(): RequestOptions {
    var options = new RequestOptions();
    options.headers = new Headers();
    options.headers.set('authorization', this.currentUser().token);
    options.headers.set('content-type', 'application/json');
    return options;
  }

  private currentUser(): any {
    if (localStorage.getItem('currentUser') == null)
      return null;

    return JSON.parse(localStorage.getItem('currentUser'));
  }
}
