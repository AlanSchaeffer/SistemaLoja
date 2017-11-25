import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: User = {
    email: "",
    password: "",
    name: ""
  };
  
  constructor(private userService: UserService, private router: Router) { 
    
  }

  ngOnInit() {
  }

  login(){
    var admin: boolean = this.router.url.indexOf('admin') > -1;

    this.userService.login(this.user, admin)
      .subscribe(result => {
        if (result === true) {
          localStorage.removeItem('order');
          if (admin){
            this.router.navigate(['/admin']);
          } else {
            this.router.navigate(['/customer']);  
          }            
        }
        else {
          alert("Usuário e/ ou senha inválidos");
        }
      }, error => { alert("Ocorreu um erro na autenticação, tente novamente mais tarde."); });
  }
}
