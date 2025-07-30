package dao;

import dto.AlunoDTO;
import util.QuerySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface AlunoDAO {

    public void inserirAluno(AlunoDTO aluno);

    public ArrayList<AlunoDTO> listarTodos();

    public AlunoDTO buscarPorId(int idPesquisado);

    public int atualizaPorId(int idPesquisado, AlunoDTO aluno);

    public int excluirPorId(int idPesquisado);

    public ArrayList<AlunoDTO> buscaPorNome(String termoPesquisa);

}
