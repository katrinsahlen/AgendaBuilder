package iprog.group7.agendabuilder.android.view;


import android.view.View;

import iprog.group7.agendabuilder.model.AgendaModel;

public class AddTaskViewController implements View.OnClickListener {

    AddTaskView view;
    AgendaModel model;

    @Override
    public void onClick(View v) {
        this.model = model;
        this.view = view;

        //listeners
        //view.imgStarter1.setOnClickListener(this);
        //view.imgStarter2.setOnClickListener(this);

    }
}
