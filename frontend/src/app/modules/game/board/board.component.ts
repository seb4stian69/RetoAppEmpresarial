import { Component, OnInit } from '@angular/core';
import { HttpServiceService } from 'src/app/Services/http-service.service';
import { ActivatedRoute, Params } from '@angular/router';
import { UserService } from 'src/app/Services/user-service.service';


@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss']
})
export class BoardComponent implements OnInit {

  dataGeneralBoard: any;
  cartasCurrentUser: any;

  constructor(
    private httpService: HttpServiceService,
    private activatedRoute: ActivatedRoute,
    private userService: UserService
  ){}

  ngOnInit(): void {

    let pathParams = this.activatedRoute.params;

    let currentUid = this.userService.getCurrentUserUid();

    pathParams.subscribe( (data: Params) =>{

      this.httpService.obtenerTablero(data['id']).subscribe( data =>{
        console.log(data);
        this.dataGeneralBoard = data;
      })

      this.httpService.obtenerCartasCurrentUser(currentUid, data['id']).subscribe( (data: Params)  => {
        console.log(data['cartas']);
        this.cartasCurrentUser = data['cartas'];
      })

    })

  }

  ponerCarta(id: any){

    console.log(`Carta puesta: ${id}`)

  }

}
