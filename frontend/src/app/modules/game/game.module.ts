import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GameRoutingModule } from './game-routing.module';
import { HomeComponent } from './Home/home.component';
import { CrearjuegoComponent } from './crearjuego/crearjuego.component';
import { ListajuegosComponent } from './listajuegos/listajuegos.component';
import { BoardComponent } from './board/board.component';




@NgModule({
  declarations: [
    HomeComponent,
    CrearjuegoComponent,
    ListajuegosComponent,
    BoardComponent
  ],
  imports: [
    CommonModule,
    GameRoutingModule
  ]
})
export class GameModule { }
