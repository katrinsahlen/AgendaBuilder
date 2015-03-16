package iprog.group7.agendabuilder.android.view;


import android.content.Intent;
import android.view.View;

import iprog.group7.agendabuilder.android.AddTaskActivity;
import iprog.group7.agendabuilder.android.MainActivity;
import iprog.group7.agendabuilder.model.AgendaModel;

public class AddTaskViewController implements View.OnClickListener {

    AddTaskView view;
    AgendaModel model;

    public AddTaskViewController(AgendaModel model, AddTaskView view)
    {
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

        if (v == view.btnCancel){
            //return to main page
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);

        } else if (v == view.btnSave){
           //add data to model

        }

}
