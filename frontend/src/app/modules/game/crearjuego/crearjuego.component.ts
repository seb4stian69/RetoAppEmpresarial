import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../../Services/user-service.service';
import { HttpServiceService } from '../../../Services/http-service.service';
import { WebsocketService } from '../../../Services/websocket.service';
import { v4 as autoUid } from 'uuid';
import { DbFireService } from '../../../Services/db-fire.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-crearjuego',
  templateUrl: './crearjuego.component.html',
  styleUrls: ['./crearjuego.component.scss']
})

export class CrearjuegoComponent implements OnInit {

  uid:any;
  uuid:string =autoUid();
  listaUsuariosRegistrados: any;
  formInicial: any;
  jugadores: any;
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
      userCheck: new FormControl('', [Validators.required])
    })

  }

  ngOnDestroy(): void { this.webSocket.close(); }


  ngOnInit(): void {

    this.uid = this.userService.getCurrentUserUid();

    this.dbService.getUser().subscribe( users => { this.listaUsuariosRegistrados = users });

    this.webSocket.conection(this.uuid).subscribe({
      next: (message: any) => console.log(message),
      error: (error: any) => console.log(error),
      complete: () => console.log('completado'),
    });

  }

  sendEventCrearJuego(players:any[]) {

    this.httpService
      .createGameBoard({

        juegoId: this.uuid,
        jugadores: this.obtenerJugadores(players),
        jugadorPrincipalId: this.uid,

      })
    .subscribe(e => console.log(e));

    this.httpService.listarGameBoard(this.uid).subscribe()

    alert("Se enviara a la lista de juegos");

    this.router.navigate(['/game/listajuegos'])

  }

  onSubmitForm(){

    let data = this.formInicial.value
    this.sendEventCrearJuego(data.userCheck);

  }

  obtenerJugadores(jugadores: Array<any>) {

    return jugadores.reduce(
      (previous, current) => ({
        ...previous,
        [current.uid]: current.alias,
      }),
      {}
    );

  }

}
