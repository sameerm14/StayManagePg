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
  },
  {
    path: 'addrooms',
    component: AddroomsComponent,
  },
  {
    path: 'showrooms',
    component: ShowroomsComponent,
  },
  {
    path: 'tenants',
    component: TenantComponent,
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
  },
  {
    path: 'RentPay',
    component: RentComponent,
  },
  {
    path: 'myRoom',
    component: MyroomComponent,
  },
  {
    path: 'myFood',
    component: ViewFoodComponent,
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
  },
  {
    path: 'services',
    component: AservicesComponent,
  },
  {
    path: 'rent-tracking',
    component: ArentsComponent,
  },
  {
    path: 'viewtenant',
    component: ViewtenantsComponent,
  },
  {
    path: 'notifications',
    component: MessageComponent,
  },
  {
    path: 'viewnotifications',
    component: ViewnotificationsComponent,
  },
  {
    path: 'generatePdf',
    component: GeneratePdfComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
