import { Component, OnInit } from '@angular/core';
import { HttpServiceService } from 'src/app/Services/http-service.service';
import { ActivatedRoute, Params } from '@angular/router';
import { UserService } from 'src/app/Services/user-service.service';
import { WebsocketService } from 'src/app/Services/websocket.service';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss']
})
export class BoardComponent implements OnInit {

  dataGeneralBoard: any;
  cartasCurrentUser: any;
  playEnable:any;
  idJuego: any;
  currentUserId: any;
  temporizador:any;

  constructor(
    private httpService: HttpServiceService,
    private activatedRoute: ActivatedRoute,
    private userService: UserService,
    private webSocket: WebsocketService
  ){}

  ngOnInit(): void {

    let pathParams = this.activatedRoute.params;
    this.currentUserId = this.userService.getCurrentUserUid();
    this.playEnable = sessionStorage.getItem('boardOwner') == this.currentUserId;

    pathParams.subscribe( (data: Params) =>{

      this.idJuego = data['id'];

      this.httpService.obtenerTablero(data['id']).subscribe( data =>{
        console.log(data)
        this.dataGeneralBoard = data;
      });

      this.httpService.obtenerCartasCurrentUser(this.currentUserId, data['id']).subscribe( (data: Params)  => {
        this.cartasCurrentUser = data['cartas'];
      });

      this.webSocket.conection(data['id']).subscribe({
        next: (message: any) => {
          console.log(message.tiempo);
          this.temporizador = message.tiempo
        },
      });

    })

  }

  iniciarRonda(){

    console.log("Iniciando ronda: " + this.idJuego)

    this.httpService.iniciarRonda(this.idJuego).subscribe( msg => {
      console.log(msg)
    })

  }

  ponerCarta(cartaId: any){

    console.log(`Carta puesta: ${cartaId}`)

    let body = {
      "jugadorId": this.currentUserId,
      "cartaId": cartaId,
      "juegoId":this.idJuego
    }

    console.log(`Carta puesta: ${cartaId}`)

    this.httpService.ponerCartaEnTablero(body)

  }

}
