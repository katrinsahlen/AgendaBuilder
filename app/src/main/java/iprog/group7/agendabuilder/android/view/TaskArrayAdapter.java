package iprog.group7.agendabuilder.android.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import iprog.group7.agendabuilder.android.R;
import iprog.group7.agendabuilder.model.Activity;
import iprog.group7.agendabuilder.model.AgendaModel;
import iprog.group7.agendabuilder.model.Day;

/**
 * An ArrayAdapter for handling and updating tasks
 * This also represents the view of each task in the boxes/lists
 */
public class TaskArrayAdapter extends ArrayAdapter<Activity> {

    AgendaModel model;
    Context context;
    int resource;
    List<Activity> objects;
    int[] color = {Color.parseColor("#6E78C7"), Color.parseColor("#6BD1C0"), Color.parseColor("#D1BD6E"), Color.parseColor("#C78465")};

    public TaskArrayAdapter(AgendaModel model, Context context, int resource, List<Activity> objects) {
        super(context, resource, objects);
        this.model = model;
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get task/activity
        Activity item = objects.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Get layout of a single task
        View row = inflater.inflate(R.layout.single_task, parent, false);

        TextView taskBox = (TextView) row.findViewById(R.id.task_box);
        TextView taskLength = (TextView) row.findViewById(R.id.task_length);

        // Update the color of the task depending on the task/activity type.
        taskBox.setBackgroundColor(color[item.getType()-1]);
        // Set name depending on the task/activity
        taskBox.setText(item.getName());
        int currentDayIndex = model.getCurrentDayIndex();
        // Check if the task is parked or in day box.
        // If parked: The left text view should show the length of the task
        // If in day box: The left text view should show the starting time of the task
        if (model.getParkedActivites().contains(item)) {
            taskLength.setText(item.getLength() + " min");
        } else {
            int startTime = model.getDay(currentDayIndex).getStart();
            int i = 0;
            for (Activity activity : model.getDay(currentDayIndex).getActivities()) {
                if (i != position) {
                    startTime += activity.getLength();
                } else {
                    break;
                }
                i++;
            }
            int startHour = startTime / 60;
            int startMin = startTime % 60;
            String startH = addZeroToTime(startHour);
            String startM = addZeroToTime(startMin);
            taskLength.setText(startH + ":" + startM);
        }

        return row;
    }

    private String addZeroToTime(int time) {
        if (time < 10) {
            return "0" + time;
        } else {
            return "" + time;
        }
    }

}
