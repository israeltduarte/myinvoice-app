import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { InvoiceComponent } from './invoice/pages/add-invoice/invoice.component';
import { SharedModule } from './shared';

@NgModule({
  imports: [BrowserModule, FormsModule, SharedModule],
  declarations: [AppComponent, InvoiceComponent],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
