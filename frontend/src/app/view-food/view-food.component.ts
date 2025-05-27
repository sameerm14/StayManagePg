import { Component } from '@angular/core';
import { foodService } from '../services/food.service';

@Component({
  selector: 'app-view-food',
  standalone: false,
  templateUrl: './view-food.component.html',
  styleUrl: './view-food.component.css',
})
export class ViewFoodComponent {
  constructor(private foodService: foodService) {}

  foodItems: any[] = [];

  ngOnInit(): void {
    this.MyFood();
  }

  MyFood() {
    this.foodService.getAllFoods().subscribe({
      next: (response) => {
        this.foodItems = response;
      },
      error: (error) => {
        console.error('Error fetching food items:', error);
      },
    });
  }
}
