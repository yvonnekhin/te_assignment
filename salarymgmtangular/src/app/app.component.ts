import { Component, OnInit } from '@angular/core';
import { User } from './user';
import { UserService } from './services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  offset = 1;
  count = 0;
  limit = 30;
  public users: User[] = [];
  totalElements: number = 0;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getEmployees();
  }

  getRequestParams(offset: number, limit: number): any {
    let params: any = {};

    if (offset) {
      params[`offset`] = offset - 1;
    }

    if (limit) {
      params[`limit`] = limit;
    }

    return params;
  }

  handlePageChange(event: number) {
    this.offset = event;
    this.getEmployees();
  }

  getEmployees(): void {
    const params = this.getRequestParams(this.offset, this.limit);

    this.userService.getEmployees(params)
    .subscribe(response => {
      const { users, totalItems } = response;
      this.users = users;
      this.count = totalItems;
      console.log(response);
    },
    error => {
      console.log(error);
    }
    );
  }

}

