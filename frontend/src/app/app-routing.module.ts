import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { AddroomimagesComponent } from './addroomimages/addroomimages.component';
import { ShowroomsComponent } from './showrooms/showrooms.component';
import { AddroomsComponent } from './addrooms/addrooms.component';
import { TenantComponent } from './tenant/tenant.component';
import { TenantRegisterComponent } from './tenant-register/tenant-register.component';
import { TenantloginComponent } from './tenantlogin/tenantlogin.component';
import { FoodComponent } from './food/food.component';
import { RentComponent } from './rent/rent.component';
import { MyroomComponent } from './myroom/myroom.component';
import { ViewFoodComponent } from './view-food/view-food.component';
import { AboutComponent } from './about/about.component';
import { ContactusComponent } from './contactus/contactus.component';
import { MyprofileComponent } from './myprofile/myprofile.component';
import { AservicesComponent } from './aservices/aservices.component';
import { ArentsComponent } from './arents/arents.component';
import { ViewtenantsComponent } from './viewtenants/viewtenants.component';
import { MessageComponent } from './message/message.component';
import { ViewnotificationsComponent } from './viewnotifications/viewnotifications.component';
import { GeneratePdfComponent } from './generate-pdf/generate-pdf.component';
import { authGuard } from './auth.guard';

const routes: Routes = [
  {
    path: '',
    component: SignupComponent,
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'signup',
    component: SignupComponent,
  },
  {
    path: 'adminPanel',
    component: AdminPanelComponent,
  },
  {
    path: 'addroomsomages',
    component: AddroomimagesComponent,
    canActivate: [authGuard],
  },
  {
    path: 'addrooms',
    component: AddroomsComponent,
    canActivate: [authGuard],
  },
  {
    path: 'showrooms',
    component: ShowroomsComponent,
    canActivate: [authGuard],
  },
  {
    path: 'tenants',
    component: TenantComponent,
    canActivate: [authGuard],
  },
  {
    path: 'Tregister',
    component: TenantRegisterComponent,
  },
  {
    path: 'TLogin',
    component: TenantloginComponent,
  },
  {
    path: 'food',
    component: FoodComponent,
    canActivate: [authGuard],
  },
  {
    path: 'RentPay',
    component: RentComponent,
    canActivate: [authGuard],
  },
  {
    path: 'myRoom',
    component: MyroomComponent,
    canActivate: [authGuard],
  },
  {
    path: 'myFood',
    component: ViewFoodComponent,
    canActivate: [authGuard],
  },
  {
    path: 'about',
    component: AboutComponent,
  },
  {
    path: 'contactus',
    component: ContactusComponent,
  },
  {
    path: 'myprofile',
    component: MyprofileComponent,
    canActivate: [authGuard],
  },
  {
    path: 'services',
    component: AservicesComponent,
    canActivate: [authGuard],
  },
  {
    path: 'rent-tracking',
    component: ArentsComponent,
    canActivate: [authGuard],
  },
  {
    path: 'viewtenant',
    component: ViewtenantsComponent,
    canActivate: [authGuard],
  },
  {
    path: 'notifications',
    component: MessageComponent,
    canActivate: [authGuard],
  },
  {
    path: 'viewnotifications',
    component: ViewnotificationsComponent,
    canActivate: [authGuard],
  },
  {
    path: 'generatePdf',
    component: GeneratePdfComponent,
    canActivate: [authGuard],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
