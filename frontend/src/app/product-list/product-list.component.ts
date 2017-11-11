import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { ProductsService } from '../products.service';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  loading: boolean = true;
  filter: string = "";
  products: Product[];
  selectedProduct: Product = new Product();

  constructor(private productsService: ProductsService) { }

  ngOnInit() {
    this.productsService.getProducts(this.filter).subscribe(result => {
      this.products = result;
      this.loading = false;
    });
  }

  onSelect(product: Product) {
    this.selectedProduct = product;
  }

  search() {
    this.loading = true;

    this.productsService.getProducts(this.filter).subscribe(result => {
      this.products = result;
      this.loading = false;
    });
  }
}
