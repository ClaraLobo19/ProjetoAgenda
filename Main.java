package ProjetoAgenda;

import java.io.IOException; /*biblioteca para tratamento de erros e excessões*/
import java.util.Scanner; /*biblioteca para entrada de dados*/
import java.util.Date;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub	    
		 Scanner cin= new Scanner(System.in);
		 Contato contato=new Contato();
		 ContatoDAO contatoDAO= new ContatoDAO();
		
		 int op=0;
		 String nome="";
		 int telefone=0, idade=0,codigo=0;
		 
		 
		 while(true) {
			 
			 System.out.println("//////////////Agenda//////////////////// ");
			 System.out.println("Digite a opcao que deseja: ");
			 System.out.println("1-INSERIR CONTATO");
			 System.out.println("2-EXCLUIR CONTATO");
			 System.out.println("3-ATUALIZAR CONTATO");
			 System.out.println("4-MOSTRAR CONTATOS");
			 System.out.println("5-BUSCAR POR ID");
			 System.out.println("6-SAIR");
			
			 op=cin.nextInt();
			 
			 if(op==1) {
				 System.out.println("Nome do contato a ser adicionado");
				 nome=cin.next();
				 contato.setNome(nome);

				 System.out.println("Idade do contato a ser adicionado");
				 idade=cin.nextInt();
				 contato.setIdade(idade);
				
				 System.out.println("Numero do contato a ser adicionado");
				 telefone=cin.nextInt();
				 contato.setTelefone(telefone);
				 
				 
				 contato.setDataCadastro(new Date());
				 
				 contatoDAO.save(contato);
				 System.out.println("***********************Operacao realizada***********************");
			 }
			 
			 else if(op==2) {
				 System.out.println("Nome do contato a ser excluido");
				 
				 try {
					 codigo=cin.nextInt();
					 contatoDAO.removeById(codigo); 
				 } catch(Exception e) {System.out.println("Nenhum contato para excluir"); }
				 
				 System.out.println("***********************Operacao realizada***********************");
			 }
			 
			 else if(op==3) {
				 System.out.println("Digite o codigo do contato a ser atualzado");
				 codigo=cin.nextInt();
				 
				 System.out.println("Digite o nome do novo contato: ");
				 nome=cin.next();
				 contato.setNome(nome);
				 
				 System.out.println("Digite a idade do novo contato: ");
				 idade=cin.nextInt();
				 contato.setIdade(idade);
				 
				 System.out.println("Digite o numero do novo contato: ");
				 telefone=cin.nextInt();
				 contato.setIdade(idade);
				 
				 contato.setDataCadastro(new Date());
				 contatoDAO.update(contato);
				 System.out.println("***********************Operacao realizada***********************");
			 }
			 
			 else if(op==4) {
				 for(Contato c: contatoDAO.getContatos()) {
					 System.out.println("NOME: "+c.getNome());
					 System.out.println("IDADE: "+ c.getIdade());
					 System.out.println("TELEFONE: "+c.getTelefone());
					 System.out.println("DATA CADASTRO: "+ c.getDataCadastro());
				 }			 
			 }
			 
			 else if(op==5) {
				 System.out.println("Digite o codigo para buscar: ");
				 codigo=cin.nextInt();
				 Contato c= contatoDAO.getContatoById(codigo);
				 System.out.println("***********************Operacao realizada***********************");
			 }
			 
			 else if(op==6) { break;} /*OPCAO QUE SAI DO LAÇO*/
			 
			 else { System.out.println("Opcao nao aceita"); }
		 }
	 cin.close();
	 
	}
}