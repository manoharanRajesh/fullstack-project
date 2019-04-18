export class User {
   empId: string;
   firstName: string;
   lastName: string;

  constructor(firstName: string = null, lastName: string = null, empId: string = null) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.empId = empId;
  }
}
