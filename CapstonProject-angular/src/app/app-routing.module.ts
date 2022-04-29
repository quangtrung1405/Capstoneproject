import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminHomepageComponent } from './admin-homepage/admin-homepage.component';
import { EcommerceComponent } from './ecommerce/ecommerce.component';
import { ShoppingCartComponent } from './ecommerce/shopping-cart/shopping-cart.component';
import { ShowUsersComponent } from './show-users/show-users.component';
import { StockReportComponent } from './stock-report/stock-report.component';
import { ShowProductsComponent } from './show-products/show-products.component';
import { UserHomepageComponent } from './user-homepage/user-homepage.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { UserProductsComponent } from './user-products/user-products.component';
import { HeaderComponent } from './header/header.component';
import { ProductviewComponent } from './productview/productview.component';

const routes: Routes = [
  {path:"adminlogin",component:AdminLoginComponent},
  {path:"userlogin",component:UserLoginComponent},
  {path:"adminhome",component:AdminHomepageComponent},
  {path:"userhome",component:UserHomepageComponent},
  {path:'ecommerce',component:EcommerceComponent},
  {path:"stockreport",component:StockReportComponent},
  {path:"shoppingcart",component:ShoppingCartComponent},
  {path:"showusers",component:ShowUsersComponent},
  {path:"products",component:ShowProductsComponent},
  {path:"userproducts",
        children:[
       {
         path:"",
         component:UserProductsComponent
       },
       {
         path:"id",
         component:ProductviewComponent
       }
        ]
  },
  {path:"",component:HeaderComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
