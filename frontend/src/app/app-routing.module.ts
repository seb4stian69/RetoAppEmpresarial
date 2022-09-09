// Librerias
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { canActivate, redirectUnauthorizedTo } from '@angular/fire/auth-guard';

const goToLogin = () => redirectUnauthorizedTo(['']);

const routes: Routes = [

  {
    path: "",
    loadChildren: () =>
          import('./modules/login/login.module').then((module) => module.LoginModule),
  },
  {
    path: "game",
    loadChildren: () =>
          import('./modules/game/game.module').then((module) => module.GameModule),
    ...canActivate(goToLogin)
  },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
