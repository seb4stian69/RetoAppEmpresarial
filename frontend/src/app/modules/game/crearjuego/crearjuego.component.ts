import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService as AuthService } from '../../../Services/user-service.service';
import { HttpServiceService } from '../../../Services/http-service.service';
import { WebsocketService } from '../../../Services/websocket.service';
import { v4 as uuidv4 } from 'uuid';

@Component({
  selector: 'app-crearjuego',
  templateUrl: './crearjuego.component.html',
  styleUrls: ['./crearjuego.component.scss']
})

export class CrearjuegoComponent implements OnInit {

  uuid:string =uuidv4();

  constructor(
    private authService: AuthService,
    private router: Router,
    private httpService: HttpServiceService,
    private webSocket: WebsocketService
  ) {}
  ngOnDestroy(): void {
    this.webSocket.close();
  }

  ngOnInit(): void {
    this.webSocket.conection(this.uuid).subscribe({
      next: (message: any) => console.log(message),
      error: (error: any) => console.log(error),
      complete: () => console.log('completado'),
    });
  }
  logOut() {
    this.authService.logOut().then(() => this.router.navigate(['/']));
  }

  crearGame() {
    this.httpService
      .createGameBoard({
        "juegoId": this.uuid,
        "jugadores": {
          "player-01": 'p1',
          "player-02": 'p2'
        },
        "jugadorPrincipalId":"uid-p1"
      }).subscribe((juego) => console.log(juego));
      console.log(this.uuid)
      alert("Seras enviado al tablero");
      this.router.navigate(["/game/board"]);
  }

}
