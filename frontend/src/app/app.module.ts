import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule }    from '@angular/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';

import { UserService } from './user.service';
import { ProductsService } from './products.service';

import { RouterModule }   from '@angular/router';
import { UsersComponent } from './users/users.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { HomeComponent } from './home/home.component';
import { ProductsComponent } from './products/products.component';
import { ProductListComponent } from './product-list/product-list.component';
import { NewProductComponent } from './new-product/new-product.component';
import { EditProductComponent } from './edit-product/edit-product.component';

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
    EditProductComponent    
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
        path: 'users',
        component: UsersComponent
      },
      {
        path: 'admin',
        component: AdminHomeComponent
      },
      {
        path: 'products',
        component: ProductListComponent  
      },
      {
        path: 'new-product',
        component: NewProductComponent
      },
      {
        path: 'product/:id',
        component: EditProductComponent
      }
    ])
  ],
  providers: [UserService, ProductsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
