export class UsuarioInterface{

  email: string;
  password: string;

  constructor(email: string, password: string){
    this.email = email;
    this.password = password;
  }

}
