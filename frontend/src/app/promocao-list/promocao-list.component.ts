import { Component, OnInit } from '@angular/core';
import { PromocaoService } from '../promocao-service';
import { PromocaoView } from '../promocao-view';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

@Component({
  selector: 'app-promocao-list',
  templateUrl: './promocao-list.component.html',
  styleUrls: ['./promocao-list.component.css']
})
export class PromocaoListComponent implements OnInit {
  loading:boolean = true;
  promocoes:PromocaoView[];

  constructor(private promocaoService: PromocaoService) { }

  ngOnInit() {
    this.loadPromocoes();
  }

  loadPromocoes() {
    this.loading = true;
    this.promocaoService.getPromocoes().subscribe(result => {
      this.promocoes = result;
      console.log(result);
      this.loading = false;
    });
  }

  excluir(id){
    if (confirm("Tem certeza que deseja excluir esta promoção?")){
      this.promocaoService.excluirPromocao(id).subscribe(result => {
        if (!result){
          alert("Ocorreu um erro ao excluir promoção");
        } else {
          alert("Promoção excluída com sucesso");
          this.loadPromocoes();
        }
     });
    }
  }
}
