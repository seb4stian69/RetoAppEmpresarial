import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LogvalidatorGuard } from './modules/game/guard/logvalidator.guard';
import { LogInComponent } from './modules/game/pages/log-in/log-in.component';
import { NewGameComponent } from './modules/game/pages/new-game/new-game.component';

const routes: Routes = [
  {
    path: "",
    component: LogInComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
