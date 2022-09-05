import { TestBed } from '@angular/core/testing';

import { LogvalidatorGuard } from './logvalidator.guard';

describe('LogvalidatorGuard', () => {
  let guard: LogvalidatorGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(LogvalidatorGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
