import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CrearPersonasComponent } from './crear-personas/crear-personas.component';
import { EditarPersonasComponent } from './editar-personas/editar-personas.component';
import { Error404Component } from './error404/error404.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  {path: '', component: HomeComponent, pathMatch: 'full'},
  {path: 'anadir', component: CrearPersonasComponent},
  {path: 'editar/:id', component: EditarPersonasComponent},
  {path: '**', component: Error404Component, pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
