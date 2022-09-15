import { Injectable } from '@angular/core';
import {
  Auth,
  signInWithEmailAndPassword,
  signInWithPopup,
  GoogleAuthProvider,
  signOut,
  createUserWithEmailAndPassword,
  getAuth,
} from '@angular/fire/auth'
import { UsuarioInterface } from '../models/User.interface';

@Injectable({
  providedIn: 'root'
})


export class UserService {

  constructor(private auth: Auth) {}

  register(user: UsuarioInterface){
    return createUserWithEmailAndPassword(this.auth, user.email, user.password);
  }

  login(user: UsuarioInterface){
    return signInWithEmailAndPassword(this.auth, user.email, user.password);
  }

  loginWithGoogle() {
    return signInWithPopup(this.auth, new GoogleAuthProvider());
  }

  getDataUser(){
    const auth = getAuth();
    return auth;
  }

  getCurrentUserUid(){
    return this.auth.currentUser?.uid;
  }

  getCurrentUserAlias(){
    return this.auth.currentUser?.displayName;
  }

  logOut() {
    return signOut(this.auth);
  }

}
