// webshop.component.ts
import { Component, OnInit } from '@angular/core';
import { ShoeService } from '../../_services/shoe.service';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-webshop',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  templateUrl: './webshop.component.html',
  styleUrls: ['./webshop.component.css']
})
export class WebshopComponent implements OnInit {
  isMenuOpen = false;
  newShoes: any[] = [];
  bestSellerShoes: any[] = [];
  loading = true;

  constructor(private shoeService: ShoeService) {}

  ngOnInit() {
    this.shoeService.getShoes().subscribe({
      next: (response) => {
        if (response.shoes) {
          // Split shoes into new arrivals and best sellers
          const half = Math.ceil(response.shoes.length / 2);
          this.newShoes = response.shoes.slice(0, half);
          this.bestSellerShoes = response.shoes.slice(half);
        }
        this.loading = false;
      },
      error: (err) => {
        console.error('Error loading shoes:', err);
        this.loading = false;
      }
    });
  }

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
    document.body.style.overflow = this.isMenuOpen ? 'hidden' : 'auto';
  }
}