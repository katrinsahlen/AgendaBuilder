package iprog.group7.agendabuilder.android.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.Observable;
import java.util.Observer;

import iprog.group7.agendabuilder.model.Activity;
import iprog.group7.agendabuilder.model.AgendaModel;

/**
 * Pop up view when creating task
 */
public class AddTaskView implements Observer{

    View view;
    Activity activity;
    AgendaModel model;
    Context context;

    public AddTaskView(Context context, Activity activity, AgendaModel model) {
        this.view = (ViewGroup) view;
        this.model = model;

        //observer
        model.addObserver(this);

        //Name

        //Length

        //dropdown type list

        //Description


    }

    @Override
    public void update(Observable observable, Object data) {

    }
}
