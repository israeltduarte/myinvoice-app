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
  searchText: string = '';

  constructor(private companyService: CompanyService) {}

  ngOnInit() {
    this.companies = this.getAllCompanies('MyInvoice_User_01');
    // this.companyService.getAllCompanies('MyInvoice_User_01');
  }

  getAllCompanies(userId: string) {
    return data.companies;
  }

  get filteredCompanies(): Company[] {
    return this.companies.filter((company: Company) => {
      // Filter logic based on attributes
      const searchTerm = this.searchText.toLowerCase();
      return (
        company.name.toLowerCase().includes(searchTerm) ||
        company.fantasyName.toLowerCase().includes(searchTerm) ||
        company.number.toLowerCase().includes(searchTerm) ||
        company.address1.toLowerCase().includes(searchTerm) ||
        company.address2.toLowerCase().includes(searchTerm) ||
        company.city.toLowerCase().includes(searchTerm) ||
        company.state.toLowerCase().includes(searchTerm) ||
        company.country.toLowerCase().includes(searchTerm) ||
        company.zipCode.toLowerCase().includes(searchTerm) ||
        company.phone.toLowerCase().includes(searchTerm) ||
        company.email.toLowerCase().includes(searchTerm)
      );
    });
  }
}
