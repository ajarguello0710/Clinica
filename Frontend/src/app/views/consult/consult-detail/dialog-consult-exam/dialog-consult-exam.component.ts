import { ExamService } from './../../../../service/exam.service';
import { FormGroup, FormControl } from '@angular/forms';
import { Exam } from './../../../../model/Exam';
import { ConsultExam } from './../../../../model/ConsultExam';
import { ConsultDetailService } from './../../../../service/consult-detail.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-dialog-consult-exam',
  templateUrl: './dialog-consult-exam.component.html',
  styleUrls: ['./dialog-consult-exam.component.css']
})
export class DialogConsultExamComponent implements OnInit {

  exam: Exam;

  formExamEdit: FormGroup;

  constructor(
    private dialogRef: MatDialogRef<DialogConsultExamComponent>,
    private consultDetailService: ConsultDetailService,
    private examServ: ExamService,
    @Inject(MAT_DIALOG_DATA) private dataConsultExam: Exam
  ) { }

  ngOnInit() {
    this.edit();
  }

  edit() {
    this.formExamEdit = new FormGroup({
      id: new FormControl(this.dataConsultExam.id),
      name: new FormControl(this.dataConsultExam.name),
      description: new FormControl(this.dataConsultExam.description)
    });
  }

  action() {
    this.exam = new Exam();
    this.exam.id = this.formExamEdit.value.id;
    this.exam.name = this.formExamEdit.value.name;
    this.exam.description = this.formExamEdit.value.description;

    this.examServ.edit(this.exam).subscribe(() => {
      this.closeDialog();
      this.examServ.reactVar.next('edit');
    });
  }

  closeDialog() {
    this.dialogRef.close();
  }

}
