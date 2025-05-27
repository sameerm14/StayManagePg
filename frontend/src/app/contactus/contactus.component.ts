import { Component } from '@angular/core';
import { contactService } from '../services/contact.service';

@Component({
  selector: 'app-contactus',
  standalone: false,
  templateUrl: './contactus.component.html',
  styleUrl: './contactus.component.css',
  template: `
    <p>
      <strong>Email:</strong>
      <a [href]="'mailto:' + email">{{ email }}</a>
    </p>
  `,
})
export class ContactusComponent {
  email = 'sameernadaf5761@gmail.com';

  message: String = '';
  messageColor: String = '';
  constructor(private contactservice: contactService) {}
  contactus = {
    fullname: '',
    email: '',
    phone: '',
    subject: '',
    message: '',
  };

  onSubmit() {
    this.contactservice.contactData(this.contactus).subscribe({
      next: (response) => {
        this.message = 'Message Sent Successfully';
        this.messageColor = 'green';
        setTimeout(() => {
          window.location.reload();
        }, 3000);
      },
      error: (error) => {
        this.message = 'Message not sent';
        this.messageColor = 'red';
      },
    });
    this.contactus;
  }
}
