import { Injectable, NgZone } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import { Router } from '@angular/router';
import { AuthProvider, GoogleAuthProvider } from 'firebase/auth';
import { JugadoresService } from '../../game/services/jugadores.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private ngZone: NgZone,
    private router: Router,
    private afAuth: AngularFireAuth,
    private gamers$: JugadoresService
  ) {}

  logout(): void {
    this.afAuth.signOut().then((_res) => {
      this.ngZone.run(() => {
        this.router.navigate(['']);
    })
    });
  }

  async getUserAuth() {
    const userData = await this.afAuth.currentUser;
    return userData;
  }

  SigninWithGoogle(): Promise<void> {
    return this.OAuthProvider(new GoogleAuthProvider())
        .then(res => {
            console.log('Successfully logged in!')
        }).catch(error => {
            console.log(error)
        });
  }

  private OAuthProvider(provider: AuthProvider) {
    return this.afAuth.signInWithPopup(provider)
        .then((res) => {
            this.gamers$.addGamer(res.user);
            this.ngZone.run(() => {
                this.router.navigate(['game/new']);
            })
        }).catch((error) => {
            window.alert(error)
        })
  }
}
