import { Component } from '@angular/core';
import { Company, User } from 'src/app/core/models';
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
  company!: Company;
  companies!: Company[];

  constructor(private companyService: CompanyService) {}

  ngOnInit() {
    this.company = this.getCompany('MyInvoice_User_01');
    this.companies = this.getAllCompanies('MyInvoice_User_01');
    // this.companyService.getCompany('MyInvoice_User_01');
    // this.companyService.getAllCompanies('MyInvoice_User_01');
  }

  loadCompany(companyId: string): void {
    this.company = this.companies.find(
      (company) => company.id === companyId
    ) as Company;
  }

  getCompany(userId: string): Company {
    return data.company;
  }

  getAllCompanies(userId: string): Company[] {
    return data.companies;
  }
}
