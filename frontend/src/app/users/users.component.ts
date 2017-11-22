import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  user: User = {
    email: "",
    password: "",
    name: ""
  }

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
  }

  register() {
    this.userService.register(this.user).subscribe(result => {
      if (result.success === true) {
        alert("Usuário cadastrado com sucesso!");
        localStorage.removeItem('currentUser');
        this.router.navigate(['']);
      } else {
        alert("Erro ao cadastrar usuário: " + result.message);
      }
    }, error => { alert("Ocorreu um erro ao cadastrar o usuário, tente novamente mais tarde") });
  }
}
