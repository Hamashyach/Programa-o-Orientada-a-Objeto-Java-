package gestaopizzaria;

public abstract class Pizza {

    protected int id;
    protected String nome;
    protected double preco;
    protected String tamanho;
    protected String descricao;

    public Pizza(int id, String nome, double preco, String tamanho, String desc) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.tamanho = tamanho;
        this.descricao = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void imprimir() {
        System.out.println("ID:  " + id);
        System.out.println("Nome:  " + nome);
        System.out.println("Preco:  " + preco);
        System.out.println("Tamanho:  " + tamanho);
        System.out.println("Descricao:  " + descricao);

    }
}
