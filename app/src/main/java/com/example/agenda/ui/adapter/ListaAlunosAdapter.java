package com.example.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.agenda.R;
import com.example.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunosAdapter extends BaseAdapter {
    private final Context context;
    @SuppressWarnings("CanBeFinal")
    private List<Aluno> alunos = new ArrayList<>();

    public ListaAlunosAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View cardAluno = inflaCard(parent);
        vinculaCard(position, cardAluno);
        return cardAluno;

    }

    private void vinculaCard(int position, View cardAluno) {
        TextView tvNomeAluno = cardAluno.findViewById(R.id.item_aluno_nome);
        TextView tvTelefoneAluno = cardAluno.findViewById(R.id.item_aluno_telefone);
        tvNomeAluno.setText(alunos.get(position).getNome());
        tvTelefoneAluno.setText(alunos.get(position).getTelefone());
    }

    private View inflaCard(ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_aluno, parent, false);
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }

    public void atualiza(List<Aluno> alunos) {
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }
}

