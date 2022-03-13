import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarPersonasComponent } from './editar-personas.component';

describe('EditarPersonasComponent', () => {
  let component: EditarPersonasComponent;
  let fixture: ComponentFixture<EditarPersonasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditarPersonasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditarPersonasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
