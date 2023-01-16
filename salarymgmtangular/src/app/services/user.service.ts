import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../user';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getEmployees(): Observable<any> {
    return this.http.get<any>(`${this.apiServerUrl}/users`);
  }

  public addEmployees(User: User): Observable<User> {
    return this.http.post<User>(`${this.apiServerUrl}/users/add`, User);
  }

  // uploadFile(file: File): Observable<HttpEvent<{}>> {
  //     const data: FormData = new FormData();
  //     data.append('file', file);
  //     const newRequest = new HttpRequest('POST', `${this.apiServerUrl}/users/upload`, data, {
  //     reportProgress: true,
  //     responseType: 'text'
  //     });
  //     return this.http.request(newRequest);
  // }
}
