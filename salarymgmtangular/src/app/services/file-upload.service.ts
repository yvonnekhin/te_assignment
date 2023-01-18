import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpErrorResponse, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { OnInit } from '@angular/core';

import { environment } from '../../environments/environment';
import { UserService } from '../services/user.service';
import { User } from '../user';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService implements OnInit {

  public users: User[] = [];

  constructor(private http: HttpClient, private userService: UserService) { }

  private apiServerUrl = environment.apiBaseUrl;

  ngOnInit() {
    //this.getEmployees();
  }

  upload(file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();

    formData.append('file', file);

    const req = new HttpRequest('POST', `${this.apiServerUrl}/users/upload`, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }

  // public getEmployees(): void {
  //   this.userService.getEmployees().subscribe(
  //     (response: User[]) => {
  //       this.users = response;
  //     },
  //     (error: HttpErrorResponse) => {
  //       alert(error.message);
  //     }
  //   );
  // }
}
