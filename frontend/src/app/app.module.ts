// Librerias
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

// Rutas
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './Templates/Home/app.component';

// Paginas
import { LoginComponent } from './Pages/Log-in/login.component';
import { HomeComponent } from './Pages/Home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
