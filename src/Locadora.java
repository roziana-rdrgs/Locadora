
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/*
 * @author Roziana
 */

public class Locadora {

    private Connection c;

    public Connection getConnection() {
        return c;
    }
    
    //Conecxão como o banco
    public void conectar(String IP, String porta, String database, String usuario, String senha) {
        
        try {   
            String url = "jdbc:mysql://" + IP + ":" + porta + "/" + database + "?user=" + usuario + "&password=" + senha;
            System.out.println(url);
            c = (Connection) DriverManager.getConnection(url);
            
            System.out.println("Conectado!");
        } catch (SQLException e) {System.out.println("Erro: "+ e);
        }
    }

    //Inserir clientes
    public int inserirCliente(Clientes cliente) {
        int resultado = 0;
        try {
            String query = "INSERT INTO CLIENTES (NOME_CLIENTE, ENDERECO, CPF, TELEFONE) "
                    + "VALUES ('" + cliente.getNome() + "', '" + cliente.getEndereco() +"', '" + cliente.getCpf() + "', '" + cliente.getTelefone() + "')";
            Statement st = (Statement) c.createStatement();
            resultado = st.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Erro: "+ e);
        }
        return resultado;
    }

    //Inserir filmes
    public int inserirFilme(Filmes filme) {
        int resultado = 0;
        try {
            String query = "INSERT INTO FILMES (NOME_CLIENTE, ENDERECO, CPF, TELEFONE) "
                    + "VALUES ('" + filme.getTitulo() + "', '" + filme.getTituloOriginal() + 
                                "', '" + filme.getDiretor() + "', '" + filme.getAnoLancamento() + 
                                "', '" + filme.getValorDiaria() +"', '" + filme.getImagem()+ "', '" + filme.getSinopse() +"')";
            Statement st = (Statement) c.createStatement();
            resultado = st.executeUpdate(query);
        } catch (Exception e) {
        }
        return resultado;
    }
    
    //Inserir emprestimos
    public int inserirEmprestimo(Emprestimos emp) {
        int resultado = 0;
        try {
            String query = "INSERT INTO EMPRESTIMOS (ID_CLIENTE, ID_FILME, DATA, DATA_DEVOLUCAO) "
                    + "VALUES ('" + emp.getCliente() + "', '" + emp.getFilme()+ 
                                "', '" + emp.getData() + "', '" + emp.getDataDevolucao()+"')";
            Statement st = (Statement) c.createStatement();
            resultado = st.executeUpdate(query);
        } catch (Exception e) {
        }
        return resultado;
    }
    
    //Consultar emprestimos
    public Emprestimos consultarEmprestimos(String parametro, String condicao, DefaultTableModel model){
        Emprestimos emp = new Emprestimos();
        ResultSet rs = null;
        String query = "SELECT * FROM EMPRESTIMOS";
        if(!parametro.isEmpty()){
            query = query + "WHERE " + parametro + "LIKE '%" + condicao + "%';";
        }else{
            query = query + ";";
        }
        try {
            Statement st = (Statement) c.createStatement();
            rs = st.executeQuery(query);
            try {
                while(rs.next()){
                    emp.setCliente(rs.getString("ID_CLIENTE"));
                    //...
                    
                    model.addRow(new String[]{});
                }
            } catch (Exception e) {
                System.out.println("Erro: "+ e);
            }
        } catch (Exception e) {
             System.out.println("Erro: "+ e);
        }
        return emp;
    }
    
    //Consultar clientes
    public Clientes consultarClientes(String parametro, String condicao, DefaultTableModel model) {
        Clientes cliente = new Clientes();
        ResultSet rs = null;
        String query = "SELECT * FROM CLIENTES";
        if(!condicao.isEmpty()){
            query = query + " WHERE "+  parametro +" LIKE '%"+ condicao +"%';";
        }else{
            query = query + ";";
        }
        try {
            Statement st = (Statement) c.createStatement();
            rs = st.executeQuery(query);
            try {
                while(rs.next()){
                    cliente.setNome(rs.getString("NOME_CLIENTE"));
                    cliente.setCpf(rs.getString("CPF"));
                    cliente.setEndereco(rs.getString("ENDERECO"));
                    System.out.println(cliente.getNome());
                    
                    model.addRow(new String[]{cliente.getNome()});
                }
            } catch (Exception e) {
                System.out.println("Erro: "+ e);
            }
        } catch (Exception e) {
            System.out.println("Erro: "+ e);
        }
        return cliente;
    }
    
    //Consultar filmes
    public Filmes consultarFilmes(String parametro, DefaultTableModel model){
        Filmes filme = new Filmes();
        
        return filme;
    }
    
    //Alterar Filmes
    
    //Alterar Clientes 
    
    //Aterar Emprestimos
    
    //Excluir
    
        
}

