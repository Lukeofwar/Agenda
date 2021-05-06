package com.example.diario.ui;

import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.diario.dao.DayDAO;
import com.example.diario.model.Day;
import com.example.diario.ui.adapter.ListaDaysAdapter;

public class ListaDaysView {

    private final ListaDaysAdapter adapter;
    private final DayDAO dao;
    private final Context context;

    public ListaDaysView(Context context) {
        this.context = context;
        this.adapter = new ListaDaysAdapter(this.context);
        dao = new DayDAO();
    }


    public void showDialogExclusionDay(@NonNull final MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle("Removing Day")
                    .setMessage("Are you sure you want to remove?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Day dayParaRemocao = (Day) adapter.getItem(menuInfo.position);
                    removeDayListAndDao(dayParaRemocao);

                })
                .setNegativeButton("No", null)
                .show();
    }

    public void removeDayListAndDao(Day dayClickedForRemoval) {
        dao.remove(dayClickedForRemoval);
        adapter.remove(dayClickedForRemoval);
    }

    public void configAdapter(ListView listOfDays) {
        listOfDays.setAdapter(adapter);
    }

    public void updateListDays() {
        adapter.update(dao.all());
    }
}


