import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { UserService } from '../../_services/user.service';
import { Order } from '../../models/order.model';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-webshop-user',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  templateUrl: './webshop-user.component.html',
  styleUrls: ['./webshop-user.component.css'],
  providers: [UserService]
})
export class WebshopUserComponent implements OnInit {
  user: User | null = null;
  orders: Order[] = [];
  loading: boolean = true;
  error: string | null = null;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.loadUserData();
  }

  loadUserData() {
  this.loading = true; // Reset loading state
  this.userService.getAllUsers().subscribe({
    next: (response) => {
      if (response.users?.length > 0) {
        const firstUser = response.users[0];
        console.log('Received user data:', firstUser); // Add debug log
        
        this.user = {
          id: firstUser.id,
          name: firstUser.nev,
          email: firstUser.email,
          avatar: 'assets/default-avatar.png',
          admin: firstUser.admin === 'igen'
        };
      }
      this.loading = false;
    },
    error: (error) => {
      console.error('Error loading user data:', error); // Add error logging
      this.error = 'Nem sikerült betölteni az adatokat.';
      this.loading = false;
    }
  });
}



// Add this interface in the component file


  // private mockOrders(): Order[] {
  //   // Temporary mock data - remove when real orders are implemented
  //   return [
  //     { id: 1, date: new Date('2024-03-15'), total: 29990, status: 'Delivered' },
  //     { id: 2, date: new Date('2024-03-20'), total: 45990, status: 'Shipped' }
  //   ];
  // }

  getStatusIcon(status: string): string {
    switch (status.toLowerCase()) {
      case 'pending': return 'fas fa-clock';
      case 'shipped': return 'fas fa-truck';
      case 'delivered': return 'fas fa-check-circle';
      case 'cancelled': return 'fas fa-times-circle';
      default: return '';
    }
  }

  getOrderStatusClass(status: string): string {
    switch (status.toLowerCase()) {
      case 'pending': return 'pending';
      case 'shipped': return 'shipped';
      case 'delivered': return 'delivered';
      case 'cancelled': return 'cancelled';
      default: return '';
    }
  }
}

interface UserApiResponse {
  users: {
    id: number;
    nev: string;
    email: string;
    jelszo: string;
    admin: string;
  }[];
  statusCode: number;
}