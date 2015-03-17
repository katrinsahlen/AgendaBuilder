package iprog.group7.agendabuilder.android.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

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

    Button btnCancel, btnSave;
    EditText txtName, txtLength, txtDescription;



    // Selection of the spinner
    Spinner spinnerType;

    public AddTaskView(Context context, Activity activity, AgendaModel model) {
        this.model = model;

        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnSave = (Button) view.findViewById(R.id.btnSave);



        //observer
        model.addObserver(this);


        //Name
        txtName = (EditText) view.findViewById(R.id.txtName);
        //Length
        txtLength = (EditText) view.findViewById(R.id.txtLength);

        //Type list - completed using xml string
        spinnerType = (Spinner) view.findViewById(R.id.spinnerType);
        //Description
        txtDescription = (EditText) view.findViewById(R.id.txtdescription);
    }

    @Override
    public void update(Observable observable, Object data) {

    }
}
