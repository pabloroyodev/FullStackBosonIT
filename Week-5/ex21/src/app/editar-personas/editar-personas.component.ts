import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ListaPersonasI } from '../domain/listaPersonas.interface';
import { PersonasService } from '../personas.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-editar-personas',
  templateUrl: './editar-personas.component.html',
  styleUrls: ['./editar-personas.component.css'],
})
export class EditarPersonasComponent implements OnInit {
  constructor(
    private activerouter: ActivatedRoute,
    private router: Router,
    private api: PersonasService
  ) {}

  editarForm = new FormGroup({
    user: new FormControl(''),
    password: new FormControl(''),
    name: new FormControl(''),
    surname: new FormControl(''),
    companyEmail: new FormControl(''),
    personalEmail: new FormControl(''),
    city: new FormControl(''),
    active: new FormControl(''),
    createdDate: new FormControl(''),
    imageUrl: new FormControl(''),
    terminationDate: new FormControl(''),
  });

  ngOnInit(): void {
    let personaId: string | null =
      this.activerouter.snapshot.paramMap.get('id');
    this.api.getPersonaById(personaId).subscribe((data) => {
      this.editarForm.setValue({
        user: data.user,
        password: data.password,
        name: data.name,
        surname: data.surname,
        companyEmail: data.companyEmail,
        personalEmail: data.personalEmail,
        city: data.city,
        active: data.active,
        createdDate: data.createdDate,
        imageUrl: data.imageUrl,
        terminationDate: data.terminationDate,
      });

      console.log(this.editarForm.value)
    });
  }
}
