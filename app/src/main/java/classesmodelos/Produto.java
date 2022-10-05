package classesmodelos;

public class Produto {
    private String nome;
    private  Float preco;
    private Mercado mercado;
    private Usuario usuario;
    private String dataenvio;



    public Produto(String nome, Float preco, Mercado mercado, Usuario usuario, String dataenvio) {
        this.nome = nome;
        this.preco = preco;
        this.mercado = mercado;
        this.usuario = usuario;
        this.dataenvio = dataenvio;
    }

    public Produto(String tomate, float preco, String godinhos, Object usuario, Object dataenvio) {
    }

    public String getNome() {
        return nome;
    }

    public Float getPreco() {
        return preco;
    }

    public Mercado getMercado() {
        return mercado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getDataenvio() {
        return dataenvio;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public void setMercado(Mercado mercado) {
        this.mercado = mercado;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setDataenvio(String dataenvio) {
        this.dataenvio = dataenvio;
    }





}
