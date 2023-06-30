import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Client } from '../models';

@Injectable()
export class ClientService {
  baseURL: string = 'localhost:8080/';

  constructor(private http: HttpClient) {}

  getClients(userId: string): Observable<Client[]> {
    return this.http.get<Client[]>(this.baseURL + '/user' + userId + '/client');
  }

  getClient(userId: string, clientId: string): Observable<Client> {
    return this.http.get<Client>(
      this.baseURL + '/user' + userId + '/client' + clientId
    );
  }

  addClient(userId: string, client: Client): Observable<Client> {
    return this.http.post<Client>(
      this.baseURL + 'user/' + userId + '/client',
      client
    );
  }

  updateClient(
    userId: string,
    clientId: string,
    client: Client
  ): Observable<Client> {
    return this.http.put<Client>(
      this.baseURL + 'user/' + userId + '/client' + clientId,
      client
    );
  }

  deleteClient(userId: string, clientId: string): void {
    this.http.delete<Client>(
      this.baseURL + 'user/' + userId + '/client' + clientId
    );
  }
}
