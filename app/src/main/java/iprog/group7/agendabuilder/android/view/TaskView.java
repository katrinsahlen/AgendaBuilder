package iprog.group7.agendabuilder.android.view;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import iprog.group7.agendabuilder.android.R;
import iprog.group7.agendabuilder.model.Activity;
import iprog.group7.agendabuilder.model.AgendaModel;

/**
 * Upper view in MainActivity
 */
public class TaskView implements Observer {

    View view;
    AgendaModel model;
    // public TextView taskBoxx;
    // public RelativeLayout boxTasksLayout;
    FrameLayout taskBox;
    Button addTaskButton;

    public TaskView(View view, AgendaModel model) {

        this.view = view;
        this.model = model;
        model.addObserver(this);
        addTaskButton = (Button) view.findViewById(R.id.add_task_button);
        // boxTasksLayout = (RelativeLayout) view.findViewById(R.id.box_tasks_layout);
        TextView taskStartTime = (TextView) view.findViewById(R.id.task_start_time);
        // taskBoxx = (TextView) view.findViewById(R.id.task_box);
        // taskBox = (FrameLayout) view.findViewById(R.id.task_box_framelayout);

    }

    public void update(Observable observable, Object object) {

    }

}
