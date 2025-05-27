import { Component } from '@angular/core';
import { AdminService } from '../services/admin.service';
import { response } from 'express';

@Component({
  selector: 'app-viewnotifications',
  standalone: false,
  templateUrl: './viewnotifications.component.html',
  styleUrl: './viewnotifications.component.css',
})
export class ViewnotificationsComponent {
  messages: { title: string; message: string; dateTime: string }[] = [];

  constructor(private adminservice: AdminService) {}

  ngOnInit(): void {
    this.fetchRents();
  }

  fetchRents() {
    this.adminservice.getMessage().subscribe({
      next: (response) => {
        this.messages = response;
      },
      error: (error) => {
        console.log(error);
      },
    });
  }
}
