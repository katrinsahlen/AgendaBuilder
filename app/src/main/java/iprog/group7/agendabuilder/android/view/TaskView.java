package iprog.group7.agendabuilder.android.view;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import java.util.Observable;
import java.util.Observer;

import iprog.group7.agendabuilder.android.R;
import iprog.group7.agendabuilder.model.AgendaModel;

/**
 * Upper view in MainActivity
 */
public class TaskView implements Observer {

    View view;
    AgendaModel model;
    Button addTaskButton;

    public TaskView(View view, AgendaModel model) {

        this.view = view;
        this.model = model;
        model.addObserver(this);
        addTaskButton = (Button) view.findViewById(R.id.add_task_button);

    }

    public void update(Observable observable, Object object) {

    }

}
