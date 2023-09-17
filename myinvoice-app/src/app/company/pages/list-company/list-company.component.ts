import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { Company, User } from 'src/app/core/models';
import { CompanyService } from 'src/app/core/services';

@Component({
  selector: 'app-list-company',
  providers: [CompanyService],
  templateUrl: './list-company.component.html',
  styleUrls: ['./list-company.component.css'],
})
export class ListCompanyComponent {
  user!: User;
  companies$!: Observable<Company[]>;
  searchText: string = '';

  constructor(private companyService: CompanyService) {}

  ngOnInit() {
    console.log('initiated');
    this.getAllCompanies('MI_MIUser_0916af69-a093-4086-ba0b-26376faf4242');
  }

  getAllCompanies(userId: string): void {
    this.companies$ = this.companyService.getAllCompaniesByUserId(userId);
  }

  // get filteredCompanies(): Observable<Company[]> {
  //   return this.companies$.pipe(
  //     map((companies: Company[] | undefined) => {
  //       if (!companies) {
  //         return [];
  //       }

  //       const searchTerm = this.searchText.toLowerCase();
  //       return companies.filter((company: Company) => {
  //         return (
  //           company.name.toLowerCase().includes(searchTerm) ||
  //           company.fantasyName.toLowerCase().includes(searchTerm) ||
  //           company.number.toLowerCase().includes(searchTerm) ||
  //           company.address1.toLowerCase().includes(searchTerm) ||
  //           company.address2.toLowerCase().includes(searchTerm) ||
  //           company.city.toLowerCase().includes(searchTerm) ||
  //           company.state.toLowerCase().includes(searchTerm) ||
  //           company.country.toLowerCase().includes(searchTerm) ||
  //           company.zipCode.toLowerCase().includes(searchTerm) ||
  //           company.phone.toLowerCase().includes(searchTerm) ||
  //           company.email.toLowerCase().includes(searchTerm)
  //         );
  //       });
  //     })
  //   );
  // }
}
