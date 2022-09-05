// Librerias
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {AngularFireModule} from '@angular/fire/compat';
import {AngularFireAuthModule} from '@angular/fire/compat/auth';
import { HttpClientModule } from '@angular/common/http';

// Routes
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './templates/home/app.component';

// Components
import { NewGameComponent } from './modules/game/pages/new-game/new-game.component';
import { LogInComponent } from './modules/game/pages/log-in/log-in.component';
import { environment } from 'src/environments/environment';
import { SharedModule } from './modules/shared/shared.module';

/* + ------------------------------------------------------------------------------------------------ +*/

@NgModule({
  declarations: [
    AppComponent,
    NewGameComponent,
    LogInComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    AngularFireAuthModule,
    AngularFireModule.initializeApp(environment.firebaseConfig),
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

/* + ------------------------------------------------------------------------------------------------ +*/
