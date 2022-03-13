import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatInputModule} from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatTableModule } from '@angular/material/table';
import { HomeComponent } from './home/home.component';
import { Error404Component } from './error404/error404.component';
import { ListadoPersonasComponent } from './listado-personas/listado-personas.component';
import { HttpClientModule } from '@angular/common/http';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { EditarPersonasComponent } from './editar-personas/editar-personas.component';
import { CrearPersonasComponent } from './crear-personas/crear-personas.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    Error404Component,
    ListadoPersonasComponent,
    EditarPersonasComponent,
    CrearPersonasComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatFormFieldModule,
    MatToolbarModule,
    MatInputModule,
    MatIconModule,
    MatTableModule,
    HttpClientModule,
    FormsModule,
    MatButtonModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
