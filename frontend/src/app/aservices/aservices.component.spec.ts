import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AservicesComponent } from './aservices.component';

describe('AservicesComponent', () => {
  let component: AservicesComponent;
  let fixture: ComponentFixture<AservicesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AservicesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AservicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
