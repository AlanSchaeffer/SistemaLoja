import { Component, OnInit } from '@angular/core';
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

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

  register() {
    this.userService.register(this.user).subscribe(result => {
      if (result) {
        alert("Usuário cadastrado com sucesso!");
      } else {
        alert("Erro ao cadastrar usuário");
      }
    });
  }
}
