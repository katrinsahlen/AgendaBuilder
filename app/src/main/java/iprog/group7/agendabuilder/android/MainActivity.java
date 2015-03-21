package iprog.group7.agendabuilder.android;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import iprog.group7.agendabuilder.android.view.DayView;
import iprog.group7.agendabuilder.android.view.TaskArrayAdapter;
import iprog.group7.agendabuilder.model.AgendaModel;

/**
 * The activity controlling views AddDayView, DayView and TaskView
 */
public class MainActivity extends Activity  implements View.OnClickListener  {


    public final static String SOURCE = "";

    AgendaModel model;
    ListView boxTasksLayout, boxDayLayout;
    DayView dayView;
    ArrayAdapter<iprog.group7.agendabuilder.model.Activity> adapterBoxTasksLayout, adapterBoxDayLayout;
    DragTaskListener dragTaskListener;
    AdapterView.OnItemLongClickListener onItemLongClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        model = ((AgendaBuilderApplication) this.getApplication()).getModel();
        if (model.getNumberOfDays() == 0) {
            model.addDay(8, 0);
            model.setCurrentDayIndex(1);
        }

        // Instantiate views
        dayView = new DayView(findViewById(R.id.page_main_view_id), model);
        dayView.timeSetup(model);

        // Set onClickListeners for the buttons in DayView
        dayView.addDay.setOnClickListener(this);
        dayView.previousDay.setOnClickListener(this);
        dayView.nextDay.setOnClickListener(this);

        // The following section creates the lists/boxes of tasks and sets adapters, to update them.
        boxTasksLayout = (ListView) findViewById(R.id.box_tasks_layout);
        boxDayLayout = (ListView) findViewById(R.id.box_day_layout);

