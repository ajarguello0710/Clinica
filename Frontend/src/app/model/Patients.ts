import { Address } from './Address';
import { DatePipe } from '@angular/common';

export class Patient {
  id: number;
  name: string;
  lastName: string;
  addres: Address;
  dateBirth: DatePipe;
  mail: string;
  // consults: Consult;
}
