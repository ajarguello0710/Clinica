import { Router } from '@angular/router';
import { ConsultSave } from './../../../model/ConsultSave';
import { DialogDetailConsultEditComponent } from './dialog-detail-consult-edit/dialog-detail-consult-edit.component';
import { DialogExamConsultEditComponent } from './dialog-exam-consult-edit/dialog-exam-consult-edit.component';
import { DialogDetailConsultSaveComponent } from './dialog-detail-consult-save/dialog-detail-consult-save.component';
import { ConsultService } from 'src/app/service/consult.service';
import { DialogExamConsultSaveComponent } from './dialog-exam-consult-save/dialog-exam-consult-save.component';
import { MatDialog } from '@angular/material/dialog';
import { ConsultDetail } from './../../../model/ConsultDetail';
import { ExamReportDtos } from './../../../model/ExamReportDtos';
import { MatTableDataSource } from '@angular/material/table';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PatientService } from './../../../service/patient.service';
import { DoctorService } from './../../../service/doctor.service';
import { FormGroup, FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Exam } from 'src/app/model/Exam';
import { formatDate } from '@angular/common';

@Component({
  selector: 'app-consult-detail-save',
  templateUrl: './consult-detail-save.component.html',
  styleUrls: ['./consult-detail-save.component.css']
})
export class ConsultDetailSaveComponent implements OnInit {

  doctors = [];
  patients = [];

  exams: Exam[];
  exam: Exam;

  details: ConsultDetail[];
  detail: ConsultDetail;

  consultSave: ConsultSave;

  examAI = 0;
  detailAI = 0;

  dateSelected: Date;
  dateNow: Date = new Date();

  formConsultSave: FormGroup;

  displayedColumnsExam: string[] = ['id', 'name', 'description', 'acciones'];
  dataSourceExam = new MatTableDataSource<Exam>();

  displayedColumnsConsultDetail: string[] = ['id', 'diagnosis', 'treatment', 'acciones'];
  dataSourceConsultDetail = new MatTableDataSource<ConsultDetail>();

  // @ViewChild(MatSort, { static: true }) mSortExam: MatSort;

  // @ViewChild(MatPaginator, { static: true }) mPaginatorExam: MatPaginator;

  // @ViewChild(MatSort, { static: true }) mSortConsultDetail: MatSort;
  // @ViewChild(MatPaginator, { static: true }) mPaginatorConsultDetail: MatPaginator;

  constructor(
    private doctorServ: DoctorService,
    private patientServ: PatientService,
    private consultServ: ConsultService,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
    private router: Router
  ) { }

  ngOnInit() {
    this.patientServ.list().subscribe(dataPatients => {
      // console.log(dataPatients);
      this.patients = dataPatients;
    });

    this.doctorServ.list().subscribe(dataDoctors => {
      this.doctors = dataDoctors;
    });

    this.formConsult();

    this.consultServ.reactExam.subscribe(data => {
      this.listExamSave(data);
    });

    this.consultServ.reactDetail.subscribe(data => {
      this.listDetailSave(data);
    });

    this.consultServ.reactExamEdit.subscribe(data => {
      this.listExamEdit(data);
    });

    this.consultServ.reactDetailEdit.subscribe(data => {
      this.listDetailEdit(data);
    });

    this.exams = new Array();
    this.details = new Array();
  }

  formConsult() {
    this.formConsultSave = new FormGroup({
      id: new FormControl(''),
      name: new FormControl(''),
      date: new FormControl(''),
      doctor: new FormControl(''),
      patient: new FormControl('')
    });
  }

