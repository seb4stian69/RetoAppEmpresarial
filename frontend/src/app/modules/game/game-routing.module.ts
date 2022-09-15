import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BoardComponent } from './board/board.component';
import { CrearjuegoComponent } from './crearjuego/crearjuego.component';
import { HomeComponent } from './Home/home.component';
import { ListajuegosComponent } from './listajuegos/listajuegos.component';



const routes: Routes = [

  {
    path: "",
    component: HomeComponent
  },
  {
    path: "crear",
    component: CrearjuegoComponent
  },
  {
    path: "listajuegos",
    component: ListajuegosComponent
  },
  {
    path: "board/:id",
    component: BoardComponent
  },
  {
    path: "historial/:id",
    component: BoardComponent
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class GameRoutingModule { }
