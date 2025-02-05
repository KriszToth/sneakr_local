import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NavbarComponent } from '../navbar/navbar.component';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-resell-sell',
  standalone: true,
  imports: [NavbarComponent, FormsModule, CommonModule],
  templateUrl: './resell-sell.component.html',
  styleUrls: ['./resell-sell.component.css']
})
export class ResellSellComponent {
  constructor(
    private router: Router,
    private http: HttpClient
  ) {}

  showSuccessModal: boolean = false;
  showLinkModal: boolean = false;
  imageUrl: string = '';
  imageError: boolean = false;
  
  openLinkModal(): void {
    this.showLinkModal = true;
  }

  closeLinkModal(): void {
    this.showLinkModal = false;
  }

  confirmImage(): void {
    if(this.imageUrl) {
      this.imageError = false;
      this.closeLinkModal();
    }
  }

  onCancel(): void {
    this.router.navigate(['/resell']);
  }

  onSubmit(form: NgForm): void {
    if (form.invalid || !this.imageUrl) {
        form.control.markAllAsTouched();
        this.imageError = !this.imageUrl;
        return;
    }

    const postData = {
        nev: form.value.nev,
        marka: form.value.marka,
        nem: form.value.nem,
        allapot: form.value.allapot,
        meret: form.value.meret.toString(),
        ar: form.value.ar.toString(),
        img: this.imageUrl
    };

    this.http.post<any>(
        'http://127.0.0.1:8080/sneakRproject-1.0-SNAPSHOT/webresources/cipok/uploadShoes',
        postData
    ).subscribe({
        next: (response) => {
            if (response.status === 'success') {
                this.showSuccessModal = true;
                form.resetForm();
                this.imageUrl = '';
            }
        },
        error: (error) => {
            console.error('Hiba a feltöltés során:', error);
            alert('Hiba történt a feltöltés során!');
        }
    });
}

onCloseSuccessModal(): void {
    this.showSuccessModal = false;
    this.router.navigate(['/resell']); // Or your resell route
}
}