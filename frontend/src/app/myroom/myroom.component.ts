import { Component } from '@angular/core';
import { TenantService } from '../services/tenant.service';

@Component({
  selector: 'app-myroom',
  standalone: false,
  templateUrl: './myroom.component.html',
  styleUrl: './myroom.component.css',
})
export class MyroomComponent {
  data: any = {};

  constructor(private tenantservice: TenantService) {}
  ngOnInit() {
    const email = localStorage.getItem('tenantEmail')!;
    this.tenantservice.getData(email).subscribe({
      next: (response) => {
        this.data = response;
      },
      error: (error) => {
        console.log('something went wrong');
      },
    });
  }
}
