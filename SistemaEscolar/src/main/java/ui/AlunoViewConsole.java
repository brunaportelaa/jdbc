package ui;

import dto.AlunoDTO;

import java.util.ArrayList;

public class AlunoViewConsole {
    public static AlunoDTO lerAluno() {
        String nome = Leitor.lerString("Nome: ");
        String cpf = Leitor.lerString("CPF: ");
        int idade = Leitor.lerInt("Idade: ");
        return new AlunoDTO(nome, cpf, idade);
    }


    public static void exibirResultado(ArrayList<AlunoDTO> alunos) {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno encontrado.");
        } else {
            for (AlunoDTO aluno : alunos) {
                System.out.printf("ID: %d | Nome: %s | CPF: %s | Idade: %d\n",
                        aluno.getId(), aluno.getNome(), aluno.getCpf(), aluno.getIdade());
            }
        }
    }
}
