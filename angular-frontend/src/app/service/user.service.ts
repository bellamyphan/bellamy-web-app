import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConfig } from '../app-config';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = AppConfig.springApiUrl; // Base URL for the API
  private userUrl = `${this.baseUrl}/api/users`; // URL for users endpoint

  constructor(private http: HttpClient) {}

  registerUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.userUrl}`, user);
  }
}
