import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpServiceService } from 'src/app/Services/http-service.service';
import { UserService } from 'src/app/Services/user-service.service';
import { v4 as uuidv4 } from 'uuid';


@Component({
  selector: 'app-listajuegos',
  templateUrl: './listajuegos.component.html',
  styleUrls: ['./listajuegos.component.scss']
})
export class ListajuegosComponent implements OnInit {

    uuid:string =uuidv4();
    uid:any;
    listJuegos:any = [];

  constructor(
    private httpService: HttpServiceService,
    private userService: UserService,
    private router: Router
  ){}

  ngOnInit(): void {

    this.uid = this.userService.getCurrentUserUid();

    this.httpService.listarGameBoard(this.uid).subscribe(juego =>{

      this.listJuegos = juego
      console.log(juego)

    })

  }

  onClickIniciar(id:string, estaIniciado: boolean, uid:any){

    if(estaIniciado){
      this.router.navigate(['/game/board'])
    }else{
      this.httpService.iniciarJuego(id).subscribe()
      console.log("Iniciando juego: " + id)
      sessionStorage.setItem("boardOwner", uid)
      alert("El juego ha sido iniciado")
      this.router.navigate([`/game/board/${id}`])
    }

  }

}
