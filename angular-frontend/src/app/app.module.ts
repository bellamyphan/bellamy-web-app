import { NgModule, importProvidersFrom } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainMenuComponent } from './component/main-menu/main-menu.component';
import { UserRegistrationComponent } from './component/user-registration/user-registration.component';
import { UserLoginComponent } from './component/user-login/user-login.component';
import { provideHttpClient } from '@angular/common/http';
import { TransactionListComponent } from './component/transaction-list/transaction-list.component'; // ✅ New import

@NgModule({
  declarations: [
    AppComponent,
    MainMenuComponent,
    UserRegistrationComponent,
    UserLoginComponent,
    TransactionListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [
    provideHttpClient() // ✅ New recommended approach
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
