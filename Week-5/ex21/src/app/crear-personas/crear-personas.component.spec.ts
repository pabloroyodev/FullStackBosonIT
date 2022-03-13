import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrearPersonasComponent } from './crear-personas.component';

describe('CrearPersonasComponent', () => {
  let component: CrearPersonasComponent;
  let fixture: ComponentFixture<CrearPersonasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CrearPersonasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CrearPersonasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
