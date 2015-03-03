package iprog.group7.agendabuilder.android.view;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import iprog.group7.agendabuilder.android.R;

/**
 * Upper view in MainActivity
 */
public class TaskView {

    View view;

    public TaskView(View view) {

        this.view = view;
        Button addTaskButton = (Button) view.findViewById(R.id.add_task_button);
        RelativeLayout boxTasksLayout = (RelativeLayout) view.findViewById(R.id.box_tasks_layout);

    }

}
