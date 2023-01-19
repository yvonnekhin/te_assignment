import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../user';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getEmployees(params: any): Observable<any> {
    return this.http.get<any>(`${this.apiServerUrl}/users`,{params});
  }

  public addEmployees(User: User): Observable<User> {
    return this.http.post<User>(`${this.apiServerUrl}/users/add`, User);
  }

}
