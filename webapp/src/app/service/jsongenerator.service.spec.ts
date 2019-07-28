import { TestBed, inject } from '@angular/core/testing';

import { JsongeneratorService } from './jsongenerator.service';

describe('JsongeneratorService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [JsongeneratorService]
    });
  });

  it('should be created', inject([JsongeneratorService], (service: JsongeneratorService) => {
    expect(service).toBeTruthy();
  }));
});
