import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class View {
    public static Aluno lerAluno() {
        String nome = Leitor.lerString("Nome: ");
        String cpf = Leitor.lerString("CPF: ");
        int idade = Leitor.lerInt("Idade: ");
        return new Aluno(nome, cpf, idade);
    }

    public static void exibirHeader(ResultSet result) throws SQLException {
        ResultSetMetaData resultMetaData = result.getMetaData();
        for (int i = 1; i <= resultMetaData.getColumnCount() ; i++) {
            System.out.printf("%-15s", resultMetaData.getColumnName(i).toUpperCase());
        }
    }

    public static void exibirConteudo(ResultSet result) throws SQLException {
        ResultSetMetaData resultMetaData = result.getMetaData();
        while (result.next()) {
            for (int i = 1; i <= resultMetaData.getColumnCount(); i++) {
                System.out.printf("%-15s", result.getString(i));
            }
            System.out.println();
        }
    }


    public static void exibirResultado(ArrayList<Aluno> alunos) {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno encontrado.");
        } else {
            for (Aluno aluno : alunos) {
                System.out.printf("ID: %d | Nome: %s | CPF: %s | Idade: %d\n",
                        aluno.getId(), aluno.getNome(), aluno.getCpf(), aluno.getIdade());
            }
        }
    }

    public static void exibirResultado(ResultSet result) throws SQLException {
        exibirHeader(result);
        System.out.println();
        exibirConteudo(result);
    }
}
