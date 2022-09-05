import { Injectable } from '@angular/core';
import { JugadoresModel } from '../models/jugadores.models';

@Injectable({
  providedIn: 'root'
})

export class JugadoresService {

  constructor() { }

  getJugadores(): Array<JugadoresModel> {

    const jugadores = new Array<JugadoresModel>;

    jugadores.push({
      name: 'Camilo',
      id: '123'
    })

    jugadores.push({
      name: 'Luisa',
      id: '321'
    })

    jugadores.push({
      name: 'Sebastian',
      id: '456'
    })

    jugadores.push({
      name: 'Jorge',
      id: '789'
    })

    return jugadores;

  }

}
