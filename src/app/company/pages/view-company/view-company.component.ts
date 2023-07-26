import { Component, OnInit } from '@angular/core';
import { Company, User, newEmptyCompany } from 'src/app/core/models';
import { CompanyService } from 'src/app/core/services';
import data from '../../../../api/data.json';

@Component({
  selector: 'app-view-company',
  providers: [CompanyService],
  templateUrl: './view-company.component.html',
  styleUrls: ['./view-company.component.css'],
})
export class ViewCompanyComponent {
  user!: User;
  selectedCompany!: Company;
  companies!: Company[];

  constructor(private companyService: CompanyService) {}

  ngOnInit() {
    this.companies = this.getAllCompanies('MyInvoice_User_01');
    this.selectedCompany = this.companies[0];
  }

  getCompany(userId: string): Company {
    return data.company;
  }

  getAllCompanies(userId: string): Company[] {
    return data.companies;
  }
}
