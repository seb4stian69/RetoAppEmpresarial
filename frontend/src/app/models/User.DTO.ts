export class UserDTO {

  uid: string;
  alias: string;
  email: string;

  constructor(uid: string, alias: string, email: string) {
    this.uid = uid;
    this.alias = alias;
    this.email = email
  }

}
