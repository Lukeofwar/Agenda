package com.example.agenda.ui.activity;

import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.ui.adapter.ListaAlunosAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaAlunosActivity extends AppCompatActivity {
    private String TAG = "LOG_MAIN";
    private final AlunoDAO dao = new AlunoDAO();
    private ListaAlunosAdapter adapter;

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
        adapter = new ListaAlunosAdapter(this);
        listaDeAlunos.setAdapter(adapter);

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
            mostraDialogExclusaoAluno(item);
        }

        return super.onContextItemSelected(item);
    }

    private void mostraDialogExclusaoAluno(@NonNull final MenuItem item) {
        new AlertDialog.Builder(this)
                .setTitle("Removendo Aluno")
                .setMessage("Tem certeza que deseja remover?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Aluno alunoParaRemocao = (Aluno) adapter.getItem(menuInfo.position);
                        removeAlunoDaListaEDao(alunoParaRemocao);

                    }
                })
                .setNegativeButton("Nao", null)
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaListaAlunos();

    }

    private void atualizaListaAlunos() {
        adapter.atualiza(dao.todos());
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