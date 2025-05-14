import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http'; // Import HttpClientModule
import { NgSelectModule } from '@ng-select/ng-select';  // Import NgSelectModule

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TransactionListComponent } from './component/transaction-list/transaction-list.component';
import { UserLoginComponent } from './component/user-login/user-login.component';
import { AuthInterceptor } from './interceptor/auth.interceptor';
import { MainMenuComponent } from './component/main-menu/main-menu.component';
import { UserRegistrationComponent } from './component/user-registration/user-registration.component';
import { TransactionCreateComponent } from './component/transaction-create/transaction-create.component';
import { SquareComponent } from './component/square/square.component';
import { BoardComponent } from './component/board/board.component';
import { BankCreateComponent } from './component/bank-create/bank-create.component';

@NgModule({
  declarations: [
    AppComponent,
    TransactionListComponent,
    UserLoginComponent,
    MainMenuComponent,
    UserRegistrationComponent,
    TransactionCreateComponent,
    SquareComponent,
    BoardComponent,
    BankCreateComponent
  ],
  imports: [
    BrowserModule, // Import BrowserModule for browser-specific features
    AppRoutingModule, // Import the routing module for navigation
    FormsModule, // Import FormsModule for template-driven forms
    HttpClientModule, // Add HttpClientModule here
    NgSelectModule, 
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }, // Register the interceptor
    provideClientHydration(withEventReplay()), // Keep the original hydration provider
  ],
  bootstrap: [AppComponent] // Bootstrap the AppComponent
})
export class AppModule { }
