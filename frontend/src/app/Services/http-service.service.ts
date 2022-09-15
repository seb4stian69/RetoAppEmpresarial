import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class HttpServiceService {

  constructor(private http: HttpClient) { }

  createGameBoard(body: any){
    return this.http.post('http://localhost:9091/juego/crear',{...body});
  }

  listarGameBoard(id: string){
    return this.http.get(`http://localhost:9091/juego/listar/${id}`);
  }

  iniciarJuego(id: string){
    return this.http.post('http://localhost:9091/juego/iniciar', {"juegoId": id})
  }

  obtenerTablero(id: string){
    return this.http.get(`http://localhost:9091/juego/tablero/${id}`)
  }

  obtenerCartasCurrentUser(userId: any, juegoId: any){
    return this.http.get(`http://localhost:9091/jugador/mazo/${juegoId}/${userId}`)
  }

  iniciarRonda(juegoId:any){
    return this.http.post(`http://localhost:9091/juego/ronda/iniciar`, {"juegoId": juegoId})
  }

  ponerCartaEnTablero(body:Object){
    return this.http.post('http://localhost:9091/juego/poner', body)
  }

}
