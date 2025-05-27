import { Component } from '@angular/core';

@Component({
  selector: 'app-about',
  standalone: false,
  templateUrl: './about.component.html',
  styleUrl: './about.component.css',
  template: `
    <p>
      <strong>Email:</strong>
      <a [href]="'mailto:' + email">{{ email }}</a>
    </p>
  `,
})
export class AboutComponent {
  email = 'sameernadaf5761@gmail.com';
}
