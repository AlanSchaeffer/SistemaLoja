import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { ProductsService } from '../products.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {
  product: Product;
  private sub: any;
  private loading: boolean = true;
  
  constructor(private productsService: ProductsService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.productsService.getProduct(params['id']).subscribe((result) => {
        this.product = result;
        this.loading = false;
      });
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

}
