package iprog.group7.agendabuilder.android.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import iprog.group7.agendabuilder.android.R;
import iprog.group7.agendabuilder.model.AgendaModel;
import iprog.group7.agendabuilder.model.Day;

/**
 * View for day schedule, horizontal scrollable
 */
public class DayView implements Observer {

    View view;
    AgendaModel model;
    public Button addDay, previousDay, nextDay;
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

        addDay = (Button) view.findViewById(R.id.add_day_button);
        previousDay = (Button) view.findViewById(R.id.previous_day_button);
        nextDay = (Button) view.findViewById(R.id.next_day_button);

        timeSetup();
    }

    public void update(Observable observable, Object object) {
        timeSetup();
    }

    public void timeSetup() {
        int currentDay = model.getCurrentDayIndex();
        Day day = model.getDay(currentDay);
        whichDayTitle.setText("Day" + currentDay);
        String[] timesStart = addZeroToTime(day.getEnd());
        String[] timesEnd = addZeroToTime(day.getEnd());
        startTime.setText(timesStart[0] + ":" + timesStart[1]);
        endTime.setText(timesEnd[0] + ":" + timesEnd[1]);
        totalLength.setText(day.getTotalLength() + " min");
    }

    private String[] addZeroToTime(int time) {
        int[] times = {time / 60, time % 60};
        String[] timeText = new String[2];
        for (int i = 0; i < times.length; i++) {
            if (times[i] < 10) {
                timeText[i] = "0" + times[i];
            } else {
                timeText[i] = "" + times[i];
            }
        }
        return timeText;
    }

}
