package dao;

import model.Aluno;
import util.QuerySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseCRUD {

    public Connection connection;

    public DatabaseCRUD(Connection connection) {
        this.connection = connection;
    }

    public void inserirAluno(Aluno aluno) {
        try (PreparedStatement ps = connection.prepareStatement(QuerySql.INSERIR_ALUNO.getQuery())){
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getCpf());
            ps.setInt(3, aluno.getIdade());

            int linhasAfetadas = ps.executeUpdate();
            System.out.println("Sucesso! Linhas afetadas: " + linhasAfetadas);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Aluno> listarTodos() {
        try (PreparedStatement ps = connection.prepareStatement(QuerySql.LISTAR_TODOS.getQuery())){
            ResultSet result = ps.executeQuery();
            ArrayList<Aluno> alunos = new ArrayList<>();
            while (result.next()) {
                Aluno aluno = new Aluno(
                        result.getInt("id"),
                        result.getString("nome"),
                        result.getString("cpf"),
                        result.getInt("idade")
                );
                alunos.add(aluno);
            }
            return alunos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Aluno buscarPorId(int idPesquisado) {
        try (PreparedStatement ps = connection.prepareStatement(QuerySql.BUSCAR_POR_ID.getQuery())){
            ps.setInt(1, idPesquisado);
            ResultSet result = ps.executeQuery();
            ArrayList<Aluno> alunos = new ArrayList<>();
            if (!result.next()) {
                System.out.println("Não existe um aluno com esse ID.");
            } else {
                Aluno aluno = new Aluno(
                            result.getInt("id"),
                            result.getString("nome"),
                            result.getString("cpf"),
                            result.getInt("idade")
                );
                return aluno;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public int atualizaPorId(int idPesquisado, Aluno aluno) {
        try (PreparedStatement ps = connection.prepareStatement(QuerySql.ATUALIZAR_POR_ID.getQuery())) {
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getCpf());
            ps.setInt(3, aluno.getIdade());
            ps.setInt(4, idPesquisado);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int excluirPorId(int idPesquisado) {
        try (PreparedStatement ps = connection.prepareStatement(QuerySql.EXCLUIR_POR_ID.getQuery())) {
            ps.setInt(1, idPesquisado);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Aluno> buscaPorNome(String termoPesquisa) {
        try (PreparedStatement ps = connection.prepareStatement(QuerySql.BUSCAR_POR_NOME.getQuery())) {
            ps.setString(1, "%" + termoPesquisa + "%");
            ResultSet result = ps.executeQuery();
            ArrayList<Aluno> alunos = new ArrayList<>();
            while (result.next()) {
                Aluno aluno = new Aluno(
                        result.getInt("id"),
                        result.getString("nome"),
                        result.getString("cpf"),
                        result.getInt("idade")
                );
                alunos.add(aluno);
            }
            return alunos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
