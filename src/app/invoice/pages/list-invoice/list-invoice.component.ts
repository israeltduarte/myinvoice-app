import { animate, style, transition, trigger } from '@angular/animations';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Client, Company, Invoice } from 'src/app/core/models';
import {
  ClientService,
  CompanyService,
  InvoiceService,
} from 'src/app/core/services';
import data from '../../../../api/data.json';

@Component({
  selector: 'app-list-invoice',
  providers: [InvoiceService, ClientService, CompanyService],
  templateUrl: './list-invoice.component.html',
  styleUrls: ['./list-invoice.component.css'],
  animations: [
    trigger('fadeOut', [
      transition(':leave', [
        style({ opacity: 1 }),
        animate('300ms', style({ opacity: 0 })),
      ]),
    ]),
  ],
})
export class ListInvoiceComponent {
  companies!: Company[];
  clients!: Client[];
  invoices!: Invoice[];

  selectedCompany: string = '';
  selectedClient: string = '';
  filteredInvoices!: Invoice[];

  constructor(
    private invoiceService: InvoiceService,
    private companyService: CompanyService,
    private clientService: ClientService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.companies = data.companies;
    this.clients = data.clients;
    this.invoices = data.invoices;
    this.applyFilters();
  }

  applyFilters(): void {
    this.filteredInvoices = this.invoices.filter((invoice) => {
      const companyFilter =
        this.selectedCompany === ''
          ? true
          : invoice.companyId === this.selectedCompany;
      const clientFilter =
        this.selectedClient === ''
          ? true
          : invoice.clientId === this.selectedClient;

      return companyFilter && clientFilter;
    });
  }

  editInvoice(invoice: Invoice): void {
    this.router.navigate(['/invoice/edit', invoice.id]);
  }

  removeInvoice(invoice: Invoice): void {
    if (confirm('Are you sure you want to remove this invoice?')) {
      const index = this.invoices.findIndex((item) => item === invoice);
      if (index !== -1) {
        this.invoices.splice(index, 1);
        this.applyFilters();
      }
    }
  }
}
