package com.example.agenda;

import android.app.Application;

import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AlunoDAO dao = new AlunoDAO();
        criaAlunosDeTeste(dao);
    }

    private void criaAlunosDeTeste(AlunoDAO dao) {
        for (int i = 0; i < 2; i++) {
            dao.salva(new Aluno("Joao", "91111-1111", "joao@gmail.com"));
            dao.salva(new Aluno("Maria", "91111-1112", "maria@gmail.com"));
        }
    }
}
