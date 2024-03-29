
public class Conta {
	private int key;
	private String nome;
	private String endereco;
	private String email;
	private String senha;
	private ListaAnimais lista;
	
	public Conta (int key, String nom, String end, String email, String senha) {
		lista=new ListaAnimais();
		setKey(key);
		setEmail(email);
		setSenha(senha);
		setNome(nom);
		setEndereco(end);
	}
	public void SetCadastrados(ListaAnimais l) {
		lista=l;
	}

	public ListaAnimais GetCadastrados() {
		return lista;
	}
	public String toString() {
		String str = new String();
		
		str.concat(String.valueOf(this.key) + '|' + this.nome + '|' + this.endereco + '|' + this.email + '|' 
		+ this.senha + '\n');
		
		return str;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

}
