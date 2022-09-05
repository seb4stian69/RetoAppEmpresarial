import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators} from '@angular/forms';
import { JugadoresModel } from '../../models/jugadores.models';
import { JugadoresService } from '../../services/jugadores.service';

@Component({
  selector: 'app-new-game',
  templateUrl: './new-game.component.html',
  styleUrls: ['./new-game.component.scss']
})

export class NewGameComponent implements OnInit {

  formJugadores: FormGroup;
  jugadores!: Array<JugadoresModel>;

  constructor(private jugadoresService: JugadoresService) {

    this.formJugadores = this.createFormJugadores();

  }

  ngOnInit(): void {

    this.jugadores = this.jugadoresService.getJugadores();
    console.log(this.jugadores);

  }

  public submit(): void {
    console.log("Submit", this.formJugadores);
  }

  private createFormJugadores(): FormGroup {

    return new FormGroup({

      jugadores: new FormControl(null, [Validators.required])

    });

  }

}
