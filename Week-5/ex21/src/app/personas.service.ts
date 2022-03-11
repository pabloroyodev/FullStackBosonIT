import { Injectable } from '@angular/core';
import { ListaPersonasI } from './domain/listaPersonas.interface';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class PersonasService {

  url:string = "http://localhost:8080/"

  constructor(private http:HttpClient) { }

  getAllPersonas():Observable<ListaPersonasI[]> {
    let direccion = this.url + "persona";
    return this.http.get<ListaPersonasI[]>(direccion);
  }
}
