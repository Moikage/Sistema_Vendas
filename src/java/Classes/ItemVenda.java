/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author jhean
 */
public class ItemVenda {
    private int id;
    private int qtd;
    private double desconto;
    private Produto produto;
    private int venda_id;

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public double total(){
        double aux=0;
        if(desconto>0){
            aux=produto.getValor()*qtd;
        aux-= (produto.getValor()*qtd*(desconto/100));
        return aux;
        }
        else{
        aux=produto.getValor()*qtd;
        return aux;
        }
    }/*
    public double desconto(){
        double aux;
        aux=produto.getValor()*qtd*desconto;
        return aux;
    }*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVenda_id() {
        return venda_id;
    }

    public void setVenda_id(int venda_id) {
        this.venda_id = venda_id;
    }
}