        setupBoxAdapters();
        setupOnLongClickListeners();

    }

    /**
     * Back from AddTaskActivity
     */
    @Override
    protected void onResume() {
        super.onResume();
        adapterBoxDayLayout.notifyDataSetChanged();
        adapterBoxTasksLayout.notifyDataSetChanged();
    }

    /**
     * The user has clicked the button "Add Activity"
     * @param view
     */
    public void addTask(View view) {
        // Control and edit the day's start time, before adding task
        setupDayStart();

        Intent intent = new Intent(this, AddTaskActivity.class);

        intent.putExtra(SOURCE, "new");
        startActivity(intent);
        onPause();
    }

    /**
     * A button in DayView has been clicked on. v is the button clicked on.
     * @param v
     */
    @Override
    public void onClick(View v) {

        // Save changed time in start time view
        setupDayStart();

        int currentDayIndex = model.getCurrentDayIndex();

        // The user has clicked "Add day"
        if (v == dayView.addDay) {
            model.addDay(8, 0);
            currentDayIndex = model.getNumberOfDays();
            model.setCurrentDayIndex(currentDayIndex);
            changeDayAdapter();
        }
        if (model.getNumberOfDays() > 1) {
            // The user has clicked "Previous" (day)
            if (v == dayView.previousDay) {
                if (currentDayIndex > 1) {
                    currentDayIndex--;
                } else {
                    currentDayIndex = model.getNumberOfDays();
                }
                model.setCurrentDayIndex(currentDayIndex);
            }
            // The user has clicked "Next" (day)
            if (v == dayView.nextDay) {
                if (currentDayIndex < model.getNumberOfDays()) {
                    currentDayIndex++;
                } else {
                    currentDayIndex = 1;
                }
                model.setCurrentDayIndex(currentDayIndex);
            }
            changeDayAdapter();
        }
    }

    private void setupDayStart() {
        int currentDayIndex = model.getCurrentDayIndex();
        int oldTime = model.getDay(currentDayIndex).getStart();
        String[] newTime = dayView.startTime.getText().toString().split(":");
        int newHour = Integer.parseInt(newTime[0]) * 60;
        int newMin = Integer.parseInt(newTime[1]);

        if ((oldTime != (newHour + newMin)) && (newHour / 60 < 24) && (newMin < 60)) {
            model.getDay(currentDayIndex).setStart(newHour + newMin);
            dayView.timeSetup(model);
        }
    }

    private void setupBoxAdapters() {
        // Setup the adapters for the day box and the task box
        int currentDayIndex = model.getCurrentDayIndex();
        adapterBoxTasksLayout = new TaskArrayAdapter(model, this, android.R.layout.simple_list_item_1, model.getParkedActivites());
        adapterBoxDayLayout = new TaskArrayAdapter(model, this, android.R.layout.simple_list_item_1, model.getDay(currentDayIndex).getActivities());
        boxTasksLayout.setAdapter(adapterBoxTasksLayout);
        boxDayLayout.setAdapter(adapterBoxDayLayout);

        // Make the day box and tasks box able to listen to drag events
        dragTaskListener = new DragTaskListener();
        boxTasksLayout.setOnDragListener(dragTaskListener);
        boxDayLayout.setOnDragListener(dragTaskListener);
    }

    private void setupOnLongClickListeners() {
        // Actions performed when the user makes a long clock on a task in a box
        onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow = new DragTaskShadowBuilder(view);
                view.startDrag(data, shadow, parent.getItemAtPosition(position), 0);
                return true;
            }
        };

        // Det the day box and task box to listen to the onLongClickListener
        boxTasksLayout.setOnItemLongClickListener(onItemLongClickListener);
        boxDayLayout.setOnItemLongClickListener(onItemLongClickListener);
    }

    // Update adapter for the day box, since the current day (that is showing) has changed
    private void changeDayAdapter() {
        int currentDayIndex = model.getCurrentDayIndex();
        adapterBoxDayLayout = new TaskArrayAdapter(model, this, android.R.layout.simple_list_item_1, model.getDay(currentDayIndex).getActivities());
        boxDayLayout.setAdapter(adapterBoxDayLayout);
        boxDayLayout.setOnDragListener(dragTaskListener);
        boxDayLayout.setOnItemLongClickListener(onItemLongClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    /**
     * Modified example on an OnDragListener from developer.android.com
     * The class is inside the MainActivity because it needs to be able to access the box layouts
     */
    public class DragTaskListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {

            final int action = event.getAction();

            switch(action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // Determines if this View can accept the dragged data
                    if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        v.invalidate();
                        return true;
                    }
                    return false;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.invalidate();
                    return true;
                case DragEvent.ACTION_DRAG_LOCATION:
                    return true;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.invalidate();
                    return true;
                case DragEvent.ACTION_DROP:
                    // Get the dropped task/activity
                    iprog.group7.agendabuilder.model.Activity item = (iprog.group7.agendabuilder.model.Activity) event.getLocalState();
                    // Get list of parked activities and activities in day box
                    List<iprog.group7.agendabuilder.model.Activity> parkedActivities, dayActivities;
                    parkedActivities = model.getParkedActivites();
                    dayActivities = model.getDay(model.getCurrentDayIndex()).getActivities();
                    // See which box the task is dropped in, and see if the task originated from that
                    // box (then do nothing), or if it is a new box (move task to new box and update model)
                    if (v == boxTasksLayout) {
                        if (!parkedActivities.contains(item)) {
                            int currentPosition = 0;
                            for (iprog.group7.agendabuilder.model.Activity a : dayActivities) {
                                if (a == item) {
                                    break;
                                }
                                currentPosition++;
                            }
                            model.moveActivity(model.getDay(model.getCurrentDayIndex()), currentPosition-1, null, parkedActivities.size());
                        }
                    } else if (v == boxDayLayout) {
                        if (!dayActivities.contains(item)) {
                            int currentPosition = 0;
                            for (iprog.group7.agendabuilder.model.Activity a : parkedActivities) {
                                if (a == item) {
                                    break;
                                }
                                currentPosition++;
                            }
                            model.moveActivity(null, currentPosition, model.getDay(model.getCurrentDayIndex()), dayActivities.size());
                        }
                    }
                    // Notify adapters, to update the appearence of the tasks, which depend on in
                    // which box they are located
                    adapterBoxDayLayout.notifyDataSetChanged();
                    adapterBoxTasksLayout.notifyDataSetChanged();
                    v.invalidate();
                    return true;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.invalidate();
                    return true;
                default:
                    break;
            }
            return false;
        }
    }
}
