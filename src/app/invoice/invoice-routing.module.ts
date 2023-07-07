import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NewInvoiceComponent } from './pages';
import { InvoiceComponent } from './pages/invoice.component';
import { ViewInvoiceComponent } from './pages/view-invoice/view-invoice.component';

const routes: Routes = [
  {
    path: '',
    component: InvoiceComponent,
  },
  {
    path: 'add',
    component: NewInvoiceComponent,
  },
  {
    path: 'view',
    component: ViewInvoiceComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class InvoiceRoutingModule {}
