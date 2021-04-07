package com.example.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.ui.ListaAlunosView;
import com.example.agenda.ui.adapter.ListaAlunosAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaAlunosActivity extends AppCompatActivity {
    private final String TAG = "LOG_MAIN";
    private final AlunoDAO dao = new AlunoDAO();
    private ListaAlunosAdapter adapter;
    private final ListaAlunosView listaALunosView = new ListaAlunosView(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(getString(R.string.string_titulo_main));
        configuraFabPlus();
        configuraLista();
    }

    private void configuraFabPlus() {
        FloatingActionButton btnPlus = findViewById(R.id.activity_lista_alunos_fab_plus);
        btnPlus.setOnClickListener(v -> intentParaFormularioInsercao());
    }

    private void intentParaFormularioInsercao() {
        startActivity(new Intent(this,
                FormularioAlunoActivity.class));
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_lista_de_alunos);
        listaALunosView.configuraAdapter(listaDeAlunos);
        configuraListenerDeClickPorItem(listaDeAlunos);
        //configuraListenerDeCliqueLongoPorItem(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
    }


    private void configuraListenerDeClickPorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener((parent, view, position, id) -> {
            Aluno alunoClicadoEdicao = (Aluno) parent.getItemAtPosition(position);
            Log.d(TAG, "onItemClick: " + alunoClicadoEdicao.getNome());
            intentParaFormularioEdicao(alunoClicadoEdicao);
        });
    }

    private void intentParaFormularioEdicao(Aluno alunoClicado) {
        Intent goToFormularioEditaAluno = new Intent(ListaAlunosActivity.this,
                FormularioAlunoActivity.class);
        goToFormularioEditaAluno.putExtra(getString(R.string.string_intent_alunoParaFormulario),
                alunoClicado);
        startActivity(goToFormularioEditaAluno);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //menu.add(getString(R.string.string_context_menu_remover));
        getMenuInflater().inflate(R.menu.activity_lista_alunos_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull final MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_alunos_menu_item_remover) {
            listaALunosView.mostraDialogExclusaoAluno(item);
        }

        return super.onContextItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        listaALunosView.atualizaListaAlunos();

    }


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