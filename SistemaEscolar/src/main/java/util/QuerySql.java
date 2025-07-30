package util;

public enum QuerySql {

    INSERIR_ALUNO("INSERT INTO aluno (nome,cpf,idade) values (?, ?, ?)"),
    LISTAR_TODOS("SELECT * FROM aluno"),
    BUSCAR_POR_ID("SELECT * FROM aluno WHERE id = ?"),
    ATUALIZAR_POR_ID("UPDATE aluno SET nome = ?, cpf = ?, idade = ? WHERE id = ?"),
    EXCLUIR_POR_ID("DELETE FROM aluno WHERE id = ?"),
    BUSCAR_POR_NOME("SELECT * FROM aluno WHERE nome LIKE ?");

    // Campo final
    final String query;

    // Construtor
    QuerySql(String query) {
        this.query = query;
    }

    // Metodo get para o campo query
    public String getQuery() {
        return query;
    }
}
