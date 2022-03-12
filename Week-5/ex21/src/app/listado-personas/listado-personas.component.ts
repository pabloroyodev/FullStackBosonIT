import { Component, OnInit} from '@angular/core';
import { PersonasService } from '../personas.service'
import { Router } from '@angular/router';
import { ListaPersonasI } from '../domain/listaPersonas.interface'

@Component({
  selector: 'app-listado-personas',
  templateUrl: './listado-personas.component.html',
  styleUrls: ['./listado-personas.component.css']
})

export class ListadoPersonasComponent implements OnInit {

  displayedColumns: string[] = ['idPersona', 'user', 'password', 'name', 'surname', 'companyEmail', 'personalEmail', 'city', 'active', 'createdDate', 'imageUrl', 'terminationDate'];
  personas: ListaPersonasI[] = [];

  constructor(private api:PersonasService, private router:Router) { }

  ngOnInit(): void {
    this.api.getAllPersonas().subscribe(data =>{
      this.personas = data;
    })
  }
}
