import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RegisterComponent } from './register/register.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { JwtInterceptor } from './_helpers/jwt.interceptor';
import { NgxMaterialTimepickerModule } from 'ngx-material-timepicker';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { CompanyLinesComponent } from './components/bus-company/company-lines/company-lines.component';
import { AdminComponent } from './components/admin/admin.component';
import { ProfileComponent } from './components/profile/profile.component';
import { MyTicketsComponent } from './components/traveler/my-tickets/my-tickets.component';
import { AuthGuard } from './_helpers/auth.guard';
import { AuthenticationService } from './_services/authentication.service';
import { SearchRouteComponent } from './components/traveler/search-route/search-route.component';
import { RouterModule } from '@angular/router';
import { CreateBusLineComponent } from './components/bus-company/create-bus-line/create-bus-line.component';
import { OverlayContainer } from '@angular/cdk/overlay';

export class CdkOverlayContainer extends OverlayContainer {

  protected _createContainer(): void {
    const container = this._document.createElement('div');

    container.classList.add('cdk-overlay-container', 'test');
    this._document.body.appendChild(container);
    this._containerElement = container;
  }
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    AdminComponent,
    RegisterComponent,
    ProfileComponent,
    CompanyLinesComponent,
    MyTicketsComponent,
    SearchRouteComponent,
    CreateBusLineComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MaterialModule,
    FlexLayoutModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    BrowserAnimationsModule,
    NgxMaterialTimepickerModule
  ],
  providers: [
    AuthenticationService, AuthGuard,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: OverlayContainer, useClass: CdkOverlayContainer }
  ],
  bootstrap: [AppComponent],
  entryComponents: [
    CreateBusLineComponent
  ]
})
export class AppModule { }
