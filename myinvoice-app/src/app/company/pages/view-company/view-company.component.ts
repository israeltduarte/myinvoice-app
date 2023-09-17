import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { Company } from 'src/app/core/models';
import { CompanyService } from 'src/app/core/services';

@Component({
  selector: 'app-view-company',
  providers: [CompanyService],
  templateUrl: './view-company.component.html',
  styleUrls: ['./view-company.component.css'],
})
export class ViewCompanyComponent {
  selectedCompany!: Company;
  companies$!: Observable<Company[]>;

  constructor(private companyService: CompanyService) {}

  ngOnInit() {
    let userId = 'MI_MIUser_0916af69-a093-4086-ba0b-26376faf4242';
    this.getAllCompanies(userId);

    this.companies$.subscribe((companies: Company[]) => {
      if (companies.length > 0) {
        this.selectedCompany = companies[0];
      }
    });
  }

  getAllCompanies(userId: string): void {
    this.companies$ = this.companyService.getAllCompaniesByUserId(userId);
  }

  getCompany(userId: string, companyId: string) {
    this.companyService
      .getCompanyByUserId(userId, companyId)
      .subscribe((company: Company) => {
        this.selectedCompany = company;
      });
  }
}
