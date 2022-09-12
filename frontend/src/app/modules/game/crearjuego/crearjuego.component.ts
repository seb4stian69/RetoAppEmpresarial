import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../../Services/user-service.service';
import { HttpServiceService } from '../../../Services/http-service.service';
import { WebsocketService } from '../../../Services/websocket.service';
import { v4 as autoUid } from 'uuid';
import { DbFireService } from '../../../Services/db-fire.service';
import { UsuarioModel } from '../../../models/Usuario.model';
import { UserDTO } from 'src/app/models/User.DTO';
import { FormGroup, FormControl } from '@angular/forms';



@Component({
  selector: 'app-crearjuego',
  templateUrl: './crearjuego.component.html',
  styleUrls: ['./crearjuego.component.scss']
})

export class CrearjuegoComponent implements OnInit {

  uid: any;
  uuid:string =autoUid();
  listUsers: any;
  formInicial: any;
  sendPlayers:Array<any> = [];

  constructor(
    private userService: UserService,
    private router: Router,
    private httpService: HttpServiceService,
    private webSocket: WebsocketService,
    private dbService: DbFireService,
  )
  {

    this.formInicial = new FormGroup({
      userCheck: new FormControl()
    })

  }

  ngOnDestroy(): void {
    this.webSocket.close();
  }


  ngOnInit(): void {

    this.uid = this.userService.getCurrentUserUid();

    this.sendPlayers.push({[this.uid]: this.userService.getCurrentUserAlias()})

    this.dbService.getUser().subscribe( users => {

      this.listUsers = users.filter( iterated => {
        return iterated.uid != this.uid;
      })

    });

    this.webSocket.conection(this.uuid).subscribe({
      next: (message: any) => console.log(message),
      error: (error: any) => console.log(error),
      complete: () => console.log('completado'),
    });

  }

  agregarJugador(alias: string, id: string){ this.sendPlayers.push( { [id]: alias } ) }

  quitarJugador(idreq: string){ this.sendPlayers.splice(this.sendPlayers.indexOf(idreq), 1) }

  logOut() { this.userService.logOut().then(() => this.router.navigate(['/'])) }

  returnObjects(arregloPlayers: Array<any>): Object{

    return arregloPlayers.join(","),{}

  }

  crearGame() {

    let players: Array<any> = [];

    this.sendPlayers.forEach(player=>{players.push(JSON.stringify(player))});

    let split = players.join(",").split(",");

    console.log(split);

    console.log({
      "juegoId":this.uuid,
      "jugadores": this.returnObjects(split),
      "jugadorPrincipalId":this.uid
    });

    // this.httpService.createGameBoard({

    //   "juegoId":this.uuid,
    //   "jugadores": this.returnObjects(split),
    //   "jugadorPrincipalId":this.uid

    // }).subscribe((juego) => console.log(juego));



    // alert("Seras enviado al tablero");

    // this.router.navigate(["/game/board"]);

  }

  checked(event: Event): boolean {
    return (event.target as HTMLInputElement).checked;
  }

}
