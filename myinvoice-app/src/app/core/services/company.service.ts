import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map } from 'rxjs';
import { Company } from '../models/company';

@Injectable()
export class CompanyService {
  baseURL: string = 'http://localhost:8080/mi-myinvoice-back/api';

  constructor(private http: HttpClient) {}

  getAllCompaniesByUserId(userId: string): Observable<Company[]> {
    return this.http
      .get<Company[]>(this.baseURL + '/users/' + userId + '/companies')
      .pipe(
        map((companies) => {
          console.log(companies);
          return companies;
        }),
        catchError(() => {
          return [];
        })
      );
  }

  getCompanyByUserId(userId: string, companyId: string): Observable<Company> {
    return this.http
      .get<Company>(
        this.baseURL + '/users/' + userId + '/companies' + companyId
      )
      .pipe(
        map((company) => {
          console.log(company);
          return company;
        }),
        catchError(() => {
          return [];
        })
      );
  }

  addCompany(userId: string, company: Company): Observable<Company> {
    return this.http.post<Company>(this.baseURL + '/companies', company).pipe(
      map((company) => {
        console.log(company);
        return company;
      }),
      catchError(() => {
        return [];
      })
    );
  }

  updateCompany(
    userId: string,
    companyId: string,
    company: Company
  ): Observable<Company> {
    return this.http.put<Company>(
      this.baseURL + '/company' + companyId,
      company
    );
  }

  deleteCompany(userId: string, companyId: string): void {
    this.http.delete<Company>(
      this.baseURL + 'user/' + userId + '/company' + companyId
    );
  }
}
