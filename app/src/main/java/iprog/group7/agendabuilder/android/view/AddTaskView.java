package iprog.group7.agendabuilder.android.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import iprog.group7.agendabuilder.android.R;
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



    // Selection of the spinner
    Spinner spinnerType;

    public AddTaskView(Context context, Activity activity, AgendaModel model) {
        this.model = model;

        //observer
        model.addObserver(this);


        //Name

        //Length

        //Type list - completed using xml string

        //Description


    }

    @Override
    public void update(Observable observable, Object data) {

    }
}
