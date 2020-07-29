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
    Context context;
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
        View cardAluno = LayoutInflater.from(context).inflate(R.layout.item_aluno, parent, false);
        TextView tvNomeAluno = cardAluno.findViewById(R.id.item_aluno_nome);
        TextView tvTelefoneAluno = cardAluno.findViewById(R.id.item_aluno_telefone);
        tvNomeAluno.setText(alunos.get(position).getNome());
        tvTelefoneAluno.setText(alunos.get(position).getTelefone());
        return cardAluno;

    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }

    public void addAll(List<Aluno> alunos) {
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

    public void clear() {
        alunos.clear();
        notifyDataSetChanged();
    }
}

