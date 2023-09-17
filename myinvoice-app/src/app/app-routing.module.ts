import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompanyModule } from './company';
import { AboutComponent, HomeComponent } from './home';
import { InvoiceModule } from './invoice';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'invoice', loadChildren: () => InvoiceModule },
  { path: 'company', loadChildren: () => CompanyModule },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],

})
export class AppRoutingModule {}
