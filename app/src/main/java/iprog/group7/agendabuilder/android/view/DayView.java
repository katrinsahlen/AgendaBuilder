package iprog.group7.agendabuilder.android.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import iprog.group7.agendabuilder.android.R;
import iprog.group7.agendabuilder.model.AgendaModel;

/**
 * View for day schedule, horizontal scrollable
 */
public class DayView implements Observer {

    View view;
    AgendaModel model;
    Button addDay, previousDay, nextDay;
    TextView whichDayTitle, endTime, totalLength;
    EditText startTime;

    public DayView(View view, AgendaModel model) {

        this.view = view;
        this.model = model;
        model.addObserver(this);

        whichDayTitle = (TextView) view.findViewById(R.id.which_day_title);
        startTime = (EditText) view.findViewById(R.id.start_time);
        endTime = (TextView) view.findViewById(R.id.end_time);
        totalLength = (TextView) view.findViewById(R.id.total_length);

        // RelativeLayout boxDayLayout = (RelativeLayout) view.findViewById(R.id.box_day_layout);

        addDay = (Button) view.findViewById(R.id.add_day_button);
        previousDay = (Button) view.findViewById(R.id.previous_day_button);
        nextDay = (Button) view.findViewById(R.id.next_day_button);

        // startTime.setText("08:00");

    }

    public void update(Observable observable, Object object) {

    }

}
