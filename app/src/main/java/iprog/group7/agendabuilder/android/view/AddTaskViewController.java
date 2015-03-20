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
    AddTaskActivity act;


    public AddTaskViewController(AgendaModel model, AddTaskView view) {
        this.model = model;
        this.view = view;

        //listeners
        view.btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.model = model;
        this.view = view;

        if (v == view.btnSave) {
            //add attributes to activity

            //add name
            a.setName(view.txtName.getText().toString());
            //add length
            a.setLength(Integer.parseInt(view.txtLength.getText().toString()));
            //add type
            a.setType(view.spinnerType.getSelectedItemPosition()+1);
            //add description
            a.setDescription(view.txtDescription.getText().toString());

            //finally add activity to AgendaModel
            model.addParkedActivity(a);

            //return to main
            act.returnToMain(v);

        }

    }
}
