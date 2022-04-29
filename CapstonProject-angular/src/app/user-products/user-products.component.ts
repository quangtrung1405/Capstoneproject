import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-user-products',
  templateUrl: './user-products.component.html',
  styleUrls: ['./user-products.component.css']
})
export class UserProductsComponent implements OnInit {
  retrieveUsers: Product[];
  searchKey:string="";
  public searchTerm:string="";
  public filterCategory:any;
  product: Product;
  item: Product;
  username: string | null;

  constructor(private pserv:ProductService) { }

  ngOnInit(): void {
    this.getUsers()
    this.pserv.search.subscribe((val:any)=>{
      this.searchKey=val;
    });
    let res = sessionStorage.getItem("usname");
    if(res!=null){
      this.username=res;
    }
  }
  search(event:any){
    this.searchTerm=(event.target as HTMLInputElement).value;
    console.log(this.searchTerm)
    this.pserv.search.next(this.searchTerm);
  }
  getUsers(): void {
    this.pserv.getAllProducts().subscribe(
      (response: Product[]) => {
        this.retrieveUsers = response;
        this.filterCategory=response;
        this.retrieveUsers.forEach((a:any)=>{
          if(a.category==="clock"||a.category==="shelves"||a.category==="antique")
          Object.assign(a,{quantity:1,total:a.price});
        });
        console.log(this.retrieveUsers);
       
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
filter(category:string){
this.filterCategory=this.retrieveUsers
.filter((a:any)=>{
  if(a.category==category||category==''){
    return a;
  }
});
}
getProduct(id:number){
  return this.pserv.getProductById(id).subscribe((res)=>{
    this.item=res
  })
  
}
}
