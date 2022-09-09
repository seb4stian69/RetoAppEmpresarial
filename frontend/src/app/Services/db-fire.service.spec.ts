import { TestBed } from '@angular/core/testing';

import { DbFireService } from './db-fire.service';

describe('DbFireService', () => {
  let service: DbFireService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DbFireService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
