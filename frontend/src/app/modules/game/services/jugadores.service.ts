import { Injectable } from '@angular/core';
import firebase from 'firebase/compat';
import { Usuario } from '../../game/models/usuario.model';
import {
  AngularFirestore,
  AngularFirestoreCollection,
} from '@angular/fire/compat/firestore';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Game } from '../models/game.model';

@Injectable({
  providedIn: 'root'
})
export class JugadoresService {
  private usersCollection: AngularFirestoreCollection<Usuario>;

  constructor(private storage: AngularFirestore, private http: HttpClient) {
    this.usersCollection = storage.collection<Usuario>('usuarios');
  }

  game(gamers: Array<string>): Observable<Game> {
    return this.http.post<Game>(`${environment.urlBase}/game`, { gamers }, {
      headers: {
        'Content-Type': 'application/json'
      }
    });
  }

  async getJugadores(): Promise<Array<Usuario>>{
    const result = await new Promise<Usuario[]>((resolve, reject) => {
      // const query = this.usersCollection.ref.where('id', '==', 'Mi Variable');
      // query.get().then((data) => {
      //   data.forEach((value) => {
      //     console.log(value);
      //   });
      // });
      const query = this.usersCollection;
      query.get().subscribe({
        next: (data) => {
          const usuarios = new Array<Usuario>();
          data.forEach((gamer) => {
            usuarios.push(gamer.data());
          });
          resolve(usuarios);
        },
        error: (error) => {
          console.log(error);
          reject(error);
        }
      });
    });
    return result;
  }

  public addGamer(user: firebase.User | null): void {
    if (user != null) {
      const newUser = {
        id: user.uid,
        name: user.displayName,
        email: user.email,
        picture: user.photoURL
      } as Usuario;

      // Update
      // this.usersCollection
      // .doc(user.uid)
      // .update({
      //   name: 'Nombre Nuevo',
      // });

      // Delete
      // this.usersCollection
      // .doc(user.uid)
      // .delete();

      // Create
      this.usersCollection
      .doc(user.uid)
      .set(newUser)
      .then(() => console.log('Jugador registrado'));
    }
  }
}
