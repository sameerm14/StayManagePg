import { Component } from '@angular/core';
import { AdminService } from '../services/admin.service';

@Component({
  selector: 'app-generate-pdf',
  standalone: false,
  templateUrl: './generate-pdf.component.html',
  styleUrl: './generate-pdf.component.css',
})
export class GeneratePdfComponent {
  selectedYear: number = new Date().getFullYear();
  selectedMonth: number = new Date().getMonth() + 1;
  reportMessage: string = '';

  constructor(private adminservice: AdminService) {}
  months = [
    { name: 'January', value: 1 },
    { name: 'February', value: 2 },
    { name: 'March', value: 3 },
    { name: 'April', value: 4 },
    { name: 'May', value: 5 },
    { name: 'June', value: 6 },
    { name: 'July', value: 7 },
    { name: 'August', value: 8 },
    { name: 'September', value: 9 },
    { name: 'October', value: 10 },
    { name: 'November', value: 11 },
    { name: 'December', value: 12 },
  ];

  generateRentReport() {
    this.adminservice
      .generateRentReport(this.selectedYear, this.selectedMonth)
      .subscribe({
        next: (blob: Blob) => {
          // Create a URL for the PDF blob and trigger download
          const url = window.URL.createObjectURL(blob);
          const a = document.createElement('a');
          a.href = url;
          a.download = `rentReport_${this.selectedYear}_${this.selectedMonth}.pdf`;
          a.click();
          window.URL.revokeObjectURL(url);
          this.reportMessage = 'PDF report downloaded successfully!';
          setTimeout(() => {
            window.location.reload();
          }, 2000);
        },
        error: (error) => {
          this.reportMessage = 'Failed to generate report.';
          console.error(error);
        },
      });
  }
}
