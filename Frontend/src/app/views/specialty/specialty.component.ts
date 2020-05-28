import { DialogSpecialtyComponent } from './dialog-specialty/dialog-specialty.component';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SpecialtyService } from './../../service/specialty.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Specialty } from './../../model/Specialty';
import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-specialty',
  templateUrl: './specialty.component.html',
  styleUrls: ['./specialty.component.css']
})
export class SpecialtyComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'acciones'];
  dataSource = new MatTableDataSource<Specialty>();

  @ViewChild(MatSort, { static: true }) mSort: MatSort;
  @ViewChild(MatPaginator, { static: true }) mPaginator: MatPaginator;

  constructor(
    private specialtyServ: SpecialtyService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog
  ) { }

  ngOnInit() {
    this.specialtyServ.reactVar.subscribe(data => {
      if (data === 'save') {
        this.showMessage('Especialidad Guardada con éxito', 'Guardar');
      } else if (data === 'edit') {
        this.showMessage('Especialidad Editada con éxito', 'Editar');
      } else if (data === 'delete') {
        this.showMessage('Especialidad Eliminada con éxito', 'Eliminar');
      } else {
        this.showMessage('Error', 'Error');
      }
      this.listSpecialty();
    });
    this.listSpecialty();
  }

  listSpecialty() {
    this.specialtyServ.list().subscribe(data => {
      // console.log(data);
      // this.mPaginator._intl.itemsPerPageLabel = 'Registros por página';
      // this.mPaginator._intl.nextPageLabel = 'Página siguiente';
      // this.mPaginator._intl.previousPageLabel = 'Página anterior';
      // this.mPaginator._intl.firstPageLabel = 'Primera Página';
      // this.mPaginator._intl.lastPageLabel = 'Ultima Página';
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.sort = this.mSort;
      this.dataSource.paginator = this.mPaginator;
    });
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  openDiaogSpecialty(obj?: Specialty) {
    const specialty = obj != null ? obj : new Specialty();
    this.dialog.open(DialogSpecialtyComponent, {
      width: '40%',
      data: specialty
    });
  }

  showMessage(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 3000,
    });
  }

}
