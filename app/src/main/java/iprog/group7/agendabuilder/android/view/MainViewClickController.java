package iprog.group7.agendabuilder.android.view;

import android.content.Intent;
import android.view.View;

import iprog.group7.agendabuilder.android.AddTaskActivity;
import iprog.group7.agendabuilder.android.MainActivity;
import iprog.group7.agendabuilder.model.AgendaModel;

/**
 * Controller for click in the main view: DayView and TaskView
 */
public class MainViewClickController implements View.OnClickListener {

    AgendaModel model;
    DayView dayView;

    public MainViewClickController(AgendaModel model, DayView dayView) {

        this.model = model;
        this.dayView = dayView;

        dayView.addDay.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        // if (v == dayView.addDay) {
            // Change the following to value in EditText widget
            // model.addDay(8, 0);
            // Intent intent = new Intent(MainActivity.class, AddTaskActivity.class);
            // startActivity(intent);
        // }

    }

}
