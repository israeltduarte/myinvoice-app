import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NewInvoiceComponent } from './pages';
import { InvoiceComponent } from './pages/invoice.component';
import { ListInvoiceComponent } from './pages/list-invoice/list-invoice.component';
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
    path: 'list',
    component: ListInvoiceComponent,
  },
  {
    path: 'view',
    component: ViewInvoiceComponent,
  },
  {
    path: 'view/:id',
    component: ViewInvoiceComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class InvoiceRoutingModule {}
