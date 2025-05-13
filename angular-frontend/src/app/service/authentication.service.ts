import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { jwtDecode } from 'jwt-decode';
import { AppConfig } from '../app-config';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private baseUrl = AppConfig.springApiUrl + "/api/auth/login"; // Base URL for the Auth API
  private usernameSubject = new BehaviorSubject<string | null>(null); // Subject to hold username
  username$ = this.usernameSubject.asObservable(); // Observable for username

  constructor(private http: HttpClient) { 
    this.loadUserFromToken(); // Load username from token on service initialization
  }

  // Retrieve the JWT token from localStorage
  getToken(): string | null {
    return localStorage.getItem('jwt_token');
  }

  // Store the JWT token in localStorage after a successful login
  storeToken(token: string): void {
    localStorage.setItem('jwt_token', token);
    this.loadUserFromToken(); // Load username from token after storing it
  }

  // Login method, it sends the username and password to the backend
  login(username: string, password: string): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.post<any>(this.baseUrl, { username, password }, { headers });
  }

  // Clear the token from localStorage when the user logs out
  logout(): void {
    localStorage.removeItem('jwt_token');
    this.usernameSubject.next(null); // Clear the username from BehaviorSubject
  }

  // Load username from the JWT token and update the BehaviorSubject
  private loadUserFromToken(): void {
    const token = this.getToken();
    if (token) {
      try {
        const decodedToken: any = jwtDecode(token);
        const isExpired = decodedToken.exp * 1000 < Date.now(); // Check if token is expired
        if (isExpired) {
          this.logout(); // Logout if token is expired
        } else {
          this.usernameSubject.next(decodedToken.sub); // Update the BehaviorSubject with the username
        }
      } catch (error) {
        console.error('Error decoding token:', error);
        this.usernameSubject.next(null); // Set username to null if token is invalid
      }
    } else {
      this.usernameSubject.next(null); // Set username to null if no token
    }
  }
}
