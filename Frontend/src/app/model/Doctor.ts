import { DatePipe } from '@angular/common';
import { Address } from './Address';
import { Specialty } from './Specialty';

export class Doctor {
  id: number;
  name: string;
  lastName: string;
  dateBirth: DatePipe;
  address: Address;
  state: boolean;
  mail: string;
  specialties: Specialty;
}
