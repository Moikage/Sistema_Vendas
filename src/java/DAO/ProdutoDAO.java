/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
public class ProdutoDAO {
    /*
    public static void main(String[] args) {
        Produto p = new Produto();
        ProdutoDAO dao= new ProdutoDAO();
        p= dao.BuscarProduto(4);
        
            System.out.println(p.getDescricao());
    }*/
    public boolean CadastrarProduto(Produto p) {                                                                                                                                                                                 
         Connection con = conexao();
         
         String sql = "insert into produto (descricao, valor) values (?,?)";
        try {
           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(2, p.getDescricao());
            ps.setDouble(3, p.getValor());
            
            if(ps.executeUpdate() == 1)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
           
        }finally{
        try{
        con.close();
        }catch(SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        return false;
    }
    
    public ArrayList<Produto> ListarProdutos(){
        Connection con = conexao();
        String sql = "select *from produto";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            ArrayList<Produto> produtos = new ArrayList();
            while(rs.next()){
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setDescricao(rs.getString("descricao"));
                p.setValor(rs.getDouble("valor"));
                produtos.add(p);
                
            }
          
             return produtos;
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    return null;
    }
    public Produto BuscarProduto(int id_produto){
        Connection con = conexao();
        String sql = "select *from produto where id=?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id_produto);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setDescricao(rs.getString("descricao"));
                p.setValor(rs.getDouble("valor"));
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    return null;
    }
}
