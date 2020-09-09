package com.example.agenda.dao;

import com.example.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private String TAG = "LOG_ALUNO_DAO";
    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeId = 1;

    public void salva(Aluno aluno) {
        aluno.setId(contadorDeId);
        alunos.add(aluno);
        aumentaId();
    }

    private void aumentaId() {
        contadorDeId++;
    }

    public void edita(Aluno aluno) {
        Aluno alunoEncontrado;
        alunoEncontrado = buscaAlunoPeloId(aluno);
        if (alunoEncontrado != null) {
            int posicaoALunoEncontrado = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoALunoEncontrado, aluno);
        }
    }

    private Aluno buscaAlunoPeloId(Aluno aluno) {
        for (Aluno a : alunos) {
            if (a.getId() == aluno.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Aluno> todos() {
        return alunos;
    }

    public void remove(Aluno alunoClicadoRemocao) {
        Aluno alunoParaRemocao = buscaAlunoPeloId(alunoClicadoRemocao);
        if (alunoClicadoRemocao != null) {
            alunos.remove(alunoParaRemocao);
        }
    }
}
