import { Exam } from './Exam';
import { ConsultDetail } from './ConsultDetail';
export class ConsultSave {
  name: string;
  date: Date;
  doctor: number;
  patient: number;
  consultDetailDtos: ConsultDetail[];
  exams: Exam[];
}
