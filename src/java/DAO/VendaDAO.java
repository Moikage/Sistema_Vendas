/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Classes.ItemVenda;
import Classes.Venda;
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
public class VendaDAO {
    /*
    public static void main(String[] args) {
        VendaDAO d = new VendaDAO();
        
        
        for(Venda a: d.VendasCliente(1)){
            System.out.println(a.getData());
        }
        
    }*/
    public boolean CriarVenda(Venda v) {
        Connection con = conexao();

        String sql = "insert into venda (data, cliente_id) values (?,?)";
        try {

            PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, v.getData());
            ps.setInt(2, v.getId_cliente());

            if (ps.executeUpdate() == 1) {
                ResultSet rs = ps.getGeneratedKeys(); 
                
            int id;
            if(rs.first()){
                id = rs.getInt(1);
                ItemVendaDAO dao=new ItemVendaDAO();
                for(ItemVenda it : v.getVendas()){
                    it.setVenda_id(id);
                    dao.CriarItemVenda(it);
                }
                return true;
            }
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

    public ArrayList<Venda> VendasCliente(int id_cliente) {
        Connection con = conexao();
        String sql = "select v.id, v.data, v.cliente_id"
                + " from venda v"
                + " where v.cliente_id =?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_cliente);
            ResultSet rs = ps.executeQuery();

            ArrayList<Venda> vendas = new ArrayList();
            while (rs.next()) {
                Venda v = new Venda();
                ItemVendaDAO item= new ItemVendaDAO();
                v.setId(rs.getInt("v.id"));
                v.setData(rs.getString("v.data"));
                v.setId_cliente(rs.getInt("v.cliente_id"));
                v.setVendas(item.ItensdaVenda(id_cliente, v.getId()));
                
                
                
                vendas.add(v);
            }

            return vendas;

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
    public Venda buscarVenda(int id) {                                                                                                                                                                                 
         Connection con = conexao();
         String sql = "select*from venda where id=?";
        try {
           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Venda v = new Venda();
                v.setId(rs.getInt("id"));
                v.setData(rs.getString("data"));
                return v;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
           
        }finally{
        try{
        con.close();
        }catch(SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        return null;
        
    }
    
}
