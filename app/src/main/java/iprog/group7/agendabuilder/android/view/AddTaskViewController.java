package iprog.group7.agendabuilder.android.view;


import android.content.Context;
import android.content.Intent;
import android.view.View;

import iprog.group7.agendabuilder.android.AddTaskActivity;
import iprog.group7.agendabuilder.android.MainActivity;
import iprog.group7.agendabuilder.model.Activity;
import iprog.group7.agendabuilder.model.AgendaModel;

public class AddTaskViewController implements View.OnClickListener {

    AddTaskView view;
    AgendaModel model;
    Activity a;


    public AddTaskViewController(AgendaModel model, AddTaskView view) {
        this.model = model;
        this.view = view;

        //listeners
        view.btnCancel.setOnClickListener(this);
        view.btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.model = model;
        this.view = view;

        if (v == view.btnSave) {
            //add data to model

            //add name
            a.setName();
            //add length
            //add type
            //add description
        }

    }
}
