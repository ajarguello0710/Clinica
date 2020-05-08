import { AutorDialogComponent } from './autor-dialog/autor-dialog.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AutorService } from 'src/app/service/autor.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Autor } from './../../model/Autor';
import { MatTableDataSource } from '@angular/material/table';
import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';

type NewType = boolean;

@Component({
  selector: 'app-autor',
  templateUrl: './autor.component.html',
  styleUrls: ['./autor.component.css']
})
export class AutorComponent implements OnInit {

  titulo = 'Autor';

  displayedColumns: string[] = ['id', 'nombre', 'email', 'fechanacimiento', 'acciones'];
  dataSource = new MatTableDataSource<Autor>();

  @ViewChild(MatSort, { static: true }) mSort: MatSort;
  @ViewChild(MatPaginator, { static: true }) mPaginator: MatPaginator;

  formAutor: FormGroup;
  isVisible: NewType = false; // switch form y del matTable
  isSaving: boolean; // saber si esta guardando o editando

  constructor(
    private autorServ: AutorService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog
    ) { }

  ngOnInit() {
    this.autorServ.varReactive.subscribe(data => {
      if (data === 'guardar') {
        this.showMessage('Autor guardado correctamente', 'Guardar');
      } else if (data === 'editar') {
        this.showMessage('Autor editado correctamente', 'Editar');
      } else {
        this.showMessage('Autor eliminado correctamente', 'Eliminar');
      }
      this.list();
    });
    this.list();
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  list() {
    this.autorServ.listar().subscribe(dataAutor => {
      this.dataSource = new MatTableDataSource(dataAutor);
      this.dataSource.sort = this.mSort;
      this.dataSource.paginator = this.mPaginator;
    });
  }

  openDialog(obj?: Autor) {
    const autor = obj != null ? obj : new Autor();
    this.dialog.open(AutorDialogComponent, {
      width: '40%',
      data: autor
    });
  }

  deleteAutor(id: number) {
    this.autorServ.eliminar(id).subscribe(() => {
      this.autorServ.varReactive.next('eliminar');
    });
  }

  showMessage(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 3000,
    });
  }

}
