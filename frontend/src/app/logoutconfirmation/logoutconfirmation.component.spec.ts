import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LogoutconfirmationComponent } from './logoutconfirmation.component';

describe('LogoutconfirmationComponent', () => {
  let component: LogoutconfirmationComponent;
  let fixture: ComponentFixture<LogoutconfirmationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LogoutconfirmationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LogoutconfirmationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
