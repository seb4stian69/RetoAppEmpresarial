import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpServiceService } from 'src/app/Services/http-service.service';
import { UserService } from 'src/app/Services/user-service.service';
import { WebsocketService } from 'src/app/Services/websocket.service';
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
    private router: Router,
    private webSocket: WebsocketService
  ){}

  ngOnInit(): void {

    this.uid = this.userService.getCurrentUserUid();

    this.webSocket.conection(this.uid).subscribe({
      next: (message: any) => {
        console.log(message)
      }
    });

    this.httpService.listarGameBoard(this.uid).subscribe(juego =>{

      this.listJuegos = juego

    })

  }

  onClickIniciar(id:string, estaIniciado: boolean, uid:any){

    if(estaIniciado){
      alert("Sera enviado al juego")
      this.router.navigate([`/game/board/${id}`])
    }else{
      this.httpService.iniciarJuego(id).subscribe()
      console.log("Iniciando juego: " + id)
      sessionStorage.setItem("boardOwner", uid)
      alert("El juego ha sido iniciado")
      this.router.navigate([`/game/board/${id}`])
    }

  }

}
