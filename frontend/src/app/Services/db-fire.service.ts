import { Injectable } from '@angular/core';

import{
  collection,
  collectionData,
  CollectionReference,
  doc,
  Firestore,
  setDoc
}from '@angular/fire/firestore';

import { Observable } from 'rxjs';
import { UsuarioModel } from '../models/Usuario.model';
import { UserDTO } from '../models/User.DTO';

@Injectable({
  providedIn: 'root'
})
export class DbFireService {

  playersFire: CollectionReference = collection(this.getFirestore, 'Players')

  constructor(private getFirestore: Firestore) { }

  getUser(){
    return collectionData(this.playersFire, {
      idField: 'uid'
    }) as Observable<UsuarioModel[]>
  }

  async saveUser(user: UserDTO){
    const userRef = doc(this.playersFire, user.uid)
    return setDoc(userRef, user)
  }

}
