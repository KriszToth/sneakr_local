// webshop.component.ts
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ShoeService } from '../../_services/shoe.service';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-webshop',
  standalone: true,
  imports: [CommonModule, HttpClientModule, RouterLink],
  templateUrl: './webshop.component.html',
  styleUrls: ['./webshop.component.css']
})
export class WebshopComponent implements OnInit {
  isMenuOpen = false;
  newShoes: any[] = [];
  bestSellerShoes: any[] = [];
  menuState: { [key: string]: boolean } = {};
  loading = true;

  constructor(private shoeService: ShoeService) {}

  @ViewChild('best') bestSection!: ElementRef;
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
    if (this.isMenuOpen) {
      document.body.style.overflow = 'hidden';
    } else {
      document.body.style.overflow = 'auto';
      this.menuState = {}; // Reset submenü állapotok
    }
  }

  toggleSubmenu(menuKey: string) {
    this.menuState[menuKey] = !this.menuState[menuKey];
  }

  scrollToBest() {
    if (this.bestSection) {
      this.bestSection.nativeElement.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }
  }
}