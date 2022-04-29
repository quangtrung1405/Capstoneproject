import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, NgForm } from '@angular/forms';
import { Product } from '../product'
import { ProductService } from '../product.service';

@Component({
  selector: 'app-show-products',
  templateUrl: './show-products.component.html',
  styleUrls: ['./show-products.component.css']
})
export class ShowProductsComponent implements OnInit {
  reactiveForm:any=FormGroup;
  userFile:any=File;
  retrieveUsers:Array<Product>=[]
  editUser:Product;
  delUser:Product
  id: number;
  deleteUs: any;
  searchKey:string="";

  constructor(private pserv:ProductService) { }

  ngOnInit(): void {
    this.getUsers()
  }
  search(event:any){

  }
   getUsers(): void {
    this.pserv.getAllProducts().subscribe(
      (response: Product[]) => {
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
    this.pserv.storeProductInfo(addForm.value).subscribe(
      (response: Product) => {
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

  public onUpdateUser(employee: Product): void {
    this.pserv.updateProductInfo(employee).subscribe(
      (response: Product) => {
        console.log(response);
        this.getUsers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteUser(id: number): void {
    this.pserv.deleteProduct(id).subscribe(
      (response) => {
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
    const results: Product[] = [];
    for (const employee of this.retrieveUsers) {
      if (employee.name.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.description.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.category.toString().indexOf(key.toLowerCase()) !== -1) {
        results.push(employee);
      }
    }
    this.retrieveUsers = results;
    if (results.length === 0 || !key) {
      this.getUsers();
    }
  }


  public onOpenModal(employee: Product, mode: string): void {
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



