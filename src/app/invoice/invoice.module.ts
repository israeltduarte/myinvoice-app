import { NgModule } from '@angular/core';
import { SharedModule } from '../shared';
import { InvoiceRoutingModule } from './invoice-routing.module';
import { NewInvoiceComponent } from './pages/add-invoice/new-invoice.component';
import { InvoiceComponent } from './pages/invoice.component';
import { ListInvoiceComponent } from './pages/list-invoice/list-invoice.component';
import { ViewInvoiceComponent } from './pages/view-invoice/view-invoice.component';

@NgModule({
  imports: [SharedModule, InvoiceRoutingModule],
  declarations: [
    NewInvoiceComponent,
    ViewInvoiceComponent,
    InvoiceComponent,
    ListInvoiceComponent,
  ],
})
export class InvoiceModule {}
