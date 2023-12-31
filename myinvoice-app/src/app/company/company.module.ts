import { NgModule } from '@angular/core';
import { SharedModule } from '../shared';
import { CompanyRoutingModule } from './company-routing.module';
import {
  ListCompanyComponent,
  NewCompanyComponent,
  ViewCompanyComponent,
} from './pages';
import { CompanyComponent } from './pages/company.component';

@NgModule({
  imports: [SharedModule, CompanyRoutingModule],
  declarations: [
    NewCompanyComponent,
    ListCompanyComponent,
    CompanyComponent,
    ViewCompanyComponent,
  ],
})
export class CompanyModule {}
