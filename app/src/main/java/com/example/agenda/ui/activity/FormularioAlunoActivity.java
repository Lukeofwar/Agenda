package com.example.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(getString(R.string.string_titulo_formulario));
        inicializacaoDosCampos();
        configuraBotaoSalvar();
    }

    private void configuraBotaoSalvar() {
        Button btnSalvar = findViewById(R.id.activity_formulario_aluno_btn_finalizar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!edtNome.getText().toString().equals("") && !edtEmail.getText().toString().equals("")) {
                    Aluno alunoCriado = criaAluno();
                    salva(alunoCriado);
                } else {
                    Toast.makeText(FormularioAlunoActivity.this,
                            getString(R.string.string_msg_nome_email_vazio), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void inicializacaoDosCampos() {
        edtNome = findViewById(R.id.activity_formulario_aluno_nome);
        edtTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        edtEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void salva(Aluno alunoCriado) {
        dao.salva(alunoCriado);
        Toast.makeText(FormularioAlunoActivity.this,
                getString(R.string.string_msg_salvo), Toast.LENGTH_SHORT).show();
        finish();
    }

    private Aluno criaAluno() {
        String nome = edtNome.getText().toString();
        String telefone = edtTelefone.getText().toString();
        String email = edtEmail.getText().toString();
        return new Aluno(nome, telefone, email);
    }
}
