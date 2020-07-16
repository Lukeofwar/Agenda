package com.example.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaAlunosActivity extends AppCompatActivity {
    private String TAG = "LOG_MAIN";
    private final AlunoDAO dao = new AlunoDAO();
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(getString(R.string.string_titulo_main));
        for (int i = 0; i < 10; i++) {
        dao.salva(new Aluno("Joao", "91111-1111", "joao@gmail.com"));
        dao.salva(new Aluno("Maria", "91111-1112", "maria@gmail.com"));
        dao.salva(new Aluno("Arnaldo", "91111-1113", "naldo@gmail.com"));
        }

        configuraFabPlus();
        configuraLista();
    }

    private void configuraFabPlus() {
        FloatingActionButton btnPlus = findViewById(R.id.activity_lista_alunos_fab_plus);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentParaFormularioInsercao();
            }
        });
    }

    private void intentParaFormularioInsercao() {
        startActivity(new Intent(this,
                FormularioAlunoActivity.class));
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_lista_de_alunos);
        configuraAdapter(listaDeAlunos);
        configuraListenerDeClickPorItem(listaDeAlunos);
        //configuraListenerDeCliqueLongoPorItem(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
    }

//    private void configuraListenerDeCliqueLongoPorItem(ListView listaDeAlunos) {
//        listaDeAlunos.setOnItemLongClickListener(new ListView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Aluno alunoClicadoRemocao = (Aluno) parent.getItemAtPosition(position);
//                Log.d(TAG, "onItemLongClick: " + position);
//                removeAlunoDaListaEDao(alunoClicadoRemocao);
////false->passa pro evento seguinte
//// true->para nesse evento
//                return false;
//            }
//        });
//    }

    private void removeAlunoDaListaEDao(Aluno alunoClicadoRemocao) {
        dao.remove(alunoClicadoRemocao);
        adapter.remove(alunoClicadoRemocao);
    }

    private void configuraListenerDeClickPorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoClicadoEdicao = (Aluno) parent.getItemAtPosition(position);
                Log.d(TAG, "onItemClick: " + alunoClicadoEdicao.getNome());
                intentParaFormularioEdicao(alunoClicadoEdicao);
            }
        });
    }

    private void intentParaFormularioEdicao(Aluno alunoClicado) {
        Intent goToFormularioEditaAluno = new Intent(ListaAlunosActivity.this,
                FormularioAlunoActivity.class);
        goToFormularioEditaAluno.putExtra(getString(R.string.string_intent_alunoParaFormulario),
                alunoClicado);
        startActivity(goToFormularioEditaAluno);
    }

    private void configuraAdapter(ListView listaDeAlunos) {
        adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1);
        listaDeAlunos.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //menu.add(getString(R.string.string_context_menu_remover));
        getMenuInflater().inflate(R.menu.activity_lista_alunos_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_alunos_menu_item_remover) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno alunoParaRemocao = adapter.getItem(menuInfo.position);
            removeAlunoDaListaEDao(alunoParaRemocao);
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaListaAlunos();

    }

    private void atualizaListaAlunos() {
        adapter.clear();
        adapter.addAll(dao.todos());
    }

}
