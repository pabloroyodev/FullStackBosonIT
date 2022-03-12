import { Component, OnInit} from '@angular/core';
import { PersonasService } from '../personas.service'
import { Router } from '@angular/router';
import { ListaPersonasI } from '../domain/listaPersonas.interface'
import { animate, state, style, transition, trigger } from '@angular/animations';

@Component({
  selector: 'app-listado-personas',
  templateUrl: './listado-personas.component.html',
  styleUrls: ['./listado-personas.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})

export class ListadoPersonasComponent implements OnInit {
  value = '';
  displayedColumns: string[] = ['idPersona', 'user', 'password', 'name', 'surname', 'companyEmail', 'personalEmail', 'city', 'active', 'createdDate', 'imageUrl', 'terminationDate'];
  columnsToDisplay: string[] = ['idPersona', 'user', 'city', 'active'];
  personas: ListaPersonasI[] = [];
  expandedElement: ListaPersonasI[] | null | undefined;
  
  constructor(private api:PersonasService, private router:Router) { }

  ngOnInit(): void {
    this.api.getAllPersonas().subscribe(data =>{
      this.personas = data;
    })
  }

  filterByUser(user: string){
    this.api.getPersonaByUser(user).subscribe(data =>{
      this.personas = data;
    })
  }

  deleteUser(id: number){
    this.api.deleteById(id).subscribe(data =>{
      this.personas = data;
      this.ngOnInit();
    })
  }
}
