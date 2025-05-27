import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AdminService } from '../services/admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-addroomimages',
  standalone: false,
  templateUrl: './addroomimages.component.html',
  styleUrl: './addroomimages.component.css',
})
export class AddroomimagesComponent {
  addroomimage: FormGroup;

  selectedFiles: File[] = [];
  message: String = '';
  messagecolor: String = '';
  previewUrls: string[] = [];
  constructor(private fb: FormBuilder, private adminservice: AdminService) {
    this.addroomimage = this.fb.group({
      roomNumber: [''],
    });
  }

  onFileChange(event: any) {
    if (event.target.files && event.target.files.length > 0) {
      this.selectedFiles = Array.from(event.target.files);

      const files = event.target.files;
      this.previewUrls = [];

      for (let file of files) {
        const reader = new FileReader();
        reader.onload = (e: any) => {
          this.previewUrls.push(e.target.result);
        };
        reader.readAsDataURL(file);
      }
    }
  }

  onSubmit() {
    const roomNumber = this.addroomimage.value.roomNumber;

    if (!roomNumber) {
      this.message = 'Room number is required.';
      this.messagecolor = 'red';
      return;
    }

    const formData = new FormData();
    formData.append('roomnumber', roomNumber);
    this.selectedFiles.forEach((file) => {
      formData.append('images', file);
    });

    this.adminservice.uploadRoomImages(formData).subscribe({
      next: (response) => {
        this.message = 'Images uploaded successfully!';
        this.messagecolor = 'green';
      },
      error: (error) => {
        this.message = 'Upload failed. Please try again.';
        this.messagecolor = 'red';
      },
    });
  }
}
