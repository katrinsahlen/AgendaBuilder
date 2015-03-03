package iprog.group7.agendabuilder.android.view;

import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import iprog.group7.agendabuilder.android.R;

/**
 * View for day schedule, horizontal scrollable
 */
public class DayView {

    View view;

    public DayView(View view) {

        this.view = view;
        TextView whichDayTitle = (TextView) view.findViewById(R.id.which_day_title);
        EditText startTime = (EditText) view.findViewById(R.id.start_time);
        TextView endTime = (TextView) view.findViewById(R.id.end_time);
        TextView totalLength = (TextView) view.findViewById(R.id.total_length);
        RelativeLayout boxDayLayout = (RelativeLayout) view.findViewById(R.id.box_day_layout);

    }

}
