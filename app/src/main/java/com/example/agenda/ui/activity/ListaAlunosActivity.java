package com.example.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaAlunosActivity extends AppCompatActivity {
    private String TAG = "LOG_MAIN";
    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(getString(R.string.string_titulo_main));
        configuraFabPlus();


    }

    private void configuraFabPlus() {
        FloatingActionButton btnPlus = findViewById(R.id.activity_lista_alunos_fab_plus);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreFormulario();
            }
        });
    }

    private void abreFormulario() {
        startActivity(new Intent(this,
                FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();

    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_lista_de_alunos);
        listaDeAlunos.setAdapter(new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, dao.todos()));
    }
}
