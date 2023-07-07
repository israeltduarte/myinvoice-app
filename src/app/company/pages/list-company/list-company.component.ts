import { Component } from '@angular/core';
import { Company, User } from 'src/app/core/models';
import { CompanyService } from 'src/app/core/services';
import data from '../../../../api/data.json';

@Component({
  selector: 'app-list-company',
  providers: [CompanyService],
  templateUrl: './list-company.component.html',
  styleUrls: ['./list-company.component.css'],
})
export class ListCompanyComponent {
  user!: User;
  companies!: Company[];

  constructor(private companyService: CompanyService) {}

  ngOnInit() {
    this.companies = this.getAllCompanies('MyInvoice_User_01');
    // this.companyService.getAllCompanies('MyInvoice_User_01');
  }

  getAllCompanies(userId: string) {
    return data.companies;
  }
}
