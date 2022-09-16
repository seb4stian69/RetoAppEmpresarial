import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService as AuthService } from '../../../Services/user-service.service';
import { JuegoServiceService } from '../../../Services/juego.service';
import { WebsocketService } from '../../../Services/websocket.service';
import firebase from 'firebase/compat';
import { Carta } from '../../../models/Shared/commands/TableroModel';
// import swal from'sweetalert2';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss']
})

export class BoardComponent implements OnInit  {

  // variables
  juegoId: string = "";
  uid: string = "";
  tiempo: number = 0;
  playEnable:any;
  jugadoresRonda: number = 0;
  jugadoresTablero: number = 0;
  numeroRonda: number = 0;
  roundStarted:boolean = false;
  cartasDelJugador: Carta[] = [];
  cartasDelTablero: Carta[] = [];
  currentUserId: any;
  ganadorRonda: string = "";
  cartasJugadorTablero: string[] = []
  ganadorAlias:string = "";
  ganador:boolean = false;

  // constructor
  constructor(
    public juegoService$: JuegoServiceService,
    public authService: AuthService,
    public ws: WebsocketService,
    private route: ActivatedRoute,
    private router: Router
  ) { }



  ngOnInit():void{

    this.currentUserId = this.authService.getCurrentUserUid();
    this.playEnable = sessionStorage.getItem('boardOwner') == this.currentUserId;

    this.route.params.subscribe((params) => {
      this.juegoId = params['id'];
      console.log(this.juegoId)
      this.uid = "" + this.authService.getCurrentUserUid();
      this.getMazo();
      this.getTablero()
    })

    this.ws.conection(this.juegoId).subscribe({
        next: (event:any) => {

          if(event.type != 'marvelgame.TiempoCambiadoDelTablero'){
            console.log(event)
          }

          if (event.type === 'marvelgame.TiempoCambiadoDelTablero') {
            this.tiempo = event.tiempo;
          }

          if(event.type === 'marvelgame.RondaIniciada'){
            this.roundStarted = true;
            this.tiempo = event.tiempo;
            this.numeroRonda=event.ronda.numero;
            this.cartasDelJugador=this.cartasDelJugador;
          }

          if(event.type === 'marvelgame.CartaPuestaEnTablero'){
            this.cartasDelTablero.push({
              cartaId: event.carta.cartaMaeestraID.uuid,
              poder: event.carta.poder,
              estaOculta: event.carta.estaOculta,
              estaHabilitada: event.carta,
              url:event.carta.url
            });
          }

          if (event.type === 'marvelgame.CartaQuitadaDelMazo'){
            this.cartasDelJugador = this.cartasDelJugador
            .filter((item) =>item.cartaId !== event.carta.cartaMaeestraID.uuid)
          }

          if (event.type === 'marvelgame.RondaCreada') {
            this.tiempo = event.tiempo;
            this.jugadoresRonda = event.ronda.jugadores.length
            this.numeroRonda=event.ronda.numero;
          }

          if(event.type === 'marvelgame.JuegoFinalizado') {

            this.ganadorAlias = "Ganador:" + event.alias;
            this.ganador = true;
            this.ganadorRonda=event.alias;

            alert(this.ganadorAlias)

            setTimeout(() => {
              this.router.navigate(['/game']);
            },600);

          }

          if(event.type === 'marvelgame.RondaTerminada'){
            this.cartasDelTablero = [];
            console.log("Se acabo la ronda");
          }

          if(event.type === 'marvelgame.CartasAsignadasAJugador'){

            if(event.ganadorId.uuid === this.uid){
              event.cartasApuesta.forEach((carta: any) => {
                this.cartasDelJugador.push({
                  cartaId: carta.cartaMaeestraID.uuid,
                  poder: carta.poder,
                  estaOculta: carta.estaOculta,
                  estaHabilitada: carta.estaHabilitada,
                  url: carta.url
                });
              });
              alert("Ganaste la ronda!")

            }else{

              alert("Perdiste la ronda :(")

          }
        }

      }

    })

  }

  ngOnDestroy(): void {
    this.ws.close();
  }

  getTablero(){
    this.juegoService$.getTablero(this.juegoId).subscribe((event)=>{
      this.tiempo = event.tiempo;
      this.jugadoresRonda = event.tablero.jugadores.length;
      this.jugadoresTablero = event.tablero.jugadores.length;
      this.numeroRonda = event.ronda.numero;
    })
  }

  getMazo() {
    this.juegoService$.getMiMazo(this.uid, this.juegoId).subscribe((element: any) => {
      this.cartasDelJugador = element.cartas
      console.log(this.cartasDelJugador)
    })
  };


  limpiarTablero(){
    this.cartasDelTablero.length-=this.cartasDelTablero.length
  }

  iniciarRonda(){
    this.ws.conection(this.juegoId).subscribe(data => console.log(data));
    this.juegoService$.iniciarRonda({
      juegoId: this.juegoId,

    }).subscribe();

  }

  ponerCarta(cardId:string){
    this.juegoService$.ponerCartaEnTablero({
      juegoId:this.juegoId,
      cartaId:cardId,
      jugadorId: this.uid
    }).subscribe(e=>console.log(e))
  }

}
