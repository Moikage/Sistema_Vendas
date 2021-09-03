package DAO;

import Classes.Cliente;
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
public class ClienteDAO {
    /*
    public static void main(String[] args) {
        ClienteDAO d= new ClienteDAO();
        System.out.println(d.IDCliente("jhean"));
    }*/
    public boolean LoginCliente(String nome, String senha) {                                                                                                                                                                                 
         Connection con = conexao();
         String sql = "select*from cliente where nome=? and senha=?";
        try {
           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,nome);
            ps.setString(2,senha);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                
                return true;
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
        return false;
        
    }
    public boolean VerificaCliente(String cpf) {                                                                                                                                                                                 
         Connection con = conexao();
         String sql = "select cpf from cliente" ;
        try {
           
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<String> cpfs= new ArrayList();
            if(rs.next()){
                String cp;
                cp=(rs.getString("cpf"));
                cpfs.add(cp);
            }
            for(String cp: cpfs){
                if(cp.equals(cpf))
                    return true;
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
        return false;
        
    }
    public boolean CadastrarCliente(Cliente c) {                                                                                                                                                                                 
         Connection con = conexao();
         if(VerificaCliente(c.getCpf())){
             return false;
         }
         String sql = "insert into cliente (nome, cpf, senha) values (?,?,?)";
        try {
           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getNome());
            ps.setString(2, c.getCpf());
            ps.setString(3, c.getSenha());
            
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
    public ArrayList<Cliente> ListarClientes(){
        Connection con = conexao();
        String sql = "select*from cliente";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            ArrayList<Cliente> clientes = new ArrayList();
            while(rs.next()){
                Cliente cl = new Cliente();
                cl.setCpf(rs.getString("cpf"));
                cl.setNome(rs.getString("nome"));
                cl.setSenha(rs.getString("senha"));
                clientes.add(cl);
                
               
            }
          
             return clientes;
            
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
    public int IDCliente(String nome) {                                                                                                                                                                                 
         Connection con = conexao();
         String sql = "select c.id "
                 + " from cliente c "
                 + " where c.nome =?" ;
        try {
           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,nome);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                int id;
                id=(rs.getInt("c.id"));
                    return id;
            
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
        return 0;
        
    }
}
/*
public Cliente LoginCliente(String cpf, String senha) {                                                                                                                                                                                 
         Connection con = conexao();
         String sql = "select*from cliente where cpf=? and senha=?";
        try {
           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,cpf);
            ps.setString(2,senha);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Cliente cl= new Cliente();
                cl.setCpf(rs.getString("cpf"));
                cl.setNome(rs.getString("nome"));
                cl.setSenha(rs.getString("senha"));
                return cl;
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
*/