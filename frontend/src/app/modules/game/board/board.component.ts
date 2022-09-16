import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../../Services/user-service.service';
import { JuegoServiceService } from '../../../Services/juego.service';
import { WebsocketService } from '../../../Services/websocket.service';
import firebase from 'firebase/compat';
import { Carta } from '../../../models/Shared/commands/TableroModel';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss']
})

export class BoardComponent implements OnInit  {

  //+ ------------------ Atributos casteados en tablero ------------------ +\\

  rondaActual: number = 0;
  playEnable:any;
  temporizador: number = 0;
  cantidadJugadores: number = 0;
  idJuego: string = "";
  mazoJugadorActual: Carta[] = [];
  cartasApostadas: Carta[] = [];

  //+ ------------------ Atributos que controlan el juego ------------------ +\\

  currentUserId: any;
  userId: string = "";
  cartaPuesta: string[] = []
  jugadoresVivosRonda: number = 0;
  ganadorRondaActual: string = "";
  ganador:boolean = false;
  ganadorAlias:string = "";
  rondaIniciada:boolean = false;

  //+ ------------------ Constructor de clase ------------------ +\\

  constructor(
    public servicioJuego: JuegoServiceService,
    public jugadorAutenticado: UserService,
    public websocket: WebsocketService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  //+ ------------------ Al iniciar ------------------ +\\

  ngOnInit():void{

    this.currentUserId = this.jugadorAutenticado.getCurrentUserUid();
    this.playEnable = sessionStorage.getItem('boardOwner') == this.currentUserId;

    this.route.params.subscribe((params) => {
      this.idJuego = params['id'];
      console.log(this.idJuego)
      this.userId = "" + this.jugadorAutenticado.getCurrentUserUid();
      this.getMazo();
      this.getTablero()
    })

    this.websocket.conection(this.idJuego).subscribe({

        next: (event:any) => {

          if(event.type != 'marvelgame.TiempoCambiadoDelTablero'){
            console.log(event)
          }

          if (event.type === 'marvelgame.TiempoCambiadoDelTablero') {
            this.temporizador = event.tiempo;
          }

          if(event.type === 'marvelgame.RondaIniciada'){
            this.rondaIniciada = true;
            this.temporizador = event.tiempo;
            this.rondaActual = event.ronda.numero;
            this.mazoJugadorActual = this.mazoJugadorActual;
          }

          if(event.type === 'marvelgame.CartaPuestaEnTablero'){
            this.cartasApostadas.push({
              cartaId: event.carta.cartaMaeestraID.uuid,
              poder: event.carta.poder,
              estaOculta: event.carta.estaOculta,
              estaHabilitada: event.carta,
              url:event.carta.url
            });
          }

          if (event.type === 'marvelgame.CartaQuitadaDelMazo'){
            this.mazoJugadorActual = this.mazoJugadorActual
            .filter((item) =>item.cartaId !== event.carta.cartaMaeestraID.uuid)
          }

          if (event.type === 'marvelgame.RondaCreada') {
            this.temporizador = event.tiempo;
            this.cantidadJugadores = event.ronda.jugadores.length
            this.rondaActual=event.ronda.numero;
          }

          if(event.type === 'marvelgame.JuegoFinalizado') {

            this.ganadorAlias = "Ganador: " + event.alias;
            this.ganador = true;
            this.ganadorRondaActual=event.alias;

            alert(this.ganadorAlias)

            setTimeout(() => {
              this.router.navigate(['/game']);
            },600);

          }

          if(event.type === 'marvelgame.RondaTerminada'){
            this.cartasApostadas = [];
            console.log("Se acabo la ronda");
          }

          if(event.type === 'marvelgame.CartasAsignadasAJugador'){

            if(event.ganadorId.uuid === this.userId){
              event.cartasApuesta.forEach((carta: any) => {
                this.mazoJugadorActual.push({
                  cartaId: carta.cartaMaeestraID.uuid,
                  poder: carta.poder,
                  estaOculta: carta.estaOculta,
                  estaHabilitada: carta.estaHabilitada,
                  url: carta.url
                });
              });
              alert("Eres el ganador de esta ronda")

            }else{
              alert("Perdiste la ronda")
            }
      }}

    })

  }

  //+ ------------------ Al finalizar ------------------ +\\

  ngOnDestroy(): void {
    this.websocket.close();
  }

  //+ ------------------ Metodos de clase ------------------ +\\

  getTablero(){
    this.servicioJuego.getTablero(this.idJuego).subscribe((event)=>{
      this.temporizador = event.tiempo;
      this.jugadoresVivosRonda = event.tablero.jugadores.length;
      this.cantidadJugadores = event.tablero.jugadores.length;
      this.rondaActual = event.ronda.numero;
    })
  }

  getMazo() {
    this.servicioJuego.getMiMazo(this.userId, this.idJuego).subscribe((element: any) => {
      this.mazoJugadorActual = element.cartas
      console.log(this.mazoJugadorActual)
    })
  };


  limpiarTablero(){
    this.cartasApostadas.length-=this.cartasApostadas.length
  }

  iniciarRonda(){
    this.websocket.conection(this.idJuego).subscribe(data => console.log(data));
    this.servicioJuego.iniciarRonda({
      juegoId: this.idJuego,

    }).subscribe();

  }

  apostarCarta(cardId:string){
    this.servicioJuego.ponerCartaEnTablero({
      juegoId:this.idJuego,
      cartaId:cardId,
      jugadorId: this.userId
    }).subscribe(e=>console.log(e))
  }

  //+ ------------------ Metodos ... ------------------ +\\


}
