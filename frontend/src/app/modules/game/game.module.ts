import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GameRoutingModule } from './game-routing.module';
import { HomeComponent } from './Home/home.component';
import { CrearjuegoComponent } from './crearjuego/crearjuego.component';
import { ListajuegosComponent } from './listajuegos/listajuegos.component';
import { BoardComponent } from './board/board.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ListboxModule } from 'primeng/listbox';
import { HistorialPartidasComponent } from './historial-partidas/historial-partidas.component';


@NgModule({
  declarations: [
    HomeComponent,
    CrearjuegoComponent,
    ListajuegosComponent,
    BoardComponent,
    HistorialPartidasComponent
  ],
  imports: [

    CommonModule,
    GameRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    ListboxModule

  ]
})

export class GameModule { }
