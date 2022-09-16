import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UsuarioModel } from 'src/app/models/Usuario.model';
import { Firestore } from 'firebase/firestore';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { UserService } from 'src/app/Services/user-service.service';
import { DbFireService } from 'src/app/Services/db-fire.service';
import { UserDTO } from 'src/app/models/User.DTO';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})

export class HomeComponent implements OnInit {

  email: string = '';
  user: any;
  listUser: UsuarioModel[] = [];

  constructor(private router: Router, private userService: UserService, private formBuild: FormBuilder, private firestore: DbFireService) { /*Void*/ }

  ngOnInit(): void {

    this.user = this.userService.getDataUser().currentUser;
    console.log(this.user);
    this.savePlayer();

    this.getAllUser();

  }

  goToCrearJuego(){
    this.router.navigate(['/game/crear'])
  }

  goToListarJuegos(){
    this.router.navigate(['/game/listajuegos'])
  }

  goToHistorial(){
    this.router.navigate(['/game/historial'])
  }

  savePlayer(): void {

    const user: UserDTO ={
      uid: this.user.uid,
      alias: this.user.displayName,
      email: this.user.email
    };

    this.firestore.saveUser(user);

  }

  getAllUser() {
    this.firestore.getUser().subscribe((users) => {
      this.listUser = users;
      console.log(users);
    });
  }

  logout(){
    alert("Saldras de la app");
    this.userService.logOut();
    this.router.navigate([""])
  }

}
