import { Injectable } from '@angular/core';
import { PromocaoView } from './promocao-view';
import { Promocao } from './promocao';
import { Http } from '@angular/http';
import { UserService } from './user.service';

@Injectable()
export class PromocaoService {
  private urlGetPromocoes = "http://localhost:8080/services/promocao/listar";
  private urlDeletePromocoes = "http://localhost:8080/services/promocao/remover";
  private urlCreatePromocoes = "http://localhost:8080/services/promocao/criar";

  constructor(private http: Http, private userService: UserService){

  }

  getPromocoes(){
    return this.http.get(this.urlGetPromocoes, this.userService.getHeaders())
      .map(result => {
        return result.json().promocoes as PromocaoView[];
      }, error => {
        alert("Ocorreu um erro ao obter promoções");
      });
  }

  criarPromocao(promocao: Promocao){
    var ids = [];
    var produtosSelecionados = promocao.produtos.filter((product, index, produtos) => {
      return product.checked === true;
    });

    ids = produtosSelecionados.map((product, index, products) => {
      return product.id;
    });

    return this.http.post(this.urlCreatePromocoes, 
    { 
      dtInicio: this.convertDateFrom(promocao.dtInicial), 
      dtFim: this.convertDateFrom(promocao.dtFinal),
      idsProdutos: ids,
      peDesconto: promocao.peDesconto 
    }, this.userService.getHeaders())
      .map(result => {
        return result.ok === true;
      });
  }

  excluirPromocao(id){
    return this.http.get(this.urlDeletePromocoes + "/" + id, this.userService.getHeaders())
      .map(result => {
        return result.ok === true;
      });
  }

  private convertDateFrom(stringDate){
    if (stringDate.length < 10) return null;
    new Date()
    return new Date(parseInt(stringDate.substr(6)),
        parseInt(stringDate.substr(3,2)) - 1,
        parseInt(stringDate.substr(0,2)));
  }
}
