import { Injectable } from '@angular/core';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  private socket!: WebSocketSubject<unknown>;
  constructor() {}

  conection(id: string) {
    return this.socket = webSocket(`ws://localhost:8081/retrieve/${id}`);
  }

  close(){
    this.socket.unsubscribe();
  }

}
