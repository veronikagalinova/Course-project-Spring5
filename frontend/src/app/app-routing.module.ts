import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './_helpers/auth.guard';
import { LoginComponent } from './login/login.component';
import { Role } from './_models/Role';
import { AdminComponent } from './components/admin/admin.component';
import { ProfileComponent } from './components/profile/profile.component';
import { CompanyLinesComponent } from './components/bus-company/company-lines/company-lines.component';
import { MyTicketsComponent } from './components/traveler/my-tickets/my-tickets.component';
import { SearchRouteComponent } from './components/traveler/search-route/search-route.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.ADMIN] }
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'profile',
    component: ProfileComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'company-lines',
    component: CompanyLinesComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.BUS_COMPANY] }
  },
  {
    path: 'tickets',
    component: MyTicketsComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.TRAVELER] }
  },
  {
    path: 'search',
    component: SearchRouteComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.TRAVELER] }
  },

  // otherwise redirect to home
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
