import { TestBed } from '@angular/core/testing';

import { AppLoggerService } from './app-logger.service';

describe('AppLoggerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AppLoggerService = TestBed.get(AppLoggerService);
    expect(service).toBeTruthy();
  });
});
