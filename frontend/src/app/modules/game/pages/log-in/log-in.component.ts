import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../shared/services/shared.service';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.scss']
})
export class LogInComponent implements OnInit {

  constructor(
    private auth$: AuthService
  ) { }

  ngOnInit(): void { }

  buttonLoguin(): void {
    console.log('Login button clicked');
    this.auth$.SigninWithGoogle();
  }

}
