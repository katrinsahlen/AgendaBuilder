package iprog.group7.agendabuilder.android.view;

import android.view.View;
import android.widget.TextView;

import iprog.group7.agendabuilder.android.R;
import iprog.group7.agendabuilder.model.AgendaModel;

/**
 * Appearance for each task in the task box
 */
public class SingleTaskView {

    View view;
    AgendaModel model;
    TextView taskLength, taskBox;


    public SingleTaskView(View view, AgendaModel model, String name, int length) {

        this.view = view;
        this.model = model;

        taskLength = (TextView) view.findViewById(R.id.task_length);
        taskBox = (TextView) view.findViewById(R.id.task_box);

        taskLength.setText(length + " min");
        taskBox.setText(name);
        // taskBox.setBackgroundColor();

    }

}
