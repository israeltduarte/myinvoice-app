import { NgModule } from '@angular/core';
import { SharedModule } from '../shared';
import { CompanyRoutingModule } from './company-routing.module';
import { NewCompanyComponent } from './pages';
import { CompanyComponent } from './pages/company.component';

@NgModule({
  imports: [SharedModule, CompanyRoutingModule],
  declarations: [NewCompanyComponent, CompanyComponent],
})
export class CompanyModule {}