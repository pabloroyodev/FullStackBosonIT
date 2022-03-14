import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ListaPersonasI } from '../domain/listaPersonas.interface';
import { PersonasService } from '../personas.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';


@Component({
  selector: 'app-crear-personas',
  templateUrl: './crear-personas.component.html',
  styleUrls: ['./crear-personas.component.css']
})
export class CrearPersonasComponent implements OnInit {

  hide = true;

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

  constructor(private activerouter: ActivatedRoute,
    private router: Router,
    private api: PersonasService) { }

  ngOnInit(): void {
  }

  postForm(form: ListaPersonasI){
    this.api.addPersona(form).subscribe( data => {
      console.log(data)
    })
  }

}
