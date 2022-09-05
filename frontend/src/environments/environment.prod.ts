import { enableProdMode } from "@angular/core";

export const environment = {
  production: true,
  urlServices: 'http://localhost:8080',
  firebaseConfig:{
    apiKey: "AIzaSyAdT5des2aONsZoajhMwyHr6-LntH9lPxY",
    authDomain: "appcards-307a5.firebaseapp.com",
    projectId: "appcards-307a5",
    storageBucket: "appcards-307a5.appspot.com",
    messagingSenderId: "628790350637",
    appId: "1:628790350637:web:abd77bb934d50bd7db79a4"
  }
};

if (environment.production) {
  enableProdMode();
}
