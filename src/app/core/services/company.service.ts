import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Company } from '../models/company';

@Injectable()
export class CompanyService {
  baseURL: string = 'localhost:8080/';

  constructor(private http: HttpClient) {}

  getCompany(userId: string, companyId: string): Observable<Company> {
    return this.http.get<Company>(
      this.baseURL + 'user/' + userId + '/company' + companyId
    );
  }

  getCompanys(userId: string): Observable<Company[]> {
    return this.http.get<Company[]>(
      this.baseURL + '/user' + userId + '/company'
    );
  }

  addCompany(userId: string, company: Company): Observable<Company> {
    return this.http.post<Company>(
      this.baseURL + 'user/' + userId + '/company',
      company
    );
  }

  updateCompany(
    userId: string,
    companyId: string,
    company: Company
  ): Observable<Company> {
    return this.http.put<Company>(
      this.baseURL + 'user/' + userId + '/company' + companyId,
      company
    );
  }

  deleteCompany(userId: string, companyId: string): void {
    this.http.delete<Company>(
      this.baseURL + 'user/' + userId + '/company' + companyId
    );
  }
}
