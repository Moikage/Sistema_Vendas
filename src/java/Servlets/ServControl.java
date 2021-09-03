/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.Cliente;
import Classes.ItemVenda;
import Classes.Produto;
import Classes.Venda;
import DAO.ClienteDAO;
import DAO.ItemVendaDAO;
import DAO.ProdutoDAO;
import DAO.VendaDAO;
import java.io.IOException;
import java.util.ArrayList;
//import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jhean
 */
@WebServlet(name = "ServControl", urlPatterns = {"/ServControl"})
public class ServControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String /*erro="",*/ caminho = "", nome;

        if (request.getParameter("var").equals("login")) {
            nome = request.getParameter("nome");
            String senha = request.getParameter("senha");
            ClienteDAO DAOcli = new ClienteDAO();
            if (DAOcli.LoginCliente(nome, senha)) {
                request.getSession().setAttribute("nome", nome);
                caminho = "/jsp/Inicio.jsp";
            } else {
                //erro="CPF ou senha inválidos";
                caminho = "index.html";
            }
        } else if (request.getParameter("var").equals("inicio")) {
            caminho = "/jsp/Inicio.jsp";
        } else if (request.getParameter("var").equals("listar")) {
            ProdutoDAO dao = new ProdutoDAO();
            request.setAttribute("produtos", dao.ListarProdutos());
            caminho = "jsp/Produtos.jsp";
        } else if (request.getParameter("var").equals("buscar")) {
            VendaDAO dao = new VendaDAO();
            ClienteDAO DAOcli = new ClienteDAO();
            request.setAttribute("venda", dao.VendasCliente(DAOcli.IDCliente(request.getParameter("n"))));
            caminho = "jsp/Vendas.jsp";
        } else if (request.getParameter("var").equals("criar")) {
            ProdutoDAO dao = new ProdutoDAO();
            request.setAttribute("produtos", dao.ListarProdutos());
            caminho = "jsp/FormVenda.jsp";
        } else if (request.getParameter("var").equals("respcriar")) {

            if (request.getParameter("op").equals("add")) {
                
                Venda v= (Venda)request.getSession().getAttribute("v");
                if(v==null){
                    ProdutoDAO dao= new ProdutoDAO();
                    v = new Venda();
                    v.setData(request.getParameter("data"));
                    Produto p;
                    p = dao.BuscarProduto(Integer.parseInt(request.getParameter("prod")));
                    ItemVenda i = new ItemVenda();
                    i.setProduto(p);
                    i.setQtd(Integer.parseInt(request.getParameter("qtd")));
                    i.setDesconto(Double.parseDouble(request.getParameter("desc")));
                    v.getVendas().add(i);
                }
                else{
                    ProdutoDAO dao= new ProdutoDAO();
                    Produto p;
                    p = dao.BuscarProduto(Integer.parseInt(request.getParameter("prod")));
                    ItemVenda i = new ItemVenda();
                    i.setProduto(p);
                    i.setQtd(Integer.parseInt(request.getParameter("qtd")));
                    i.setDesconto(Double.parseDouble(request.getParameter("desc")));
                    v.getVendas().add(i);
                    
                }
                request.getSession().setAttribute("v",v);
                
                caminho="ServControl?var=criar";
                
                
            } else if (request.getParameter("op").equals("fim")) {
                VendaDAO d = new VendaDAO();
                ClienteDAO c= new ClienteDAO();
                Venda v= (Venda)request.getSession().getAttribute("v");
                v.setId_cliente(c.IDCliente((String)request.getSession().getAttribute("nome")));
                d.CriarVenda(v);
                //v= (Venda)request.getSession().invalidate();
                request.getSession().removeAttribute("v");
                caminho = "jsp/Inicio.jsp";
            }
        }
         else if (request.getParameter("var").equals("item")) {
            int idvenda = Integer.parseInt(request.getParameter("vid"));
            int idcliente = Integer.parseInt(request.getParameter("idcl"));
            ItemVendaDAO i = new ItemVendaDAO();
            VendaDAO d= new VendaDAO();
            Venda v;
            v= d.buscarVenda(idvenda);
            v.setVendas(i.ItensdaVenda(idcliente, idvenda));
            request.setAttribute("item", v);
            caminho = "jsp/Items.jsp";
        } else if (request.getParameter("var").equals("sair")) {
            request.getSession().invalidate();
            caminho = "index.html";
        }
        else if (request.getParameter("var").equals("cancel")) {
            request.getSession().removeAttribute("v");
                caminho = "/jsp/Inicio.jsp";
        }
        else if (request.getParameter("var").equals("del")) {
            Venda v= (Venda)request.getSession().getAttribute("v");
            int i= Integer.parseInt(request.getParameter("op"));
            v.getVendas().remove(i);
            //ProdutoDAO dao = new ProdutoDAO();
            //request.setAttribute("produtos", dao.ListarProdutos());
            caminho = "ServControl?var=criar";
        }
        //request.setAttribute("MSGerro", erro);
        RequestDispatcher rd = request.getRequestDispatcher(caminho);
        rd.forward(request, response);
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
