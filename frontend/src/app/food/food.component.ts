import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

import { foodService } from '../services/food.service';

@Component({
  selector: 'app-food',
  standalone: false,
  templateUrl: './food.component.html',
  styleUrl: './food.component.css',
})
export class FoodComponent {
  constructor(private http: HttpClient, private foodservice: foodService) {}
  food = {
    mealType: '',
    foodName: '',
    foodDescription: '',
    mealStartTime: '',
    mealEndTime: '',
    dayOfWeek: '',
    imageUrl: '',
  };

  message: String = '';
  messageColor: String = '';

  selectedFiles: File[] = [];
  days: string[] = [
    'Monday',
    'Tuesday',
    'Wednesday',
    'Thursday',
    'Friday',
    'Saturday',
    'Sunday',
  ];

  onFileChange(event: any) {
    if (event.target.files && event.target.files.length > 0) {
      this.selectedFiles = Array.from(event.target.files);
    }
  }

  onSubmit() {
    const formData = new FormData();

    formData.append('mealType', this.food.mealType);
    formData.append('foodName', this.food.foodName);
    formData.append('foodDescription', this.food.foodDescription || '');
    formData.append('mealStartTime', this.food.mealStartTime);
    formData.append('mealEndTime', this.food.mealEndTime);
    formData.append('dayOfWeek', this.food.dayOfWeek);

    for (let i = 0; i < this.selectedFiles.length; i++) {
      formData.append('imageFile', this.selectedFiles[i]);
    }

    this.foodservice.addOrUpdateFood(formData).subscribe({
      next: () => {
        this.message = 'Food added Successfully';
        this.messageColor = 'green';
        window.location.reload();
      },
      error: () => {
        this.message = 'Food not added';
        this.messageColor = 'red';
      },
    });
  }
}
