import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DialogPatientComponent } from './dialog-patient/dialog-patient.component';
import { Patient } from './../../model/Patients';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { PatientService } from './../../service/patient.service';
import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'lastName', 'dateBirth', 'state', 'acciones'];
  dataSource = new MatTableDataSource<Patient>();

  @ViewChild(MatSort, { static: true }) mSort: MatSort;
  @ViewChild(MatPaginator, { static: true }) mPaginator: MatPaginator;

  // paginacion

  totalElements = 0;

  constructor(
    private patientServ: PatientService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog
    ) { }

  ngOnInit() {
    this.patientServ.reactVar.subscribe(data => {
      if (data === 'save') {
        this.showMessage('Paciente Guardado con éxito', 'Guardar');
      } else if (data === 'edit') {
        this.showMessage('Paciente Editado con éxito', 'Editar');
      } else if (data === 'delete') {
        this.showMessage('Paciente Eliminado con éxito', 'Eliminar');
      } else {
        this.showMessage('No se puede agregar el paciente y doctor', 'Error');
      }
      this.listPatient(0, 10);
    });
    this.listPatient(0, 10);
  }

  listPatient(page: number, size: number) {
    this.patientServ.listPaginated(page, size).subscribe(data => {
      // console.log(data);
      this.totalElements = data.totalElements;
      this.mPaginator._intl.itemsPerPageLabel = 'Registros por página';
      this.mPaginator._intl.nextPageLabel = 'Página siguiente';
      this.mPaginator._intl.previousPageLabel = 'Página anterior';
      this.mPaginator._intl.firstPageLabel = 'Primera Página';
      this.mPaginator._intl.lastPageLabel = 'Ultima Página';
      this.dataSource = new MatTableDataSource(data.content);
      this.dataSource.sort = this.mSort;
      // this.dataSource.paginator = this.mPaginator;
    });
  }

  changePage(e: any) {
    // console.log(e);
    this.listPatient(e.pageIndex, e.pageSize);
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  openDiaogPatient(obj?: Patient) {
    const patient = obj != null ? obj : new Patient();
    this.dialog.open(DialogPatientComponent, {
      width: '40%',
      data: patient
    });
  }

  showMessage(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 3000,
    });
  }

}
