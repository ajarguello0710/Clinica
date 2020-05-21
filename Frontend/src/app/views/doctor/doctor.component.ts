import { DialogDoctorComponent } from './dialog-doctor/dialog-doctor.component';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DoctorService } from './../../service/doctor.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Doctor } from './../../model/Doctor';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.css']
})
export class DoctorComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'lastName', 'dateBirth', 'state', 'acciones'];
  dataSource = new MatTableDataSource<Doctor>();

  @ViewChild(MatSort, { static: true }) mSort: MatSort;
  @ViewChild(MatPaginator, { static: true }) mPaginator: MatPaginator;

  // paginacion
  totalElements = 0;

  constructor(
    private doctorServ: DoctorService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog
    ) { }

  ngOnInit() {
    this.doctorServ.reactVar.subscribe(data => {
      if (data === 'save') {
        this.showMessage('Doctor Guardado con éxito', 'Guardar');
      } else if (data === 'edit') {
        this.showMessage('Doctor Editado con éxito', 'Editar');
      } else if (data === 'delete') {
        this.showMessage('Doctor Eliminado con éxito', 'Eliminar');
      } else {
        this.showMessage('No se puede agregar el paciente y doctor', 'Error');
      }
      this.listDoctor(0, 10);
    });
    this.listDoctor(0 , 10);
  }

  listDoctor(page: number, size: number) {
    this.doctorServ.listPage(page, size).subscribe(data => {
      console.log(data);
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
    console.log(e);
    this.listDoctor(e.pageIndex, e.pageSize);
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  openDiaogDoctor(obj?: Doctor) {
    const doctor = obj != null ? obj : new Doctor();
    this.dialog.open(DialogDoctorComponent, {
      width: '40%',
      data: doctor
    });
  }

  showMessage(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 3000,
    });
  }

}
