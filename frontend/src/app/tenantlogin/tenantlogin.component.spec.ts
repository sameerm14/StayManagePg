import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TenantloginComponent } from './tenantlogin.component';

describe('TenantloginComponent', () => {
  let component: TenantloginComponent;
  let fixture: ComponentFixture<TenantloginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TenantloginComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TenantloginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
