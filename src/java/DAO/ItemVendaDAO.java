/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Classes.ItemVenda;
import Classes.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.Conexao;
import static jdbc.Conexao.conexao;

/**
 *
 * @author jhean
 */
public class ItemVendaDAO {
    /*
    public static void main(String[] args) {
        ItemVendaDAO i= new ItemVendaDAO();
        ArrayList<ItemVenda> a= new ArrayList();
        a=i.ItensdaVenda("123", 1);
        
    }*/
    public boolean CriarItemVenda(ItemVenda i) {
        Connection con = conexao();

        String sql = "insert into item_venda (qtd, desconto, produto_id, venda_id) values (?,?,?,?);";
        try {

            PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, i.getQtd());
            ps.setDouble(2, i.getDesconto());
            ps.setInt(3, i.getProduto().getId());
            ps.setInt(4, i.getVenda_id());

           ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys(); 
            
            if(rs.first()){
                    return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;

    }

    public ArrayList<ItemVenda> ItensdaVenda(int id_cliente, int id_venda) {
        Connection con = conexao();
        String sql = "select i.id, p.descricao, p.valor, i.qtd, i.desconto, v.id, p.id"
                + " from item_venda i"
                + " inner join produto p on i.produto_id = p.id"
                + " inner join venda v on i.venda_id = v.id"
                + " inner join cliente c on v.cliente_id = c.id"
                + " where v.cliente_id =? and v.id=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_cliente);
            ps.setInt(2, id_venda);
            ResultSet rs = ps.executeQuery();

            ArrayList<ItemVenda> items = new ArrayList();
            while (rs.next()) {
                Produto p= new Produto();
                ItemVenda i = new ItemVenda();
                i.setId(rs.getInt("i.id"));
                p.setDescricao(rs.getString("p.descricao"));
                p.setId(rs.getInt("p.id"));
                p.setValor(rs.getDouble("p.valor"));
                i.setProduto(p);
                i.setQtd(rs.getInt("i.qtd"));
                i.setDesconto(rs.getInt("i.desconto"));
                i.setVenda_id(rs.getInt("v.id"));
                items.add(i);

            }

            return items;

        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {

                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
