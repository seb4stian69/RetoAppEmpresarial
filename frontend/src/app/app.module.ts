// Librerias
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

// Routes
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './templates/home/app.component';

// Components
import { NewGameComponent } from './modules/game/pages/new-game/new-game.component';

/* + ------------------------------------------------------------------------------------------------ +*/

@NgModule({
  declarations: [
    AppComponent,
    NewGameComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

/* + ------------------------------------------------------------------------------------------------ +*/
