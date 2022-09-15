import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistorialPartidasComponent } from './historial-partidas.component';

describe('HistorialPartidasComponent', () => {
  let component: HistorialPartidasComponent;
  let fixture: ComponentFixture<HistorialPartidasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HistorialPartidasComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HistorialPartidasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
