import { Component } from '@angular/core';
import { AdminService } from '../services/admin.service';

@Component({
  selector: 'app-showrooms',
  standalone: false,
  templateUrl: './showrooms.component.html',
  styleUrl: './showrooms.component.css',
})
export class ShowroomsComponent {
  message: String = '';
  messagecolor: String = '';
  rooms: any[] = [];

  selectedFloor: number | null = null;
  uniqueFloors: number[] = [];

  constructor(private adminservice: AdminService) {}

  ngOnInit(): void {
    this.adminservice.getRooms().subscribe({
      next: (response) => {
        this.rooms = response;
        this.extractUniqueFloors();
      },
      error: (error) => {
        console.error('Error fetching rooms:', error);
      },
    });
  }
  removeRoom(roomNumber: string): void {
    this.adminservice.removeRoom(roomNumber).subscribe({
      next: (response) => {
        this.rooms = this.rooms.filter(
          (room) => room.roomNumber !== roomNumber
        );
        this.extractUniqueFloors(); // Recalculate floors if room removed
      },
      error: (error) => {
        console.error('Error removing room:', error);
      },
    });
  }

  extractUniqueFloors(): void {
    const floorSet = new Set<number>();
    this.rooms.forEach((room) => {
      if (room.floor !== undefined && room.floor !== null) {
        floorSet.add(room.floor);
      }
    });
    this.uniqueFloors = Array.from(floorSet).sort((a, b) => a - b);
  }

  selectFloor(floor: number): void {
    this.selectedFloor = floor;
  }

  getRoomsByFloor(floor: number): any[] {
    return this.rooms.filter((room) => room.floor === floor);
  }
}
