import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { rentService } from '../services/rent.service';
import { TenantService } from '../services/tenant.service';

@Component({
  selector: 'app-rent',
  standalone: false,
  templateUrl: './rent.component.html',
  styleUrl: './rent.component.css',
})
export class RentComponent {
  rent = {
    tno: '',
    pmethod: '',
    phone: '',
    transactionId: '',
  };

  constructor(private tenantservice: TenantService) {}
  isMobile = /Android|iPhone|iPad/i.test(navigator.userAgent);
  payWithUPI(paymentApp: string) {
    let upiId = '';

    if (paymentApp === 'googlepay') {
      upiId = 'snadaf576@okhdfcbank';
    } else if (paymentApp === 'phonepe') {
      upiId = 'snadaf576@pingpay';
    }

    if (this.isMobile) {
      const upiUrl = `upi://pay?pa=${upiId}&pn=RentPayment&mc=123456&tid=789&url=http://localhost:4200/RentPay`;
      window.location.href = upiUrl;
    } else {
      // Desktop or PC logic, show message or redirect to an alternative method
      alert(
        'UPI payment can only be done from a mobile device. Please use Credit/Debit Card for PC.'
      );
    }
  }
  createMonthlyRent() {
    if (!this.rent.tno || !this.rent.pmethod || !this.rent.phone) {
      alert('Please fill in all the fields.');
      return;
    }

    this.tenantservice.Payrent(this.rent).subscribe({
      next: (response) => {
        alert('Rent successfully submitted.');
        console.log(response);
        window.location.reload();
      },
      error: (err) => {
        alert('Error submitting rent.');
        console.error(err);
      },
    });
  }
}
