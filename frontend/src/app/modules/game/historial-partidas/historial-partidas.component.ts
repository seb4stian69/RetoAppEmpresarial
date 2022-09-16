import { Component, OnInit } from '@angular/core';
import { HttpServiceService } from 'src/app/Services/http-service.service';
import { UserService } from 'src/app/Services/user-service.service';

@Component({
  selector: 'app-historial-partidas',
  templateUrl: './historial-partidas.component.html',
  styleUrls: ['./historial-partidas.component.scss']
})
export class HistorialPartidasComponent implements OnInit {

  listaJuegos: any;

  constructor(
    private httpService: HttpServiceService,
    private userService: UserService,
  ){}

  ngOnInit(): void {

    this.httpService.historicoFinalizados(""+this.userService.getCurrentUserUid()).subscribe(data=>{
      this.listaJuegos = data
      console.log(data)
    })

  }

}
