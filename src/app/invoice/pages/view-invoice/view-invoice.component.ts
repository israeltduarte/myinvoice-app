import { animate, style, transition, trigger } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import * as converter from 'number-to-words';
import { Invoice, Item } from 'src/app/core/models';
import {
  ClientService,
  CompanyService,
  InvoiceService,
} from 'src/app/core/services';
import data from '../../../../api/data.json';

@Component({
  selector: 'app-view-invoice',
  providers: [CompanyService, InvoiceService, ClientService],
  templateUrl: './view-invoice.component.html',
  styleUrls: ['./view-invoice.component.css'],
  animations: [
    trigger('fadeOut', [
      transition(':leave', [
        style({ opacity: 1 }),
        animate('300ms', style({ opacity: 0 })),
      ]),
    ]),
  ],
})
export class ViewInvoiceComponent implements OnInit {
  company: any = null;
  client: any = null;
  invoices!: Invoice[];

  selectedInvoice: any = null;
  subtotal: string = '';
  wordsSubtotal: string = '';

  invoiceId: string | null = null;

  constructor(
    private invoiceService: InvoiceService,
    private companyService: CompanyService,
    private clientService: ClientService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.invoices = data.invoices;
    this.route.paramMap.subscribe((params) => {
      this.invoiceId = params.get('id');
      this.client = data.client; //this.clientService.getClient('MyInvoice_Client_01');
    });

    if (this.invoiceId == null) {
      this.selectedInvoice = this.invoices[0];
    } else {
      this.selectedInvoice = this.invoices.find((invoice) => {
        return invoice.id === this.invoiceId;
      });
    }

    this.loadCompanyAndClient();
  }

  loadCompanyAndClient() {
    this.company = data.companies.find((company) => {
      return company.id === this.selectedInvoice?.companyId;
    });
    this.client = data.clients.find((client) => {
      return client.id === this.selectedInvoice?.clientId;
    });
    this.calculateSubTotal();
  }

  calculateSubTotal(): void {
    this.subtotal = this.selectedInvoice.items
      .reduce(
        (accumulator: number, item: Item) =>
          accumulator + item.hours * item.price,
        0
      )
      .toFixed(2);
    this.wordsSubtotal = converter.toWords(this.subtotal);
  }
}
