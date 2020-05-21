import { Address } from './Address';
import { DatePipe } from '@angular/common';

export class Patient {
  id: number;
  name: string;
  lastName: string;
  address: Address;
  dateBirth: DatePipe;
  mail: string;
  state: boolean;
  // consults: Consult;
}
