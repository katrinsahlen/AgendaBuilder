package iprog.group7.agendabuilder.android.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
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
    Button addDay;

    public DayView(View view, AgendaModel model) {

        this.view = view;
        this.model = model;
        model.addObserver(this);
        TextView whichDayTitle = (TextView) view.findViewById(R.id.which_day_title);
        EditText startTime = (EditText) view.findViewById(R.id.start_time);
        TextView endTime = (TextView) view.findViewById(R.id.end_time);
        TextView totalLength = (TextView) view.findViewById(R.id.total_length);
        RelativeLayout boxDayLayout = (RelativeLayout) view.findViewById(R.id.box_day_layout);
        addDay = (Button) view.findViewById(R.id.add_day_button);

    }

    public void update(Observable observable, Object object) {

    }

}
