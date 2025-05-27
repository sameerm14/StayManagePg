import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArentsComponent } from './arents.component';

describe('ArentsComponent', () => {
  let component: ArentsComponent;
  let fixture: ComponentFixture<ArentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ArentsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
