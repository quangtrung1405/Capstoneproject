import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserEntity } from '../user-entity';
import { UserService } from '../user.service';

@Component({
  selector: 'app-show-users',
  templateUrl: './show-users.component.html',
  styleUrls: ['./show-users.component.css']
})
export class ShowUsersComponent implements OnInit {
  retrieveUsers:Array<UserEntity>=[]
  editUser:UserEntity;
  delUser:UserEntity
  id: number;
  deleteUs: any;

  constructor(private UsServ:UserService) { }

  ngOnInit(): void {
    this.getUsers()
  }
   getUsers(): void {
    this.UsServ.getAllUsers().subscribe(
      (response: UserEntity[]) => {
        this.retrieveUsers = response;
        console.log(this.retrieveUsers);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public onAddUser(addForm: NgForm): void {
    document.getElementById('add-employee-form')?.click();
    this.UsServ.addUser(addForm.value).subscribe(
      (response: UserEntity) => {
        console.log(response);
        this.getUsers();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onUpdateUser(employee: UserEntity): void {
    this.UsServ.updateUser(employee).subscribe(
      (response: UserEntity) => {
        console.log(response);
        this.getUsers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteUser(id: number): void {
    this.UsServ.deleteUserInfo(id).subscribe(
      (response: void) => {
        console.log(response);
        this.getUsers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public searchUser(key: string): void {
    console.log(key);
    const results: UserEntity[] = [];
    for (const employee of this.retrieveUsers) {
      if (employee.username.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.email.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.contact.toString().indexOf(key.toLowerCase()) !== -1
      || employee.password.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(employee);
      }
    }
    this.retrieveUsers = results;
    if (results.length === 0 || !key) {
      this.getUsers();
    }
  }

  public onOpenModal(employee: UserEntity, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addEmployeeModal');
    }
    if (mode === 'edit') {
      this.editUser = employee;
      button.setAttribute('data-target', '#updateEmployeeModal');
    }
    if (mode === 'delete') {
      this.delUser = employee;
      button.setAttribute('data-target', '#deleteEmployeeModal');
    }
    container!.appendChild(button);
    button.click();
  }


}


