import { Component } from '@angular/core';
import { Company, newEmptyCompany } from 'src/app/core/models';
import { User } from 'src/app/core/models/user';
import { CompanyService } from 'src/app/core/services';
import data from '../../../../api/data.json';

@Component({
  selector: 'app-new-company',
  providers: [CompanyService],
  templateUrl: './new-company.component.html',
  styleUrls: ['./new-company.component.css'],
})
export class NewCompanyComponent {
  user!: User;
  newCompany: Company = newEmptyCompany();

  constructor(private companyService: CompanyService) {}

  ngOnInit() {
    this.newCompany = data.company; //this.companyService.getCompany('MyInvoice_Company_01');
  }

  addNewCompany(): void {
    // this.companyService.addCompany(this.user.id, this.newCompany);
    this.resetCompanyForm();
  }

  resetCompanyForm(): void {
    this.newCompany = newEmptyCompany();
  }
}
