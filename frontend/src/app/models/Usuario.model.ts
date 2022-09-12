export class UsuarioModel{

  uid: string;
  email: string;
  password: string;

  constructor(uid: string ,email: string, password: string){
    this.uid = uid;
    this.email = email;
    this.password = password;
  }

  public get Email(){
    return this.email;
  }

  public get Password(){
    return this.password;
  }

  public get Uid(){
    return this.uid;
  }

}
