import { Doctor } from './Doctor';
import { DatePipe } from '@angular/common';

export class Consult {
  id: number;
  name: string;
  date: DatePipe;
  doctor: Doctor;
  // patient: Patient;
}
