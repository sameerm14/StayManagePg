import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './auth.interceptor';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';

import { AddroomimagesComponent } from './addroomimages/addroomimages.component';
import { ShowroomsComponent } from './showrooms/showrooms.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AddroomsComponent } from './addrooms/addrooms.component';
import { TenantComponent } from './tenant/tenant.component';
import { TenantRegisterComponent } from './tenant-register/tenant-register.component';
import { TenantloginComponent } from './tenantlogin/tenantlogin.component';
import { FoodComponent } from './food/food.component';
import { RentComponent } from './rent/rent.component';
import { MyroomComponent } from './myroom/myroom.component';
import { ViewFoodComponent } from './view-food/view-food.component';
import { FooterComponent } from './footer/footer.component';
import { AboutComponent } from './about/about.component';
import { ContactusComponent } from './contactus/contactus.component';
import { LogoutconfirmationComponent } from './logoutconfirmation/logoutconfirmation.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MyprofileComponent } from './myprofile/myprofile.component';
import { ArentsComponent } from './arents/arents.component';
import { AservicesComponent } from './aservices/aservices.component';
import { ViewtenantsComponent } from './viewtenants/viewtenants.component';
import { MessageComponent } from './message/message.component';
import { ViewnotificationsComponent } from './viewnotifications/viewnotifications.component';
import { GeneratePdfComponent } from './generate-pdf/generate-pdf.component';

@NgModule({
  declarations: [
    AppComponent,
    SignupComponent,
    LoginComponent,
    AdminPanelComponent,
    AddroomimagesComponent,
    ShowroomsComponent,
    NavbarComponent,
    AddroomsComponent,
    TenantComponent,
    TenantRegisterComponent,
    TenantloginComponent,
    FoodComponent,
    RentComponent,
    MyroomComponent,
    ViewFoodComponent,
    FooterComponent,
    AboutComponent,
    ContactusComponent,
    LogoutconfirmationComponent,
    MyprofileComponent,
    ArentsComponent,
    AservicesComponent,
    ViewtenantsComponent,
    MessageComponent,
    ViewnotificationsComponent,
    GeneratePdfComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    MatDialogModule,
    MatButtonModule,
    MatIconModule,
    FormsModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
