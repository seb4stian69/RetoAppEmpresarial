import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { JuegoModel } from '../models/Board/juego.model';
import { Observable } from 'rxjs';
import { TableroModel } from '../models/Shared/commands/TableroModel';
import { IniciarJuegoCommand } from '../models/Shared/commands/IniciarJuegoCommands';
import { CrearRondaCommand } from '../models/Shared/commands/CrearRondaCommands';
import { PonerCartaCommand } from '../models/Shared/commands/ponerCartaEnTablero';

@Injectable({
  providedIn: 'root'
})
export class JuegoServiceService {


  constructor(private http: HttpClient) { }


  iniciar(command: IniciarJuegoCommand){
    return this.http.post( 'http://localhost:9091/juego/iniciar', command);
  }

  crearJuego(body:any){
    return this.http.post('http://localhost:9091/juego/crear/', {...body})
  }

  // listarJuegos(idJugadorPrincipal: string | null):Observable<JuegoModel[]>{
  //   return this.http.get<JuegoModel[]>(`http://localhost:9091/juego/listar/${idJugadorPrincipal}`);
  // }

  getMiMazo(uid: string, juegoId: string) {
    return this.http.get(`http://localhost:9091/jugador/mazo/${juegoId}/${uid}`);
  }

  getTablero(juegoId: string): Observable<TableroModel> {
    return this.http.get<TableroModel>(`http://localhost:9091/juego/tablero/${juegoId}`);
  }

  iniciarRonda(body: any){
    return this.http.post('http://localhost:9091/juego/ronda/iniciar/', {...body})
  }

  crearRonda(command: CrearRondaCommand){
    return this.http.post( 'http://localhost:9091/juego/crear/ronda', command);
  }

  ponerCartaEnTablero(command: PonerCartaCommand){
    return this.http.post('http://localhost:9091/juego/poner', command)
  }

  // getJuegos(): Observable<JuegoModel[]> {
  //   return this.http.get<JuegoModel[]>('http://localhost:9091/juegos/' );
  // }



}
