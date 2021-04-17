package com.example.diario;

import android.app.Application;

import com.example.diario.dao.DayDAO;
import com.example.diario.model.Day;

public class DiarioApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DayDAO dao = new DayDAO();
        criaAlunosDeTeste(dao);
    }

    private void criaAlunosDeTeste(DayDAO dao) {
        for (int i = 0; i < 20; i++) {
            dao.save(new Day("17/04/2021", "Sat", "happy"));
            dao.save(new Day("18/04/2021", "Sun", "in peace"));
        }
    }
}
