import { Component, OnInit } from '@angular/core';
import { HttpServiceService } from 'src/app/Services/http-service.service';
import { v4 as uuidv4 } from 'uuid';

@Component({
  selector: 'app-listajuegos',
  templateUrl: './listajuegos.component.html',
  styleUrls: ['./listajuegos.component.scss']
})
export class ListajuegosComponent implements OnInit {

    uuid:string =uuidv4();
    listJuegos:any = [];
  constructor(
    private httpService: HttpServiceService
  ){}

  ngOnInit(): void {

    this.httpService.listarGameBoard("uid-p1").subscribe(juego => this.listJuegos = juego)

  }

}