  action() {
    if (this.details.length === 0 || this.exams.length === 0) {
      if (this.details.length === 0) {
        this.showMessage('Por favor, Agregar un detalle a la consulta', 'Error');
      } else {
        this.showMessage('Por favor, Agregar un exámen a la consulta', 'Error');
      }
    } else {
      // console.log(this.dateNow.getDate);
      this.dateSelected = this.formConsultSave.value.date;
      // console.log(this.dateSelected.getDate());
      if (this.dateSelected.getDate() < this.dateNow.getDate()) {
        this.showMessage('Por favor, Seleccionar una fecha válida', 'Error');
      } else {
        this.consultSave = new ConsultSave();
        this.consultSave.name = this.formConsultSave.value.name;
        this.consultSave.date = this.formConsultSave.value.date;
        this.consultSave.doctor = this.formConsultSave.value.doctor;
        this.consultSave.patient = this.formConsultSave.value.patient;
        // this.consultSave.consultDetailDtos = new Consult();

        this.consultSave.consultDetailDtos = this.details;

        this.consultSave.exams = this.exams;

        this.consultServ.save(this.consultSave).subscribe(() => {
          this.showMessage('Consulta Guarda con éxito', 'Guardar');
          this.consultServ.reactVar.next('saveFull');
          this.router.navigate(['/consult']);
        });
      }
    }

  }

  listDetailSave(obj: ConsultDetail) {
    this.detailAI++;
    this.detail = new ConsultDetail();
    this.detail = obj;
    this.detail.id = this.detailAI;

    this.details.push(this.detail);

    this.dataSourceConsultDetail = new MatTableDataSource(this.details);
    this.showMessage('Detalle agregado a la lista', 'Agregar');

  }

  listDetailEdit(obj: ConsultDetail) {

    let x = 0;
    this.details.forEach(element => {
      const id = obj.id - 1;
      if (obj.id === element.id) {
        this.details.splice(x, 1, obj);
      } else {
        x++;
      }
    });

    this.dataSourceConsultDetail = new MatTableDataSource(this.details);
    this.showMessage('Detalle modificado de la lista', 'Editar');
  }

  listDetailDelete(id: number) {
    let x = 0;
    // id--;
    this.details.forEach(element => {
      if (id === element.id) {
        this.details.splice(x, 1);
      } else {
        x++;
      }
    });

    this.dataSourceConsultDetail = new MatTableDataSource(this.details);
    this.showMessage('Detalle eliminado de la lista', 'Eliminar');
  }

  listExamSave(obj: Exam) {
    this.examAI++;
    this.exam = new Exam();
    this.exam = obj;
    this.exam.id = this.examAI;

    this.exams.push(this.exam);

    this.dataSourceExam = new MatTableDataSource(this.exams);
    this.showMessage('Exámen agregado a la lista', 'Agregar');
  }

  listExamEdit(obj: Exam) {
    let x = 0;
    this.exams.forEach(element => {
      const id = obj.id - 1;
      if (obj.id === element.id) {
        this.exams.splice(x, 1, obj);
      } else {
        x++;
      }
    });

    this.dataSourceExam = new MatTableDataSource(this.exams);
    this.showMessage('Detalle modificado de la lista', 'Editar');
  }

  listExamDelete(id: number) {
    let x = 0;
    // id--;
    this.exams.forEach(element => {
      if (id === element.id) {
        this.exams.splice(x, 1);
      } else {
        x++;
      }
    });

    this.dataSourceExam = new MatTableDataSource(this.exams);
    this.showMessage('Detalle eliminado de la lista', 'Eliminar');
  }

  openDialogExamSave() {
    this.dialog.open(DialogExamConsultSaveComponent, {
      width: '40%'
    });
  }

  openDialogDetailSave() {
    this.dialog.open(DialogDetailConsultSaveComponent, {
      width: '40%'
    });
  }

  openDialogExamEdit(obj?: Exam) {
    this.dialog.open(DialogExamConsultEditComponent, {
      width: '40%',
      data: obj
    });
  }

  openDialogDetailEdit(obj?: ConsultDetail) {
    this.dialog.open(DialogDetailConsultEditComponent, {
      width: '40%',
      data: obj
    });
  }

  showMessage(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 3000,
    });
  }

}
