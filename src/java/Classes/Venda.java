/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;
//import java.util.Date;

/**
 *
 * @author jhean
 */
public class Venda {
    private int id;
    private String data;/*
    SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");    
    Date data = fmt.parse("17/12/2007 19:30:20"); 
    String str = fmt.format(data); */
    private ArrayList<ItemVenda> vendas=new ArrayList();; 
    private int id_cliente;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ArrayList<ItemVenda> getVendas() {
        return vendas;
    }

    public void setVendas(ArrayList<ItemVenda> vendas) {
        this.vendas = vendas;
    }
    public String dados(){
        String aux="";
        for(ItemVenda item: vendas){
            aux+=item.getProduto().Dados()+", Qtd: "+item.getQtd()+", Desc: "+item.getDesconto();
        }
        return "Data: "+data+aux;
    }
    public double total(){
        double aux=0;
        for(ItemVenda item: vendas){
            aux+=item.total();
        }
        return aux;
    }/*
    public double desconto(){
        double aux=0;
        for(ItemVenda item: vendas){
            aux+=item.desconto();
        }
        return aux;
    }
    public double Totaldesconto(){
        double aux=0;
        for(ItemVenda item: vendas){
            aux+=item.totalComdesconto();
        }
        return aux;
    }*/

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
