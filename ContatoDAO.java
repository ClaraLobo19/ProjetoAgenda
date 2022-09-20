package ProjetoAgenda;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class ContatoDAO{
	Connection conn= null;//DEVOLVE UMA  CONEXÃO, NELE CONTÉM MÉTODOS E PROPRIEDADES QUE O MYSQL TEM
	PreparedStatement pstm= null;// RESPONSÁVEL PELA OPERRAÇOES REALIXADAS NO BANCO
	//MÉTODO SAVE
	public void save(Contato contato) { //RECEBE O OBJETO CONTATO
		
		// Isso é uma sql comum, os? são os parâmetros que nós vamos adicionar na base
		//de dados 
		String sql= "INSERT INTO contato(nome,idade,telefone,dataCadastro)"+ " VALUES(?,?,?,?)";
		Connection conn= null;//REPONSÁVEL POR CONECTAR O BANCO. PARA CADA MÉTODO SE INICIALIZA A VARIÁVEL E NO FINAL FECHA ELA
		PreparedStatement pstm= null;//IRÁ FAZER AS OPERAÇÕES NO BD
		
		try{
			conn= Conexao.createConnectionToMySQL();
			pstm= conn.prepareStatement(sql);
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setInt(3, contato.getTelefone());
			pstm.setDate(4, new Date(contato.getDataCadastro().getTime()));
			pstm.execute();
		} catch(Exception e) { e.printStackTrace(); }
		
		finally{
			try{
				if(pstm!= null) {
					pstm.close();
				}
				if(conn!= null) {
					conn.close();
				}
			} catch(Exception e) { e.printStackTrace(); }
		}
	}
	
	
	public void removeById(int id) {
		String sql= "DELETE FROM contato WHERE id = ?";	
		try{
			conn= Conexao.createConnectionToMySQL(); // INICIALIZA A VARIÁVEL: O MÉTODO É ESTATICO, NÃO PRECISOU CRIAR O OBJETO
			pstm= conn.prepareStatement(sql);//PASSA O COMANDO SQL PARA O OBEJTO
			pstm.setInt(1, id);//SET O ID NO COMANDO SLQ
			pstm.execute();//EXECUTA O COMANDO SLQ QUE ESTÁ NO OBJETO PSTM
		} catch(Exception e) { 
			// TODOAuto-generated catch block
			e.printStackTrace();
		  }
		finally {
			try{
				if(pstm!= null) { pstm.close(); }
				if(conn!= null) { conn.close(); }
			} catch(Exception e) { e.printStackTrace(); }
		}	
	}
	
	
	//MÉTODO UPDATE
	public void update(Contato contato) {//RECEBE TODO O OBJETO
		String sql="UPDATE contato SET nome = ?, idade = ?, telefone = ?,dataCadastro = ?" + "WHERE id = ?";// SÃO COLUNAS DA TABELA NO MYSQL
		try{
			conn= Conexao.createConnectionToMySQL(); 
			pstm= conn.prepareStatement(sql);
			// ADICIONAR OS VALORRES DOS PARÂMETROS DA SQL 
			pstm.setString(1, contato.getNome()); 
			pstm.setInt(2, contato.getIdade());
			pstm.setInt(3, contato.getTelefone());
			pstm.setDate(4, new Date(contato.getDataCadastro().getTime()));
			pstm.setInt(5,contato.getId());//QUARTO PARÂMETRO:SET O ID COMO CONDIÇÃO DE ATUALIZAÇÃO DA LINHA QUE VC QUER MUDAR
			
			// EXECUTAR SQL PARA INSERÇÃO DE DADOS 
			pstm.execute();
		} catch(Exception e) { e.printStackTrace(); }
		
		finally{
			// FECHA AS CONEXÕES QUE ESTÃO ABERTAS
			try{
				if(pstm!= null) { pstm.close(); }
				if(conn!= null) { conn.close(); }
			} catch(Exception e) { e.printStackTrace(); }// TRAZER O ERRO CASO O TRY NÃO DER CERTO
		}
	}
	
	public List<Contato> getContatos(){//VISIBILIDADE/TIPO DE RETORNO:RETORNA UMA LISTA DE CONTATOS/NOME DO MÉTODO
		String sql="SELECT *FROM contato";
		List<Contato> contatos=new ArrayList<Contato>();//RECEBERÁ UM COLEÇÃO DO OBJETO CONTATO
		//CLASSE QUE VAI RECUPERAR OS DADOS DO BANCO DE DADOS
		ResultSet rset=null;//RECEBER A COLEÇÃO DA VARIÁVEL CONTATOS
		
		try{
			conn= Conexao.createConnectionToMySQL(); 
			pstm= conn.prepareStatement(sql);
			rset=pstm.executeQuery();//EXECUTA E DEVOLVE O RESULSTAD DENTRO DO rset DO TIPO ResultSet que é uma matriz;
			// ADICIONAR OS VALORRES DOS PARÂMETROS DA SQL 
			
			// IRÁ VARRER O OBEJTO rset, VAI PEGAR OS VALORES, ADICIONAR ELES EM contato E RETORNAR A LISTA  
			while(rset.next()){//ENQUANTO EXISTIR DADOS NO BANCO DE DADOS:percorre várias vezes o rset
				Contato contato=new Contato();//
				//RECUPERA O ID DO BANCO E ATRIBUI A ELE OA OBJETO
				contato.setId(rset.getInt("id"));
				//RECUPERA O NOME DO BANCO E ATRIBUI A ELE OA OBJETO
				contato.setNome(rset.getString("nome"));
				//RECUPERA O ID DO BANCO E ATRIBUI A ELE OA OBJETO
				contato.setTelefone(rset.getInt("telefone"));
				//RECUPERA O ID DO BANCO E ATRIBUI A ELE OA OBJETO
				contato.setIdade(rset.getInt("idade"));
				//RECUPERA A DATA DE CADASTRO DO BANCO E ATRIBUI A ELE OA OBJETO
				contato.setDataCadastro(rset.getDate("dataCadastro"));
				//ADICIONAO O CONTATO RECUPERADO, A LISTA DE CONTATO
				contatos.add(contato);//ADICIONA DENTRO DA LISTA:ARRAYLIST contatos.
			}
			
		} catch(Exception e) { e.printStackTrace(); }
		
		finally{
			// FECHA AS CONEXÕES QUE ESTÃO ABERTAS
			try{
				if(pstm!= null) { pstm.close(); }
				if(conn!= null) { conn.close(); }
			} catch(Exception e) { e.printStackTrace(); }// TRAZER O ERRO CASO O TRY NÃO DER CERTO
		}
		return contatos;
	}
	
	public Contato getContatoById(int id) {
		String sql="SELECT * FROM contato where id=?"; //id=? irá receber o parâmetro
		Contato contato = new Contato();
		ResultSet rset=null; //RECEBER A COLEÇÃO DA VARIÁVEL CONTATOS
		
		try {
			conn= Conexao.createConnectionToMySQL(); 
			pstm= conn.prepareStatement(sql);
			pstm.setInt(1,id);
			rset=pstm.executeQuery();
			
			rset.next();//IRÁ LER OQ TÁ BANCO DE DADOS
			contato.setNome(rset.getString("nome"));
			contato.setTelefone(rset.getInt("telefone"));
			contato.setIdade(rset.getInt("Idade"));
			contato.setDataCadastro(rset.getDate("dataCadastro"));
		} catch(Exception e) { e.printStackTrace(); }
		
		finally{
			// FECHA AS CONEXÕES QUE ESTÃO ABERTAS
			try{
				if(pstm!= null) { pstm.close(); }
				if(conn!= null) { conn.close(); }
			} catch(Exception e) { e.printStackTrace(); }// TRAZER O ERRO CASO O TRY NÃO DER CERTO
		}
		return contato;
	}
}