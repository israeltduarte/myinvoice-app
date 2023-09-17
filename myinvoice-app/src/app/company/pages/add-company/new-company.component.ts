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
    this.newCompany = data.company;
  }

  addNewCompany(): void {
    console.log('add new company');
    this.companyService.addCompany(
      'MI_MIUser_0916af69-a093-4086-ba0b-26376faf4242',
      this.newCompany
    );
    this.resetCompanyForm();
  }

  resetCompanyForm(): void {
    this.newCompany = newEmptyCompany();
  }
}
