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
    return this.http.post('http://localhost:9091/juego/iniciar', {id})
  }

  obtenerTablero(id: string){
    return this.http.get('')
  }

}
