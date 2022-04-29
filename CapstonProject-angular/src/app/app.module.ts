import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import{  HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MatButtonModule } from '@angular/material/button'
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { AdminHomepageComponent } from './admin-homepage/admin-homepage.component';
import { UserHomepageComponent } from './user-homepage/user-homepage.component';
import { EcommerceComponent } from './ecommerce/ecommerce.component';
import { OrdersComponent } from './ecommerce/orders/orders.component';
import { ProductsComponent } from './ecommerce/products/products.component';
import { ShoppingCartComponent } from './ecommerce/shopping-cart/shopping-cart.component';
import { SearchFilterPipe } from './SearchPipes/search-filter.pipe';
import { StockReportComponent } from './stock-report/stock-report.component';
import { ShowUsersComponent } from './show-users/show-users.component'
import { RouterModule } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ShowProductsComponent } from './show-products/show-products.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { CsvUploadComponent } from './csv-upload/csv-upload.component';
import { UserProductsComponent } from './user-products/user-products.component';
import { HeaderComponent } from './header/header.component';
import { MdbAccordionModule } from 'mdb-angular-ui-kit/accordion';
import { MdbCarouselModule } from 'mdb-angular-ui-kit/carousel';
import { MdbCheckboxModule } from 'mdb-angular-ui-kit/checkbox';
import { MdbCollapseModule } from 'mdb-angular-ui-kit/collapse';
import { MdbDropdownModule } from 'mdb-angular-ui-kit/dropdown';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { MdbModalModule } from 'mdb-angular-ui-kit/modal';
import { MdbPopoverModule } from 'mdb-angular-ui-kit/popover';
import { MdbRadioModule } from 'mdb-angular-ui-kit/radio';
import { MdbRangeModule } from 'mdb-angular-ui-kit/range';
import { MdbRippleModule } from 'mdb-angular-ui-kit/ripple';
import { MdbScrollspyModule } from 'mdb-angular-ui-kit/scrollspy';
import { MdbTabsModule } from 'mdb-angular-ui-kit/tabs';
import { MdbTooltipModule } from 'mdb-angular-ui-kit/tooltip';
import { MdbValidationModule } from 'mdb-angular-ui-kit/validation';
import { ProductviewComponent } from './productview/productview.component';
import { DatePipe } from './date.pipe';

@NgModule({
  declarations: [
    AppComponent, 
    AdminHomepageComponent,
    UserHomepageComponent,
    EcommerceComponent,
    OrdersComponent,
    ProductsComponent, 
    ShoppingCartComponent, 
    SearchFilterPipe, 
    StockReportComponent,
    ShowUsersComponent, 
    ShowProductsComponent,
    AdminLoginComponent, 
    UserLoginComponent, CsvUploadComponent, UserProductsComponent, HeaderComponent, ProductviewComponent, DatePipe,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,ReactiveFormsModule,HttpClientModule,FormsModule,RouterModule,
    BrowserAnimationsModule,MatButtonModule, MdbAccordionModule, MdbCarouselModule, MdbCheckboxModule, MdbCollapseModule, MdbDropdownModule, MdbFormsModule, MdbModalModule, MdbPopoverModule, MdbRadioModule, MdbRangeModule, MdbRippleModule, MdbScrollspyModule, MdbTabsModule, MdbTooltipModule, MdbValidationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
