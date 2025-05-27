import { Component } from '@angular/core';
import { AdminService } from '../services/admin.service';

@Component({
  selector: 'app-arents',
  standalone: false,
  templateUrl: './arents.component.html',
  styleUrl: './arents.component.css',
})
export class ArentsComponent {
  rents: any[] = [];
  paidRents: any[] = [];
  unpaidRents: any[] = [];
  currentDate: Date = new Date();
  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.fetchRents();
  }

  fetchRents() {
    this.adminService.getRentDetails().subscribe({
      next: (response) => {
        console.log(response);
        this.rents = response;
        this.paidRents = response.filter(
          (rents: any) => rents.rentStatus === true
        );
        this.unpaidRents = response.filter(
          (rents: any) => rents.rentStatus === false
        );
      },
      error: (error) => {
        console.log('something happened');
      },
    });
  }
}
