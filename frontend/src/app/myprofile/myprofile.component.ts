import { Component } from '@angular/core';
import { TenantService } from '../services/tenant.service';
import { response } from 'express';

@Component({
  selector: 'app-myprofile',
  standalone: false,
  templateUrl: './myprofile.component.html',
  styleUrl: './myprofile.component.css',
})
export class MyprofileComponent {
  myRoomData: any = null;
  errorMessage: string = '';
  imageUrl: string = '';
  selectedFile!: File;
  isUploadEnabled: boolean = false;
  previewUrl: string | ArrayBuffer | null = null;

  constructor(private tenantService: TenantService) {}

  ngOnInit(): void {
    const email = localStorage.getItem('tenantEmail');
    if (email) {
      this.tenantService.getMyRoom(email).subscribe({
        next: (data) => {
          this.myRoomData = data;
        },
        error: (error) => {
          this.errorMessage = 'Unable to fetch room details.';
          
        },
      });
    } else {
      this.errorMessage = 'Email not found in local storage.';
    }
  }

  triggerFileInput() {
    const fileInput = document.getElementById('fileInput') as HTMLInputElement;
    fileInput.click();
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.selectedFile = file;
      this.isUploadEnabled = true;
      const reader = new FileReader();
      reader.onload = () => {
        this.previewUrl = reader.result;
      };
      reader.readAsDataURL(file);
    }
  }

  uploadImage() {
    if (!this.selectedFile) {
      alert('Please select an image first.');
      return;
    }

    const formData = new FormData();
    formData.append('image', this.selectedFile);
    const email = localStorage.getItem('tenantEmail');
    if (!email) {
      alert('Tenant email not found in localStorage.');
      return;
    }
    formData.append('temail', email);

    this.tenantService.addTenantImage(formData).subscribe({
      next: (res) => window.location.reload(),
      error: (err) => console.error('Upload error:', err),
    });
  }
}
