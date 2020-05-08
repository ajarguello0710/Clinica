import { Libro } from './../../model/Libro';
import { LibroService } from './../../service/libro.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { FormGroup, FormControl } from '@angular/forms';
import { AutorService } from 'src/app/service/autor.service';
import { MatSnackBar } from '@angular/material/snack-bar';

type NewType = boolean;


@Component({
  selector: 'app-libro',
  templateUrl: './libro.component.html',
  styleUrls: ['./libro.component.css']
})

export class LibroComponent implements OnInit {

  titulo = 'Libro';

  displayedColumns: string[] = ['id', 'nombre', 'aniopublicacion', 'paginas', 'editorial', 'acciones'];
  dataSource = new MatTableDataSource<Libro>();

  @ViewChild(MatSort, { static: true }) mSort: MatSort;
  @ViewChild(MatPaginator, { static: true }) mPaginator: MatPaginator;

  formLibro: FormGroup;
  isVisible: NewType = false; // switch form y del matTable
  isSaving: boolean; // saber si esta guardando o editando

  selecAutores = [];

  constructor(
    private libroServ: LibroService,
    private autorServ: AutorService,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit() {
    this.libroServ.varReactive.subscribe(data => {
      if (data === 'guardar') {
        this.showMessage('Libro guardado correctamente', 'Guardar');
        this.hideForm();
      } else if (data === 'editar') {
        this.showMessage('Libro editado correctamente', 'Editar');
        this.hideForm();
      } else {
        this.showMessage('Libro eliminado correctamente', 'Eliminar');
      }
      this.list();
    });
    this.clearForm();
    this.list();
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  showForm() {
    this.clearForm();
    this.isSaving = true;
    this.isVisible = true;
  }
  hideForm() {
    this.isVisible = false;
  }

  operar() {
    const libro = new Libro();
    libro.id = this.formLibro.value.id;
    libro.nombre = this.formLibro.value.nombre;
    libro.aniopublicacion = this.formLibro.value.aniopublicacion;
    libro.paginas = this.formLibro.value.paginas;
    libro.editorial = this.formLibro.value.editorial;

    if (this.isSaving) {
      libro.autor = this.formLibro.value.autor;
      const datosForm = {
        nombre: this.formLibro.value.nombre,
        aniopublicacion: this.formLibro.value.aniopublicacion,
        paginas: this.formLibro.value.paginas,
        editorial: this.formLibro.value.editorial,
        autor: {
          id: this.formLibro.value.autor,
        }
      };

      const json = JSON.stringify(datosForm);
      const salida = JSON.parse(json);
      this.libroServ.guardar(salida).subscribe(() => {
        this.libroServ.varReactive.next('guardar');
      });
    } else {
      this.libroServ.editar(libro).subscribe(() => {
        this.libroServ.varReactive.next('editar');
      });
    }
    
  }

  editLibro(id: number) {
    this.isSaving = false;

    this.libroServ.listarId(id).subscribe(dataLibro => {
      this.formLibro = new FormGroup({
        id: new FormControl(dataLibro.id),
        nombre: new FormControl(dataLibro.nombre),
        aniopublicacion: new FormControl(dataLibro.aniopublicacion),
        paginas: new FormControl(dataLibro.paginas),
        editorial: new FormControl(dataLibro.editorial),
        autor: new FormControl(dataLibro.autor),
      });
    });

    this.isVisible = true;
  }

  deleteLibro(id: number) {
    this.libroServ.eliminar(id).subscribe(() => {
      this.libroServ.varReactive.next('eiminar');
    });
  }

  showMessage(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 3000,
    });
  }

  list() {
    this.autorServ.listar().subscribe(dataAutor => {
      this.selecAutores = dataAutor;
    });

    this.libroServ.listar().subscribe(dataLibro => {
      this.dataSource = new MatTableDataSource(dataLibro);
      this.dataSource.sort = this.mSort;
      this.dataSource.paginator = this.mPaginator;
    });
  }

  clearForm() {
    this.formLibro = new FormGroup({
      id: new FormControl(0),
      nombre: new FormControl(''),
      aniopublicacion: new FormControl(''),
      paginas: new FormControl(''),
      editorial: new FormControl(''),
      autor: new FormControl('')
    });
  }

}
