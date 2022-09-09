import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListajuegosComponent } from './listajuegos.component';

describe('ListajuegosComponent', () => {
  let component: ListajuegosComponent;
  let fixture: ComponentFixture<ListajuegosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListajuegosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListajuegosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
