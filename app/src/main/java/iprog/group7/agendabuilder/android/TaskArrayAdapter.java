package iprog.group7.agendabuilder.android;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import iprog.group7.agendabuilder.model.Activity;

/**
 * An ArrayAdapter for handling tasks
 */
public class TaskArrayAdapter extends ArrayAdapter<Activity> {

    Context context;
    int resource;
    List<Activity> objects;
    int purpleish, blueish, yellowish, redish;

    public TaskArrayAdapter(Context context, int resource, List<Activity> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        purpleish = Color.parseColor("#6E78C7");
        blueish = Color.parseColor("#6BD1C0");
        yellowish = Color.parseColor("#D1BD6E");
        redish = Color.parseColor("#C78465");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Activity item = objects.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = inflater.inflate(R.layout.single_task, parent, false);

        TextView taskBox = (TextView) row.findViewById(R.id.task_box);
        TextView taskLength = (TextView) row.findViewById(R.id.task_length);

        int type = item.getType();
        if (type == 1) {
            taskBox.setBackgroundColor(purpleish);
        } else if (type == 2) {
            taskBox.setBackgroundColor(blueish);
        } else if (type == 3) {
            taskBox.setBackgroundColor(yellowish);
        } else if (type == 4) {
            taskBox.setBackgroundColor(redish);
        }
        taskBox.setText(item.getName());
        taskLength.setText(item.getLength() + " min");

        return row;
    }

}
