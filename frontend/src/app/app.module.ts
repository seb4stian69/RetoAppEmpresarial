// Librerias
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

// Rutas
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './Templates/Home/app.component';

// Paginas
import { LoginComponent } from './Pages/Log-in/login.component';
import { HomeComponent } from './Pages/Home/home.component';
import { initializeApp,provideFirebaseApp } from '@angular/fire/app';
import { environment } from '../environments/environment';
import { provideAuth,getAuth } from '@angular/fire/auth';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    provideFirebaseApp(() => initializeApp(environment.firebase)),
    provideAuth(() => getAuth())
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
