// user.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

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

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://127.0.0.1:8080/sneakRproject-1.0-SNAPSHOT/webresources/userek/getAllUsers';

  constructor(private http: HttpClient) { }

  getAllUsers(): Observable<UserApiResponse> {
    return this.http.get<UserApiResponse>(this.apiUrl);
  }
}