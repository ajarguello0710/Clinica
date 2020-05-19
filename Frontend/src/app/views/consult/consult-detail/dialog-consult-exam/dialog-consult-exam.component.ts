import { ActivatedRoute, Params, Router } from '@angular/router';
import { ExamService } from './../../../../service/exam.service';
import { FormGroup, FormControl } from '@angular/forms';
import { Exam } from './../../../../model/Exam';
import { ConsultExam } from './../../../../model/ConsultExam';
import { ConsultDetailService } from './../../../../service/consult-detail.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Component, OnInit, Inject } from '@angular/core';
import { Route } from '@angular/compiler/src/core';

@Component({
  selector: 'app-dialog-consult-exam',
  templateUrl: './dialog-consult-exam.component.html',
  styleUrls: ['./dialog-consult-exam.component.css']
})
export class DialogConsultExamComponent implements OnInit {

  exam: Exam;
  idConsult: number;
  formExam: FormGroup;
  isSaving = true;

  constructor(
    private dialogRef: MatDialogRef<DialogConsultExamComponent>,
    private consultDetailService: ConsultDetailService,
    private examServ: ExamService,
    private router: ActivatedRoute,
    @Inject(MAT_DIALOG_DATA) private dataConsultExam: Exam
  ) { }

  ngOnInit() {
    if (Object.entries(this.dataConsultExam).length === 0) {
      this.save();
    } else {
      this.isSaving = false;
      this.edit();
    }
  }

  save() {
    this.formExam = new FormGroup({
      id: new FormControl(''),
      name: new FormControl(''),
      description: new FormControl('')
    });
  }

  edit() {
    this.formExam = new FormGroup({
      id: new FormControl(this.dataConsultExam.id),
      name: new FormControl(this.dataConsultExam.name),
      description: new FormControl(this.dataConsultExam.description)
    });
  }

  action() {
    this.exam = new Exam();
    this.exam.name = this.formExam.value.name;
    this.exam.description = this.formExam.value.description;
    if (this.isSaving) {
      // this.
    } else {
      this.exam.id = this.formExam.value.id;
      this.examServ.edit(this.exam).subscribe(() => {
        this.closeDialog();
        this.examServ.reactVar.next('edit');
      });
    }

  }

  closeDialog() {
    this.dialogRef.close();
  }

}
