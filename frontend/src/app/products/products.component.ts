import { Component, OnInit, Input } from '@angular/core';
import { Product } from '../product';
import { ProductsService } from '../products.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  @Input() product: Product;
  constructor(private productsService: ProductsService, private router: Router) { }

  ngOnInit() {
  }

  save() {
    this.productsService.save(this.product).subscribe(result => {
      if (result == true) {
        alert("Produto salvo com sucesso");
        if (this.product.isNew === true) {
          this.router.navigate(['admin/products']);
        }
      } else {
        alert("Ocorreu um erro ao salvar o produto");
      }      
    });
  }
}
