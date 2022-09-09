import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { UsuarioInterface } from 'src/app/models/User.interface';
import { UserService } from 'src/app/Services/user-service.service';
import { Router } from '@angular/router'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent implements OnInit {

  formInicial: FormGroup;

  constructor(private userService: UserService, private router: Router) {
    this.formInicial = new FormGroup({
      email: new FormControl(),
      password: new FormControl()
    })
  }

  ngOnInit(): void {}

  login(){
    this.userService.login(new UsuarioInterface(this.formInicial.value.email, this.formInicial.value.password))
      .then(res =>{
        console.log(res)
        this.router.navigate(['/gamecard']);
      })
      .catch(err => console.error(err))
  }

  google(){
    this.userService.loginWithGoogle()
    .then(res =>{
      console.log(res)
      this.router.navigate(['/gamecard']);
    })
    .catch(err => console.error(err))
  }

  onSubmit(){/*Void*/}

}
