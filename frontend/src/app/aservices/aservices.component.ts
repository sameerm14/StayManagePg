import { Component } from '@angular/core';

@Component({
  selector: 'app-aservices',
  standalone: false,
  templateUrl: './aservices.component.html',
  styleUrl: './aservices.component.css',
})
export class AservicesComponent {
  isActive = false;

  toggleHologram() {
    this.isActive = !this.isActive;
  }
}
