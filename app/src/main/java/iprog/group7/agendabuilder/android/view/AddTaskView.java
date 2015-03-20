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

import iprog.group7.agendabuilder.android.AddTaskActivity;
import iprog.group7.agendabuilder.android.R;
import iprog.group7.agendabuilder.model.Activity;
import iprog.group7.agendabuilder.model.AgendaModel;

/**
 * Pop up view when creating task
 */
public class AddTaskView implements Observer{

    View view = null;
    Activity activity;
    AgendaModel model = null;

    private String source = "d";

    Button btnCancel, btnSave;
    EditText txtName, txtLength, txtDescription;
    Spinner spinnerType;



    public AddTaskView(View view, AgendaModel model, String src) {
        this.model = model;
        this.view = view;
        this.source = src;

        //observer
        model.addObserver(this);
        //buttons

        this.btnCancel = (Button) view.findViewById(R.id.btnCancel);
        this.btnSave = (Button) view.findViewById(R.id.btnSave);

        //Name
        this.txtName = (EditText) view.findViewById(R.id.txtName);
        //Length
        this.txtLength = (EditText) view.findViewById(R.id.txtLength);
        //Type list - completed using xml string
        this.spinnerType = (Spinner) view.findViewById(R.id.spinnerType);
        //Description
        this.txtDescription = (EditText) view.findViewById(R.id.txtDescription);

        txtName.setText(source);

        //NEW ACTIVITY
        if (source.equals("new"))
        {
            //set default values
           txtName.setText("(Enter Name)");
           txtLength.setText("30");
           txtDescription.setText("(Enter Description)");
           spinnerType.setSelection(0);

        //EDIT ACTIVITY
        } else if (source.equals("edit"))
        {
            //load data from object
            //TODO: load data
        }

    }

    @Override
    public void update(Observable observable, Object data) {

    }

    public String getSource()
    {
        return this.source;
    }
    public void setSource(String val)
    {
        this.source = val;
    }

}
