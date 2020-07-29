package com.example.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {
    private String TAG = "LOG_FORMULARIO";
    private EditText edtNome;
    private EditText edtTelefone;
    private EditText edtEmail;
    private final AlunoDAO dao = new AlunoDAO();
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        inicializacaoDosCampos();
        carregaAluno();
    }

    private void carregaAluno() {
        Intent dadosIntent = getIntent();
        if (dadosIntent.hasExtra(getString(R.string.string_intent_alunoParaFormulario))) {
            setTitle(getString(R.string.string_titulo_formulario_edicao));

            aluno = (Aluno) dadosIntent
                    .getSerializableExtra(getString(R.string.string_intent_alunoParaFormulario));
            preencheCamposAlunoEdicao();
        } else {
            setTitle(getString(R.string.string_titulo_formulario_insercao));
            aluno = new Aluno();
        }
    }

    private void preencheCamposAlunoEdicao() {
        edtNome.setText(aluno.getNome());
        edtTelefone.setText(aluno.getTelefone());
        edtEmail.setText(aluno.getEmail());
    }


    private void finalizaFormulario() {
        preencheAluno();
        if (aluno.temIdValido()) {
            dao.edita(aluno);
        } else {
            dao.salva(aluno);
        }
        finish();
    }

    private void inicializacaoDosCampos() {
        edtNome = findViewById(R.id.activity_formulario_aluno_nome);
        edtTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        edtEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void preencheAluno() {
        String nome = edtNome.getText().toString();
        String telefone = edtTelefone.getText().toString();
        String email = edtEmail.getText().toString();
        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_formulario_aluno_menu_ok) {
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }
}
