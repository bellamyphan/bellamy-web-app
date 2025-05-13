import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { authGuard } from './auth.guard';  // Import the authGuard
import { MainMenuComponent } from './component/main-menu/main-menu.component';
import { UserRegistrationComponent } from './component/user-registration/user-registration.component';
import { UserLoginComponent } from './component/user-login/user-login.component';
import { TransactionListComponent } from './component/transaction-list/transaction-list.component';

const routes: Routes = [
  { path: '', redirectTo: '/main-menu', pathMatch: 'full' },
  { path: 'main-menu', component: MainMenuComponent },
  { path: 'user-login', component: UserLoginComponent },
  { path: 'user-registration', component: UserRegistrationComponent },
  { path: 'money-transactions', component: TransactionListComponent, canActivate: [authGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { onSameUrlNavigation: 'reload' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
