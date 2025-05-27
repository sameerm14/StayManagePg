import { Component } from '@angular/core';

@Component({
  selector: 'app-footer',
  standalone: false,
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css',
  template: `
    <p>
      <strong>Email:</strong>
      <a [href]="'mailto:' + email">{{ email }}</a>
    </p>
  `,
})
export class FooterComponent {
  email = 'sameernadaf5761@gmail.com';
  currentYear: number = new Date().getFullYear();
}
