package controller;

import dao.AlunoDAOImpl;
import dto.AlunoDTO;
import ui.Leitor;
import ui.AlunoViewConsole;

public class Controller {

    AlunoDAOImpl alunoDAO = new AlunoDAOImpl();

    public void start() {
        boolean continuar = true;
        while (continuar) {
            int opcao = Leitor.lerInt("[1] - Cadastrar aluno\n" +
                    "[2] - Listar todos os alunos\n" +
                    "[3] - Atualizar um aluno pelo ID\n" +
                    "[4] - Excluir um aluno pelo ID\n" +
                    "[5] - Buscar aluno por nome\n" +
                    "[6] - Sair", 1, 5);

            switch (opcao) {
                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    exibirTodosAlunos();
                    break;
                case 3:
                    atualizarAluno();
                    break;
                case 4:
                    excluirAluno();
                    break;
                case 5:
                    pesquisarAluno();
                    break;
                default:
                    System.out.println("Encerrando a conexão.");
                    continuar = false;
                    break;
            }
        }
    }

    public void cadastrarAluno() {
        AlunoDTO aluno = AlunoViewConsole.lerAluno();
        alunoDAO.inserirAluno(aluno);
    }

    public void exibirTodosAlunos() {
        AlunoViewConsole.exibirResultado(alunoDAO.listarTodos());
    }

    public void atualizarAluno() {
        int idPesquisado = Leitor.lerInt("Informe o ID que deseja pesquisar:");
        if (alunoDAO.buscarPorId(idPesquisado) == null) {
            System.out.println("Não existe um aluno com esse ID.");
        } else {
            AlunoDTO novoAluno = AlunoViewConsole.lerAluno();
            alunoDAO.atualizaPorId(idPesquisado, novoAluno);
            alunoDAO.listarTodos();
        }
    }

    public void excluirAluno() {
        int idPesquisado = Leitor.lerInt("Informe o ID que deseja pesquisar:");
        if (alunoDAO.buscarPorId(idPesquisado) == null) {
            System.out.println("Não existe um aluno com esse ID.");
        } else {
            alunoDAO.excluirPorId(idPesquisado);
            alunoDAO.listarTodos();
        }
    }

    public void pesquisarAluno() {
        String termoPesquisa = Leitor.lerString("Informe o termo a ser pesquisado: ");
        AlunoViewConsole.exibirResultado(alunoDAO.buscaPorNome(termoPesquisa));
    }
}
