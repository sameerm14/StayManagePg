import { Component } from '@angular/core';
import { AdminService } from '../services/admin.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-addrooms',
  standalone: false,
  templateUrl: './addrooms.component.html',
  styleUrl: './addrooms.component.css',
})
export class AddroomsComponent {
  addroomform: FormGroup;
  floors: string[] = [
    '1st Floor',
    '2nd Floor',
    '3rd Floor',
    '4th Floor',
    '5th Floor',
  ];
  uniqueFloors: string[] = [];
  roomsOnSelectedFloor: any[] = [];
  selectedFloor: string = '';
  rooms: any[] = [];
  allRooms: any[] = [];
  message: String = '';
  messagecolor: String = '';

  constructor(
    private adminService: AdminService,
    private fb: FormBuilder,
    private router: Router
  ) {
    this.addroomform = this.fb.group({
      roomNumber: [null, Validators.required],
      totalbeds: [null, Validators.required],
      floor: ['', Validators.required],
    });
  }
  extractFloors() {
    const floorsSet = new Set(this.allRooms.map((room) => room.floor));
    this.uniqueFloors = Array.from(floorsSet);
  }

  ngOnInit(): void {
    this.adminService.getAllRooms().subscribe({
      next: (rooms) => {
        console.log('All rooms:', rooms);
        this.allRooms = rooms;
        this.extractFloors();
      },
      error: (err) => console.error('Error fetching rooms:', err),
    });
  }

  onSubmit() {
    this.adminService.addrooms(this.addroomform.value).subscribe({
      next: (response) => {
        this.message = 'Room Added Successfully';
        this.messagecolor = 'green';
        this.addroomform.reset();
        this.fetchRooms();
      },
      error: (error) => {
        this.message = 'Room is already added';
        this.messagecolor = 'red';
      },
    });
  }
  fetchRooms() {
    this.adminService.getAllRooms().subscribe({
      next: (data: any[]) => {
        console.log('Rooms fetched from backend:', data);


        this.allRooms = data;
        if (this.selectedFloor) {
          this.filterRoomsByFloor(this.selectedFloor);
        }
      },
      error: (err) => {
        console.error('Failed to load rooms', err);
      },
    });
  }
  selectFloor(floor: string) {
    this.selectedFloor = floor;
    this.roomsOnSelectedFloor = this.allRooms.filter(
      (room) => room.floor === floor
    );
  }
  filterRoomsByFloor(floor: string) {
    this.rooms = this.allRooms.filter((room) => room.floor === floor);
  }
}
