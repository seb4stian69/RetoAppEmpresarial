import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

// Librerias
import { BrowserModule } from '@angular/platform-browser';
import { environment } from '../../../environments/environment';
import { initializeApp,provideFirebaseApp } from '@angular/fire/app';
import { provideAuth,getAuth } from '@angular/fire/auth';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {getFirestore, provideFirestore } from '@angular/fire/firestore';
import { LoginRoutingModule } from './login-routing.module';

// Components
import { LoginComponent } from './Log-in/login.component';


@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    CommonModule,
    LoginRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    provideFirebaseApp(() => initializeApp(environment.firebase)),
    provideAuth(() => getAuth()),
    provideFirestore(()=> getFirestore())
  ]
})

export class LoginModule { }
