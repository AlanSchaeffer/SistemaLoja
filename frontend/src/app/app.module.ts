import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule }    from '@angular/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';

import { UserService } from './user.service';
import { ProductsService } from './products.service';
import { CartService } from './cart-service';
import { OrderService } from './order-service';

import { RouterModule }   from '@angular/router';
import { UsersComponent } from './users/users.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { HomeComponent } from './home/home.component';
import { ProductsComponent } from './products/products.component';
import { ProductListComponent } from './product-list/product-list.component';
import { NewProductComponent } from './new-product/new-product.component';
import { EditProductComponent } from './edit-product/edit-product.component';
import { CustomerHomeComponent } from './customer-home/customer-home.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { CustomerOrdersComponent } from './customer-orders/customer-orders.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UsersComponent,
    AdminHomeComponent,
    HomeComponent,
    ProductsComponent,
    ProductListComponent,
    NewProductComponent,
    EditProductComponent,
    CustomerHomeComponent,
    ShoppingCartComponent,
    CustomerOrdersComponent
  ],
  imports: [
    HttpModule,
    BrowserModule,
    FormsModule,
    RouterModule.forRoot([
      {
        path: '',
        component: HomeComponent  
      },
      {
        path: 'login',
        component: LoginComponent
      },
      {
        path: 'admin/login',
        component: LoginComponent
      },
      {
        path: 'users',
        component: UsersComponent
      },
      {
        path: 'customer',
        component: CustomerHomeComponent
      },
      {
        path: 'customer-orders',
        component: CustomerOrdersComponent
      },
      {
        path: 'shopping-cart',
        component: ShoppingCartComponent
      },
      {
        path: 'admin',
        component: AdminHomeComponent
      },
      {
        path: 'admin/products',
        component: ProductListComponent  
      },
      {
        path: 'admin/products/new',
        component: NewProductComponent
      },
      {
        path: 'admin/products/:id',
        component: EditProductComponent
      },
      {
        path: 'admin/orders',
        component: CustomerOrdersComponent
      }
    ])
  ],
  providers: [UserService, ProductsService, CartService, OrderService],
  bootstrap: [AppComponent]
})
export class AppModule { }
