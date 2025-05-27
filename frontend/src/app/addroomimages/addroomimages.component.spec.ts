import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddroomimagesComponent } from './addroomimages.component';

describe('AddroomimagesComponent', () => {
  let component: AddroomimagesComponent;
  let fixture: ComponentFixture<AddroomimagesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddroomimagesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddroomimagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
