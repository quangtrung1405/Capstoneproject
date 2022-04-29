import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from '../product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-stock-report',
  templateUrl: './stock-report.component.html',
  styleUrls: ['./stock-report.component.css']
})
export class StockReportComponent implements OnInit {
  products:Array<Product> =[];
  users: any;
  constructor(public route:Router,public prodSer:ProductService) { }

  ngOnInit(): void {
    this.getAllproducts();
  }

  getAllproducts():void{
    this.prodSer.getAllProducts().subscribe(res=>this.products=res);
  }

  backtoadminhome():void{
    this.route.navigate(["adminhome"])
  }
  public searchEmployees(key: string): void {
    console.log(key);
    const results: Product[] = [];
    for (const employee of this.products) {
      if (employee.name.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.description.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.price.toString().indexOf(key.toLowerCase()) !== -1) {
        results.push(employee);
      }
    }
    this.users = results;
    if (results.length === 0 || !key) {
      this.getAllproducts();
    }
  }
}
