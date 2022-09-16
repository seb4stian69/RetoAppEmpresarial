import { Component, OnInit } from '@angular/core';
import { HttpServiceService } from 'src/app/Services/http-service.service';

@Component({
  selector: 'app-historial-partidas',
  templateUrl: './historial-partidas.component.html',
  styleUrls: ['./historial-partidas.component.scss']
})
export class HistorialPartidasComponent implements OnInit {

  constructor(
    private httpService: HttpServiceService,

    ){ }

  ngOnInit(): void {

    

  }

}
