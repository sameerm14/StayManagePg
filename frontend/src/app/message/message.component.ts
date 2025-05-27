import { Component } from '@angular/core';
import { AdminService } from '../services/admin.service';
import { response } from 'express';

@Component({
  selector: 'app-message',
  standalone: false,
  templateUrl: './message.component.html',
  styleUrl: './message.component.css',
})
export class MessageComponent {
  constructor(private adminservice: AdminService) {}

  message: String = '';
  messageColor: String = '';

  notification = {
    title: '',
    message: '',
    dateTime: new Date(),
  };
  sendNotification() {
    this.adminservice.sendNotification(this.notification).subscribe({
      next: (response) => {
        this.message = 'Notification sent Successfull !';
        this.messageColor = 'green';
        setTimeout(() => {
          window.location.reload();
        }, 3000);
      },
      error: (error) => {
        this.message = 'Notification not sent !';
        this.messageColor = 'red';
      },
    });
  }
}
