
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class Interface{
	
	private JFrame tela;
	private ListaAnimais listaAnimais;
	private ListaAnimais listaCadastrado;
	private ListaAnimais ultimaBusca;
	private ListaContas listaContas;
	private Conta contaLogada;
	
	public Interface() {
		tela=new JFrame("PET Adoption - App");
		listaAnimais=new ListaAnimais();
		listaContas=new ListaContas();
		listaCadastrado=new ListaAnimais();
		contaLogada=new Conta(0,"","","","");
		try {
		     GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts//SansitaOne.ttf")));

		} catch (Throwable e) {
		     //Handle exception
		}
	}
	
	private Painel PainelEntrada() {
		Painel entradaPainel=new Painel("img/bg_entrar.png");


	    JButton pessoalBtn=entradaPainel.addButton("Conta Pessoal",100,190);
	    JButton institBtn=entradaPainel.addButton("Conta Institucional",100,270);
	    
	    pessoalBtn.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    	    OpenNewPainel(PainelPessoal());
	    	  } 
	    	} );
	    institBtn.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    	    OpenNewPainel(PainelInstitucional());
	    	  } 
	    	} );
	    return entradaPainel;
	}
	
	
	// Sess�o Pessoal
	private Painel PainelPessoal() {
		Painel p=new Painel("img/bg_solido.png");

	    p.addLabelWhite("Conta Pessoal",100,100);

	    JButton loginBtn=p.addButton("Login",100,170);
	    JButton cadastroBtn=p.addButton("Cadastro",100,240);
	    JButton backBtn=p.addButton("Voltar",100,400);
	    
	    loginBtn.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    	    OpenNewPainel(LoginPessoal());
	    	  } 
	    	} );
	    cadastroBtn.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    	    OpenNewPainel(CadastroPessoal());
	    	  } 
	    	} );
	    backBtn.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    	    OpenNewPainel(PainelEntrada());
	    	  } 
	    	} );
	    return p;
	}
	
	private Painel LoginPessoal() {
		Painel p=new Painel("img/bg_solido.png");

	    p.addLabelWhite("Login - Pessoal",100,100);
	    JTextField email=p.addTextField("Login:",100, 170);
	    JPasswordField senha= p.addPasswordField("Senha:",100, 220);
	    
	    JButton enterAppBtn=p.addButton("Entrar",100,280);
	    JButton backBtn=p.addButton("Voltar",100,400);
	    JLabel errorLabel=p.addLabelWhite("",130,235,12);
	    enterAppBtn.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		  String emailTxt=email.getText();
	    		  String senhaTxt = new String(senha.getPassword());
	    		  if(!emailTxt.equals("")) {
		    		  if(listaContas.contaPessoalExistente(emailTxt)) {
		    			  if((contaLogada=listaContas.checkSenha(emailTxt, senhaTxt))!=null) {
		    				  	listaCadastrado=contaLogada.GetCadastrados();
		    		    	    OpenNewPainel(PessoalMenu());
		    			  }else {
		    				  errorLabel.setText("Senha incorreta.");
		    				  senha.requestFocus();
		    			  }
		    		  }else {
		    			  errorLabel.setText("Conta inexistente.");
		    			  email.requestFocus();
		    		  }
		    			  
	    		  }else {
	    			  email.requestFocus();
	    		  }

	    			  
	    			  
	    	  } 
	    	} );
	    backBtn.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    	    OpenNewPainel(PainelPessoal());
	    	  } 
	    	} );
	    return p;
	}
	
	private Painel CadastroPessoal() {
		Painel p=new Painel("img/bg_solido.png");

	    p.addLabelWhite("Cadastro - Pessoal",100,100);
	    JTextField email=p.addTextField("Login:",100, 170);
	    JTextField telefone=p.addTextField("Telefone:",100, 210);
	    JTextField endereco=p.addTextField("Endere�o:",100, 250);
	    
	    JPasswordField senha= p.addPasswordField("Senha:",100, 290);

	    JPasswordField confSenha= p.addPasswordField("Conf. Senha:",100, 320);
	    JButton enterAppBtn=p.addButton("Cadastrar",100,400);
	    JButton backBtn=p.addButton("Voltar",100,500);
	    JLabel errorLabel=p.addLabelWhite("",130,280,12);
	    enterAppBtn.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		String emailTxt=email.getText();
	    		String telefoneTxt=telefone.getText();
	    		String enderecoTxt=endereco.getText();
	    		String senhaTxt = new String(senha.getPassword());
	    		String senha2Txt = new String(confSenha.getPassword());
	    		if(emailTxt.equals("") || telefoneTxt.equals("")|| enderecoTxt.equals("")) {
	    			  errorLabel.setText("Login inv�lido.");
	    			  email.requestFocus();
	    		}else if(!listaContas.contaExistente(emailTxt)) {
	    			  if(senhaTxt.length()>3 && senhaTxt.equals(senha2Txt)) {
	    				  listaContas.addPessoa(telefoneTxt, enderecoTxt, emailTxt, senhaTxt);
	    		    	    OpenNewPainel(LoginPessoal());
	    			  }else {
	    				  errorLabel.setText("Senha incorreta.");
	    				  senha.requestFocus();
	    			  }
	    		  }else {
	    			  errorLabel.setText("Conta j� existe.");
	    			  email.requestFocus();
	    		  }
	    	  } 
	    	} );
	    backBtn.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    	    OpenNewPainel(PainelPessoal());
	    	  } 
	    	} );
	    return p;
	}
	
	private Painel PessoalMenu() {
		Painel p=new Painel("img/bg_red_rect2.png");

	    p.addLabelRed("O que deseja fazer?",80,100);

	    JButton adotarUmBtn=p.addButton("Adotar um bichinho",75,170,200);
	    JButton cadastrarUmBtn=p.addButton("Cadastrar um bichinho",75,240,200);
	    JButton backBtn=p.addButton("Voltar",100,400);
	    
	    adotarUmBtn.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    	    OpenNewPainel(AdotarMenu());
	    	  } 
	    	} );
	    cadastrarUmBtn.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    	    OpenNewPainel(CadastroMenu());
	    	  } 
	    	} );
	    backBtn.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    	    OpenNewPainel(PainelPessoal());
	    	  } 
	    	} );
	    return p;
	}
	
	private Painel AdotarMenu() {
		Painel p=new Painel("img/bg_red_rect_patas.png");

	    p.addLabelWhite("Qual bichinho deseja adotar?",35,40);

	    JButton caoBtn=p.addButton("C�o",100,140);
	    JButton gatoBtn=p.addButton("Gato",100,210);
	    JButton semPrefBtn=p.addButton("Sem prefer�ncia",100,280);
	    JButton backBtn=p.addButton("Voltar",100,400);
	    caoBtn.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    	    OpenNewPainel(PainelPesquisar(1));
	    	  } 
	    	} );
	    gatoBtn.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    	    OpenNewPainel(PainelPesquisar(2));
	    	  } 
	    	} );
	    semPrefBtn.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    	    OpenNewPainel(PainelPesquisar(0));
	    	  } 
	    	} );
	    backBtn.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    	    OpenNewPainel(PessoalMenu());
	    	  } 
	    	} );
	    return p;
	}
	
	private Painel CadastroMenu() {
		Painel p=new Painel("img/bg_solido.png");

		JScrollPane scroll=p.addScrollPane(-30,60);
		Painel animais=new Painel("img/bg_solido2.png");
	    p.addLabelWhite("Meus cadastros",20,5);

	    JButton backBtn=p.addButton("Voltar",170,500);
	    JButton cadastrarBtn=p.addButton("Adicionar novo",10,500);

	    cadastrarBtn.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    	    OpenNewPainel(CadastrarAnimal());
	    	  } 
	    	} );
	    backBtn.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		  OpenNewPainel(PessoalMenu());
	    	  } 
	    	} );
	    
		animais.ChangeSize(new Dimension(300, 550));
		animais.setLayout(new WrapLayout());
		
		int i=0;
	    for (Animal animal : listaCadastrado.getAll()) {
		    JButton perfilBtn=animais.addButtonFlow(animal.getNome(),300,50);
		    perfilBtn.addActionListener(new ActionListener() { 
		    	public void actionPerformed(ActionEvent e) { 
		    		OpenNewPainel(PerfilAnimalEditavel(animal,CadastroMenu()));
		    	} 
		    } );
		    i++;
		    if(i>9)
		    	break;
	    }
		scroll.setViewportView(animais);
		return p;
	}
	
	private Painel CadastrarAnimal() {
		Painel p = new Painel("img/bg_solido2.png");
	    p.addLabelRed("Cadastro", 35, 20);

	    String tipos[] = { "Gato", "Cachorro"};
	    String sexos[] = { "Macho", "F�mea"};
	    
	    JTextField nome = p.addTextFieldRed("Nome:", 130, 140);
	    JButton backBtn = p.addButton("Voltar",160, 20);
	    JTextField local = p.addTextFieldRed("Localiza��o:", 130, 170);
	    JComboBox<String> tipo = p.addComboBox("Tipo:", 130, 200, tipos);
	    JComboBox<String> sexo = p.addComboBox("Sexo:", 130, 230, sexos);
	    JSpinner idade = p.addNumericInput("Idade:", 130, 260);
	    JCheckBox vacinado = p.addCheckBox("Vacinado", 200, 260);
	    JCheckBox castrado = p.addCheckBox("Castrado", 200, 290);
	    JTextArea descricao = p.addTextArea("Descri��o", 100, 310);
	    JButton cadastrarBtn = p.addButton("Cadastrar", 160, 500);
	    
	    cadastrarBtn.addActionListener(new ActionListener() { 
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		
	    		// Salva dados do animal
	    	    String nome_animal = nome.getText();
	    	    String local_animal = local.getText();
	    	    String tipo_animal = (String) tipo.getSelectedItem();
	    	    String sexo_animal = (String) sexo.getSelectedItem();
	    	    int porte_animal=0;
	    	    int idade_animal = (int) idade.getValue();
	    	    boolean vacina_animal = vacinado.isSelected();
	    	    boolean castrado_animal = castrado.isSelected();
	    	    String descricao_animal = descricao.getText();
	    	    
	    	    if(!nome_animal.equals("")) {
	    	    	if(!local_animal.equals("")) {
			    		// Adiciona animal na lista
			    		if(tipo_animal == "Gato") {
			    			Gato gato = new Gato(0,nome_animal, idade_animal, porte_animal, vacina_animal, castrado_animal, local_animal, descricao_animal, sexo_animal);
			    			gato.SetConta(contaLogada);
			    			listaAnimais.add_animal_gato(gato);
			    			listaCadastrado.add_animal_gato(gato);
			    			contaLogada.SetCadastrados(listaCadastrado);
			    			
			    			OpenNewPainel(PerfilAnimalEditavel(gato,CadastrarAnimal()));
						}
			    		else if(tipo_animal == "Cachorro") {
			    			Cachorro cachorro = new Cachorro(0,nome_animal, idade_animal,porte_animal, vacina_animal, castrado_animal, local_animal, descricao_animal, sexo_animal);
			    			cachorro.SetConta(contaLogada);
			    			listaAnimais.add_animal_cao(cachorro);
			    			listaCadastrado.add_animal_cao(cachorro);
			    			contaLogada.SetCadastrados(listaCadastrado);
			    			
			    			OpenNewPainel(PerfilAnimalEditavel(cachorro,CadastrarAnimal()));
						}
	    	    	}else {
	    	    		local.requestFocus();
	    	    	}
	    	    }else {
	    	    	nome.requestFocus();
	    	    }
	    		

	    	}
	    } );
	    
	    backBtn.addActionListener(new ActionListener() { 
	    	public void actionPerformed(ActionEvent e) { 
	    		OpenNewPainel(CadastroMenu());
	    	} 
	    } );
	    
	    return p;
	}

	private Painel InfoContato(Animal a,Painel pAnterior) {
		Painel p=new Painel("img/bg_solido.png");
		Conta contato=a.GetConta();
	    p.addLabelWhite("Contato:", 40, 70,40);
	    
	    p.addLabelWhite(contato.getNome(), 40, 130);
	    p.addLabelWhite(contato.getEndereco(), 40,190);
		p.addLabelWhite(contato.getEmail(), 40, 220);

	    JButton backBtn = p.addButton("Voltar", 100, 500);
	    
	    backBtn.addActionListener(new ActionListener() { 
	    	public void actionPerformed(ActionEvent e) { 
	    		OpenNewPainel(pAnterior);
	    	}
	    } );
		
	    return p;
	}
	
	private Painel PerfilAnimal(Animal a,Painel pAnterior) {
		Painel p=new Painel("img/bg_solido.png");
		if(a instanceof Cachorro)
	    	p.addLabelWhite("C�o", 30, 10,15);
		else if(a instanceof Gato) 
		    	p.addLabelWhite("Gato", 30, 10,15);
		
	    p.addLabelWhite(a.getNome(), 40, 70,40);
	    
	    int idade=a.getIdade();
	    if(idade>0)
	    	p.addLabelWhite(a.getIdade()+" anos", 40, 130);
	    else
	    	p.addLabelWhite("Menos de um ano", 40, 130);
	    boolean macho=a.getSexo().equals("Macho");
	    p.addLabelWhite(a.getSexo(), 225,190);
	    if(macho) {
		    p.addLabelWhite((a.isCastrado()?"Castrado":"N�o castrado"), 40, 190);
		    p.addLabelWhite((a.isVacinado()?"Vacinado":"N�o vacinado"), 40, 220);
	    }else {

		    p.addLabelWhite((a.isCastrado()?"Castrada":"N�o castrada"), 40, 190);
		    p.addLabelWhite((a.isVacinado()?"Vacinada":"N�o vacinada"), 40, 220);
	    }

	    p.addLabelWhite("Localiza��o: "+a.getLocalizacao(), 40, 250);

	    p.addLabelWhite("Descri��o: ", 40, 280);
	    String desc="<html>";
	    String[] parts = a.getDescricao().split("\n");
	    for(int i=0;i<parts.length;i++)
	    	desc+=(parts[i]+"<br/>");
	    desc+="</html>";
	    
	    p.addLabelWhite(desc, 40, 320,20);
	    JButton adotarBtn = p.addButton("Adotar", 10, 500);
	    JButton backBtn = p.addButton("Voltar", 170, 500);
	    adotarBtn.addActionListener(new ActionListener() { 
	    	public void actionPerformed(ActionEvent e) { 
	    		OpenNewPainel(InfoContato(a,pAnterior));
	    	}
	    } );
	    backBtn.addActionListener(new ActionListener() { 
	    	public void actionPerformed(ActionEvent e) { 
	    		OpenNewPainel(pAnterior);
	    	}
	    } );
		
	    return p;
	}

	private Painel PerfilAnimalEditavel(Animal a,Painel pAnterior) {
		Painel p=new Painel("img/bg_solido.png");
		if(a instanceof Cachorro)
	    	p.addLabelWhite("C�o", 30, 10,15);
		else if(a instanceof Gato) 
		    	p.addLabelWhite("Gato", 30, 10,15);
		
	    p.addLabelWhite(a.getNome(), 40, 70,40);
	    
	    int idade=a.getIdade();
	    if(idade>0)
	    	p.addLabelWhite(a.getIdade()+" anos", 40, 130);
	    else
	    	p.addLabelWhite("Menos de um ano", 40, 130);
	    boolean macho=a.getSexo().equals("Macho");
	    p.addLabelWhite(a.getSexo(), 225,190);
	    if(macho) {
		    p.addLabelWhite((a.isCastrado()?"Castrado":"N�o castrado"), 40, 190);
		    p.addLabelWhite((a.isVacinado()?"Vacinado":"N�o vacinado"), 40, 220);
	    }else {

		    p.addLabelWhite((a.isCastrado()?"Castrada":"N�o castrada"), 40, 190);
		    p.addLabelWhite((a.isVacinado()?"Vacinada":"N�o vacinada"), 40, 220);
	    }

	    p.addLabelWhite("Localiza��o: "+a.getLocalizacao(), 40, 250);

	    p.addLabelWhite("Descri��o: ", 40, 280);
	    String desc="<html>";
	    String[] parts = a.getDescricao().split("\n");
	    for(int i=0;i<parts.length;i++)
	    	desc+=(parts[i]+"<br/>");
	    desc+="</html>";
	    
	    p.addLabelWhite(desc, 40, 320,20);
	    JButton backBtn = p.addButton("Voltar", 170, 500);
	    JButton removerBtn = p.addButton("Remover", 10, 500);
	    removerBtn.addActionListener(new ActionListener() { 
	    	public void actionPerformed(ActionEvent e) { 
	    		listaAnimais.rmvAnimal(a);
	    		listaCadastrado.rmvAnimal(a);
	    		contaLogada.SetCadastrados(listaCadastrado);
	    		OpenNewPainel(CadastroMenu());
	    	}
	    } );
	    backBtn.addActionListener(new ActionListener() { 
	    	public void actionPerformed(ActionEvent e) { 
	    		OpenNewPainel(pAnterior);
	    	}
	    } );
		
	    return p;
	}
	
	private Painel PainelPesquisar(int tipo) {
		Painel p = new Painel("img/bg_patas_rect.png");
		/* tipo
		 *  1 -> c�o
		 *  2 -> gato
		 *  0 -> ambos
		 */
		String str="";
		switch(tipo) {
			case 1:
				str="Buscar por c�o";
			break;
			case 2:
				str="Buscar por gato";
			break;
			case 0:
				str="Buscar";
			break;
		}
				
	    p.addLabelWhite(str,35,100);

	    JTextField nome = p.addTextFieldWhite("Nome:", 130, 160);
	    JTextField local = p.addTextFieldWhite("Localiza��o:", 130, 190);
	    JButton backBtn=p.addButton("Voltar",100,400);
	    JButton searchBtn=p.addButton("Pesquisar",100,300);
	    searchBtn.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		
	    	    OpenNewPainel(ExibePesquisa(tipo,nome.getText(),local.getText()));
	    	  } 
	    	} );
	    
	    backBtn.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    	    OpenNewPainel(AdotarMenu());
	    	  } 
	    	} );
	    return p;
	}
	
	
	private Painel ExibePesquisa(int tipo,String nome, String local) {

		Painel p=new Painel("img/bg_solido.png");
		JScrollPane scroll=p.addScrollPane(-30,60);
		Painel animais=new Painel("img/bg_patas.png");
	    String str="";
		switch(tipo) {
		case 1:
			str="Resultados da busca por c�o";
		break;
		case 2:
			str="Resultados da busca por gato";
		break;
		case 0:
			str="Resultados da busca";
		break;
	}
	    p.addLabelWhite(str,20,5);

	    JButton backBtn=p.addButton("Voltar",150,500);
	    
	    backBtn.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		  OpenNewPainel(AdotarMenu());
	    	  } 
	    	} );
	    
		animais.ChangeSize(new Dimension(300, 550));
		animais.setLayout(new WrapLayout());
		ListaAnimais busca_tipo;
		ListaAnimais busca_nome;
		ListaAnimais busca_local;
		//busca por tipo
		if(tipo!=0)
			busca_tipo=listaAnimais.pesquisaPorTipo(tipo);
		else
			busca_tipo=listaAnimais;
		ultimaBusca=busca_tipo;
		if(!nome.equals("")) {
			busca_nome=ultimaBusca.pesquisaPorNome(nome);
			ultimaBusca=busca_nome;
		}
		
		if(!local.equals("")) {
			busca_local=ultimaBusca.pesquisaPorLocalizacao(local);
			ultimaBusca=busca_local;
		}
		
		int i=0;
	    for (Animal animal : ultimaBusca.getAll()) {
		    JButton perfilBtn=animais.addButtonFlow(animal.getNome(),300,50);
		    perfilBtn.addActionListener(new ActionListener() { 
		    	public void actionPerformed(ActionEvent e) { 
		    		OpenNewPainel(PerfilAnimal(animal,ExibePesquisa(tipo)));
		    	} 
		    } );
		    i++;
		    if(i>9)
		    	break;
	    }
		scroll.setViewportView(animais);
		return p;
	}
	
	private Painel ExibePesquisa(int tipo) {

		Painel p=new Painel("img/bg_solido.png");
		JScrollPane scroll=p.addScrollPane(-30,60);
		Painel animais=new Painel("img/bg_patas.png");
	    String str="";
		switch(tipo) {
		case 1:
			str="Resultados da busca por c�o";
		break;
		case 2:
			str="Resultados da busca por gato";
		break;
		case 0:
			str="Resultados da busca";
		break;
	}
	    p.addLabelWhite(str,20,5);

	    JButton backBtn=p.addButton("Voltar",150,500);
	    
	    backBtn.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		  OpenNewPainel(AdotarMenu());
	    	  } 
	    	} );
	    
		animais.ChangeSize(new Dimension(300, 550));
		animais.setLayout(new WrapLayout());
		int i=0;
	    for (Animal animal : ultimaBusca.getAll()) {
		    JButton perfilBtn=animais.addButtonFlow(animal.getNome(),300,50);
		    perfilBtn.addActionListener(new ActionListener() { 
		    	public void actionPerformed(ActionEvent e) { 
		    		OpenNewPainel(PerfilAnimal(animal,ExibePesquisa(tipo)));
		    	} 
		    } );
		    i++;
		    if(i>9)
		    	break;
	    }
		scroll.setViewportView(animais);
		return p;
	}
		
	// Sess�o institucional
		private Painel PainelInstitucional() {
			Painel p = new Painel("img/bg_solido3.png");

		    tela.setLayout(new GridBagLayout());
		    p.addLabelWhite("Conta Institucional",85,100);

		    JButton loginBtn=p.addButton("Login",100,170);
		    JButton cadastroBtn=p.addButton("Cadastro",100,240);
		    JButton backBtn=p.addButton("Voltar",100,400);
		    
		    loginBtn.addActionListener(new ActionListener() { 
		    	  public void actionPerformed(ActionEvent e) { 
		    	    OpenNewPainel(LoginInstitucional());
		    	  } 
		    	} );
		    cadastroBtn.addActionListener(new ActionListener() { 
		    	  public void actionPerformed(ActionEvent e) { 
		    	    OpenNewPainel(CadastroInstitucional());
		    	  } 
		    	} );
		    backBtn.addActionListener(new ActionListener() { 
		    	  public void actionPerformed(ActionEvent e) { 
		    		  OpenNewPainel(PainelEntrada());
		    	  } 
		    	} );
		    
		    return p;
		}
		
		private Painel InstitucionalMenu() {
			Painel p=new Painel("img/bg_solido3.png");

		    p.addLabelWhite(contaLogada.getEmail(),80,100);

		    JButton cadastrarBtn = p.addButton("Cadastrar novo",75,170,200);
		    JButton atualizarBtn = p.addButton("Animais cadastrados",75,240,200);
		    JButton backBtn = p.addButton("Voltar",100,400);
		    
		    cadastrarBtn.addActionListener(new ActionListener() { 
		    	  public void actionPerformed(ActionEvent e) { 
		    	    OpenNewPainel(CadastrarAnimalInst());
		    	  } 
		    	} );
		    atualizarBtn.addActionListener(new ActionListener() { 
		    	  public void actionPerformed(ActionEvent e) { 
		    	    OpenNewPainel(CadastroMenuInst());
		    	  } 
		    	} );
		    backBtn.addActionListener(new ActionListener() { 
		    	  public void actionPerformed(ActionEvent e) { 
		    	    OpenNewPainel(PainelInstitucional());
		    	  } 
		    	} );
		    return p;
		}

		private Painel CadastrarAnimalInst() {
			Painel p = new Painel("img/bg_solido3.png");
		    p.addLabelWhite("Cadastro", 35, 20);

		    String tipos[] = { "Gato", "Cachorro"};
		    String sexos[] = { "Macho", "F�mea"};
		    
		    JTextField nome = p.addTextFieldWhite("Nome:", 130, 140);
		    JButton backBtn = p.addButton("Voltar",160, 20);
		    JTextField local = p.addTextFieldWhite("Localiza��o:", 130, 170);
		    JComboBox<String> tipo = p.addComboBoxWhite("Tipo:", 130, 200, tipos);
		    JComboBox<String> sexo = p.addComboBoxWhite("Sexo:", 130, 230, sexos);
		    JSpinner idade = p.addNumericInputWhite("Idade:", 130, 260);
		    JCheckBox vacinado = p.addCheckBoxWhite("Vacinado", 200, 260);
		    JCheckBox castrado = p.addCheckBoxWhite("Castrado", 200, 290);
		    JTextArea descricao = p.addTextAreaWhite("Descri��o", 100, 310);
		    JButton cadastrarBtn = p.addButton("Cadastrar", 160, 500);
		    
		    cadastrarBtn.addActionListener(new ActionListener() { 
		    	public void actionPerformed(ActionEvent e) {
		    		
		    		
		    		// Salva dados do animal
		    	    String nome_animal = nome.getText();
		    	    String local_animal = local.getText();
		    	    String tipo_animal = (String) tipo.getSelectedItem();
		    	    String sexo_animal = (String) sexo.getSelectedItem();
		    	    int porte_animal=0;
		    	    int idade_animal = (int) idade.getValue();
		    	    boolean vacina_animal = vacinado.isSelected();
		    	    boolean castrado_animal = castrado.isSelected();
		    	    String descricao_animal = descricao.getText();
		    	    
		    	    if(!nome_animal.equals("")) {
		    	    	if(!local_animal.equals("")) {
				    		// Adiciona animal na lista
				    		if(tipo_animal == "Gato") {
				    			Gato gato = new Gato(0,nome_animal, idade_animal, porte_animal, vacina_animal, castrado_animal, local_animal, descricao_animal, sexo_animal);
				    			gato.SetConta(contaLogada);
				    			listaAnimais.add_animal_gato(gato);
				    			listaCadastrado.add_animal_gato(gato);
				    			contaLogada.SetCadastrados(listaCadastrado);
				    			OpenNewPainel(PerfilAnimalEditavelInst(gato,CadastrarAnimalInst()));
							}
				    		else if(tipo_animal == "Cachorro") {
				    			Cachorro cachorro = new Cachorro(0,nome_animal, idade_animal,porte_animal, vacina_animal, castrado_animal, local_animal, descricao_animal, sexo_animal);
				    			cachorro.SetConta(contaLogada);
				    			listaAnimais.add_animal_cao(cachorro);
				    			listaCadastrado.add_animal_cao(cachorro);
				    			contaLogada.SetCadastrados(listaCadastrado);
				    			
				    			OpenNewPainel(PerfilAnimalEditavelInst(cachorro,CadastrarAnimalInst()));
							}
		    	    	}else {
		    	    		local.requestFocus();
		    	    	}
		    	    }else {
		    	    	nome.requestFocus();
		    	    }
		    		

		    	}
		    } );
		    
		    backBtn.addActionListener(new ActionListener() { 
		    	public void actionPerformed(ActionEvent e) { 
		    		OpenNewPainel(InstitucionalMenu());
		    	} 
		    } );
		    
		    return p;
		}

		private Painel PerfilAnimalEditavelInst(Animal a,Painel pAnterior) {
			Painel p=new Painel("img/bg_solido3.png");
			if(a instanceof Cachorro)
		    	p.addLabelWhite("C�o", 30, 10,15);
			else if(a instanceof Gato) 
			    	p.addLabelWhite("Gato", 30, 10,15);
			
		    p.addLabelWhite(a.getNome(), 40, 70,40);
		    
		    int idade=a.getIdade();
		    if(idade>0)
		    	p.addLabelWhite(a.getIdade()+" anos", 40, 130);
		    else
		    	p.addLabelWhite("Menos de um ano", 40, 130);
		    boolean macho=a.getSexo().equals("Macho");
		    p.addLabelWhite(a.getSexo(), 225,190);
		    if(macho) {
			    p.addLabelWhite((a.isCastrado()?"Castrado":"N�o castrado"), 40, 190);
			    p.addLabelWhite((a.isVacinado()?"Vacinado":"N�o vacinado"), 40, 220);
		    }else {

			    p.addLabelWhite((a.isCastrado()?"Castrada":"N�o castrada"), 40, 190);
			    p.addLabelWhite((a.isVacinado()?"Vacinada":"N�o vacinada"), 40, 220);
		    }

		    p.addLabelWhite("Localiza��o: "+a.getLocalizacao(), 40, 250);

		    p.addLabelWhite("Descri��o: ", 40, 280);
		    String desc="<html>";
		    String[] parts = a.getDescricao().split("\n");
		    for(int i=0;i<parts.length;i++)
		    	desc+=(parts[i]+"<br/>");
		    desc+="</html>";
		    
		    p.addLabelWhite(desc, 40, 320,20);
		    JButton backBtn = p.addButton("Voltar", 170, 500);
		    JButton removerBtn = p.addButton("Remover", 10, 500);
		    removerBtn.addActionListener(new ActionListener() { 
		    	public void actionPerformed(ActionEvent e) { 
		    		listaAnimais.rmvAnimal(a);
		    		listaCadastrado.rmvAnimal(a);
		    		contaLogada.SetCadastrados(listaCadastrado);
		    		OpenNewPainel(CadastroMenuInst());
		    	}
		    } );
		    backBtn.addActionListener(new ActionListener() { 
		    	public void actionPerformed(ActionEvent e) { 
		    		OpenNewPainel(pAnterior);
		    	}
		    } );
			
		    return p;
		}
		
		private Painel LoginInstitucional() {
			Painel p = new Painel("img/bg_solido3.png");

		    p.addLabelWhite("Login - Institucional",70,100);
		    JTextField email = p.addTextField("Login:", 100, 170);
		    JPasswordField senha = p.addPasswordField("Senha:", 100, 220);
		    
		    JButton enterAppBtn=p.addButton("Entrar",100,280);
		    JButton backBtn=p.addButton("Voltar",100,400);
		    JLabel errorLabel=p.addLabelWhite("",130,235,12);
		    enterAppBtn.addActionListener(new ActionListener() { 
		    	public void actionPerformed(ActionEvent e) { 
		    		String emailTxt=email.getText();
	    		  String senhaTxt = new String(senha.getPassword());
	    		  if(!emailTxt.equals("")) {
		    		  if(listaContas.contaInstitucionalExistente(emailTxt)) {
		    			  if((contaLogada=listaContas.checkSenha(emailTxt, senhaTxt))!=null) {
		    				  listaCadastrado=contaLogada.GetCadastrados();
		    		    	    OpenNewPainel(InstitucionalMenu());
		    			  }else {
		    				  errorLabel.setText("Senha incorreta.");
		    				  senha.requestFocus();
		    			  }
		    		  }else {
		    			  errorLabel.setText("Conta inexistente.");
		    			  email.requestFocus();
		    		  }
		    			  
	    		  }else {
	    			  email.requestFocus();
	    		  }
		    	} 
		    } );
		    backBtn.addActionListener(new ActionListener() { 
		    	public void actionPerformed(ActionEvent e) { 
		    		OpenNewPainel(PainelInstitucional());
		    	} 
		    } );
		    return p;
		}
		
		private Painel CadastroInstitucional() {
			Painel p=new Painel("img/bg_solido3.png");

		    p.addLabelWhite("Cadastro - Institucional",70,100);
		    JTextField email=p.addTextField("Login:",100, 170);
		    JTextField telefone=p.addTextField("Telefone:",100, 210);
		    JTextField endereco=p.addTextField("Endere�o:",100, 250);
		    
		    JPasswordField senha= p.addPasswordField("Senha:",100, 290);

		    JPasswordField confSenha= p.addPasswordField("Conf. Senha:",100, 320);
		    JButton enterAppBtn=p.addButton("Cadastrar",100,400);
		    JButton backBtn=p.addButton("Voltar",100,500);
		    JLabel errorLabel=p.addLabelWhite("",130,280,12);
		    enterAppBtn.addActionListener(new ActionListener() { 
		    	  public void actionPerformed(ActionEvent e) { 
		    		String emailTxt=email.getText();
		    		String telefoneTxt=telefone.getText();
		    		String enderecoTxt=endereco.getText();
		    		String senhaTxt = new String(senha.getPassword());
		    		String senha2Txt = new String(confSenha.getPassword());
		    		if(emailTxt.equals("") || telefoneTxt.equals("")|| enderecoTxt.equals("")) {
		    			  errorLabel.setText("Login inv�lido.");
		    			  email.requestFocus();
		    		}else if(!listaContas.contaExistente(emailTxt)) {
		    			  if(senhaTxt.length()>3 && senhaTxt.equals(senha2Txt)) {
		    				  listaContas.addEmpresa(telefoneTxt, enderecoTxt, emailTxt, senhaTxt);
		    		    	    OpenNewPainel(LoginInstitucional());
		    			  }else {
		    				  errorLabel.setText("Senha incorreta.");
		    				  senha.requestFocus();
		    			  }
		    		  }else {
		    			  errorLabel.setText("Conta j� existe.");
		    			  email.requestFocus();
		    		  }
		    	  } 
		    	} );
		    backBtn.addActionListener(new ActionListener() { 
		    	  public void actionPerformed(ActionEvent e) { 
		    	    OpenNewPainel(PainelInstitucional());
		    	  } 
		    	} );
		    return p;
		}

		private Painel CadastroMenuInst() {
			Painel p=new Painel("img/bg_solido3.png");

			JScrollPane scroll=p.addScrollPane(-30,60);
			Painel animais=new Painel("img/bg_solido2.png");
		    p.addLabelWhite("Meus cadastros",20,5);

		    JButton backBtn=p.addButton("Voltar",170,500);
		    //JButton cadastrarBtn=p.addButton("Adicionar novo",10,500);

		    /*cadastrarBtn.addActionListener(new ActionListener() { 
		    	  public void actionPerformed(ActionEvent e) { 
		    	    OpenNewPainel(CadastrarAnimalInst());
		    	  } 
		    	} );*/
		    backBtn.addActionListener(new ActionListener() { 
		    	  public void actionPerformed(ActionEvent e) { 
		    		  OpenNewPainel(InstitucionalMenu());
		    	  } 
		    	} );
		    
			animais.ChangeSize(new Dimension(300, 550));
			animais.setLayout(new WrapLayout());
			
			int i=0;
		    for (Animal animal : listaCadastrado.getAll()) {
			    JButton perfilBtn=animais.addButtonFlow(animal.getNome(),300,50);
			    perfilBtn.addActionListener(new ActionListener() { 
			    	public void actionPerformed(ActionEvent e) { 
			    		OpenNewPainel(PerfilAnimalEditavelInst(animal,CadastroMenuInst()));
			    	} 
			    } );
			    i++;
			    if(i>9)
			    	break;
		    }
			scroll.setViewportView(animais);
			return p;
		}
		
	public void ExibeTelaEntrada() {
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Painel p=PainelEntrada();

		tela.add(p);
		tela.pack();
		tela.setSize(350,622);
	    CenterJFrame(tela);
	    tela.setResizable(false);
	    tela.setVisible(true);
	}
	
	private void CenterJFrame(JFrame jf) {
		  Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		  jf.setLocation(dim.width/2-jf.getSize().width/2, dim.height/2-jf.getSize().height/2);
		    
	}
	
	private void OpenNewPainel(Painel p) {
		tela.getContentPane().removeAll();
		
		tela.getContentPane().add(p);

		tela.revalidate();
		tela.repaint();

	}
	
}
