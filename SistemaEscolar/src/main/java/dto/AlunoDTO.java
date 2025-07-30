package dto;

public class AlunoDTO {
    private int id;
    private String nome;
    private String cpf;
    private int idade;

    public AlunoDTO(String nome, String cpf, int idade) {
        setNome(nome);
        setCpf(cpf);
        setIdade(idade);
    }

    public AlunoDTO(int id, String nome, String cpf, int idade) {
        setId(id);
        setNome(nome);
        setCpf(cpf);
        setIdade(idade);
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
