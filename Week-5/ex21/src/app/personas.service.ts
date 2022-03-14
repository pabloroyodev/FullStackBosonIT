import { Injectable } from '@angular/core';
import { ListaPersonasI } from './domain/listaPersonas.interface';
import { HttpClient } from '@angular/common/http';
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

  getPersonaByUser(username: string):Observable<ListaPersonasI[]> {
    let direccion = this.url + "persona/" + username + "/usuario";
    return this.http.get<ListaPersonasI[]>(direccion);
  }

  deleteById(id: number){
    let direccion = this.url + "persona/" + id;
    return this.http.delete<ListaPersonasI[]>(direccion);
  }

  getPersonaById(id: string | null):Observable<ListaPersonasI>{
    let direccion = this.url + "persona/" + id;
    return this.http.get<ListaPersonasI>(direccion);
  }

  editarPersona(id: any, form:ListaPersonasI ):Observable<ListaPersonasI>{
    let direccion = this.url + "persona/" + id;
    return this.http.put<ListaPersonasI>(direccion, form);
  }

  addPersona(form:ListaPersonasI ):Observable<ListaPersonasI>{
    let direccion = this.url + "persona";
    return this.http.post<ListaPersonasI>(direccion, form);
  }
}
