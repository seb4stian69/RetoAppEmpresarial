import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrearjuegoComponent } from './crearjuego.component';

describe('CrearjuegoComponent', () => {
  let component: CrearjuegoComponent;
  let fixture: ComponentFixture<CrearjuegoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CrearjuegoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CrearjuegoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
