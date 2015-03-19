package iprog.group7.agendabuilder.android.view;

import android.view.View;

import iprog.group7.agendabuilder.model.AgendaModel;
import iprog.group7.agendabuilder.model.Day;

/**
 * Controller for click in the main view: DayView and TaskView
 */
public class MainViewClickController implements View.OnClickListener {

    AgendaModel model;
    DayView dayView;
    int dayIndex;

    public MainViewClickController(AgendaModel model, DayView dayView) {

        this.model = model;
        this.dayView = dayView;

        dayIndex = 1;
        model.addDay(8, 0);

        dayView.addDay.setOnClickListener(this);
        dayView.previousDay.setOnClickListener(this);
        dayView.nextDay.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == dayView.addDay) {
            // Change the following to value in EditText widget
            // model.addDay(8, 0);
            model.addDay(8, 0);
            dayIndex = model.getNumberOfDays();
            changeDayInMainView(model.getDay(dayIndex));
        }
        if (model.getNumberOfDays() > 1) {
            if (v == dayView.previousDay) {
                if (dayIndex > 1) {
                    dayIndex--;
                } else {
                    dayIndex = model.getNumberOfDays();
                }
                // changeDayInMainView(model.getDay(dayIndex));
            }
            if (v == dayView.nextDay) {
                if (dayIndex < model.getNumberOfDays()) {
                    dayIndex++;
                } else {
                    dayIndex = 1;
                }
                // changeDayInMainView(model.getDay(dayIndex));
            }
        }

    }

    private void changeDayInMainView(Day currentDay) {
        dayView.whichDayTitle.setText("Day " + dayIndex);
        // dayView.startTime.setText(day.getStart());
        dayView.endTime.setText(currentDay.getEnd());
        dayView.totalLength.setText(currentDay.getTotalLength());

    }

}
