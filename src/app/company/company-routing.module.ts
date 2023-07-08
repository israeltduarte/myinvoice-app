import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {
  ListCompanyComponent,
  NewCompanyComponent,
  ViewCompanyComponent,
} from './pages';
import { CompanyComponent } from './pages/company.component';

const routes: Routes = [
  {
    path: '',
    component: CompanyComponent,
  },
  {
    path: 'add',
    component: NewCompanyComponent,
  },
  {
    path: 'list',
    component: ListCompanyComponent,
  },
  {
    path: 'view',
    component: ViewCompanyComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CompanyRoutingModule {}
