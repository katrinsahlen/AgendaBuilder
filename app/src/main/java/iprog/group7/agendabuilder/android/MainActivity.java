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

        model.addDay(8, 0);
        model.setCurrentDayIndex(1);

        // Instantiate views
        dayView = new DayView(findViewById(R.id.page_main_view_id), model);
        dayView.timeSetup(model);

        dayView.addDay.setOnClickListener(this);
        dayView.previousDay.setOnClickListener(this);
        dayView.nextDay.setOnClickListener(this);


        // The following section creates the lists/boxes of tasks and sets adapters, to update them.
        boxTasksLayout = (ListView) findViewById(R.id.box_tasks_layout);
        boxDayLayout = (ListView) findViewById(R.id.box_day_layout);

        int currentDayIndex = model.getCurrentDayIndex();
        adapterBoxTasksLayout = new TaskArrayAdapter(model, this, android.R.layout.simple_list_item_1, model.getParkedActivites());
        adapterBoxDayLayout = new TaskArrayAdapter(model, this, android.R.layout.simple_list_item_1, model.getDay(currentDayIndex).getActivities());

        boxTasksLayout.setAdapter(adapterBoxTasksLayout);
        boxDayLayout.setAdapter(adapterBoxDayLayout);

        dragTaskListener = new DragTaskListener();

        boxTasksLayout.setOnDragListener(dragTaskListener);
        boxDayLayout.setOnDragListener(dragTaskListener);

        onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow = new DragTaskShadowBuilder(view);
                view.startDrag(data, shadow, parent.getItemAtPosition(position), 0);
                return true;
            }
        };

        boxTasksLayout.setOnItemLongClickListener(onItemLongClickListener);
        boxDayLayout.setOnItemLongClickListener(onItemLongClickListener);

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapterBoxDayLayout.notifyDataSetChanged();
        adapterBoxTasksLayout.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

        int currentDayIndex = model.getCurrentDayIndex();

        if (v == dayView.addDay) {
            model.addDay(8, 0);
            currentDayIndex = model.getNumberOfDays();
            model.setCurrentDayIndex(currentDayIndex);
            dayView.timeSetup(model);
            changeDayAdapter();
        }
        if (model.getNumberOfDays() > 1) {
            if (v == dayView.previousDay) {
                if (currentDayIndex > 1) {
                    currentDayIndex--;
                } else {
                    currentDayIndex = model.getNumberOfDays();
                }
                model.setCurrentDayIndex(currentDayIndex);
            }
            if (v == dayView.nextDay) {
                if (currentDayIndex < model.getNumberOfDays()) {
                    currentDayIndex++;
                } else {
                    currentDayIndex = 1;
                }
                model.setCurrentDayIndex(currentDayIndex);
            }
            dayView.timeSetup(model);
            changeDayAdapter();
        }
    }

    private void changeDayAdapter() {
        int currentDayIndex = model.getCurrentDayIndex();
        adapterBoxDayLayout = new TaskArrayAdapter(model, this, android.R.layout.simple_list_item_1, model.getDay(currentDayIndex).getActivities());
        boxDayLayout.setAdapter(adapterBoxDayLayout);
        boxDayLayout.setOnDragListener(dragTaskListener);
        boxDayLayout.setOnItemLongClickListener(onItemLongClickListener);
    }

    private void setupActivities() {
        // Activities added for testing
        model.addParkedActivity(new iprog.group7.agendabuilder.model.Activity("Demo", "Demo descr", 30, 1));
        model.addParkedActivity(new iprog.group7.agendabuilder.model.Activity("Brainstorming", "Brainstorming descr", 60, 2));
        model.addParkedActivity(new iprog.group7.agendabuilder.model.Activity("QA session", "QA session descr", 20, 3));
        model.addParkedActivity(new iprog.group7.agendabuilder.model.Activity("Coffee break", "Coffee break descr", 10, 4));
    }

    public void addTask(View view) {
        Intent intent = new Intent(this, AddTaskActivity.class);


        intent.putExtra(SOURCE, "new");
        startActivity(intent);
        onPause();

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
                    iprog.group7.agendabuilder.model.Activity item = (iprog.group7.agendabuilder.model.Activity) event.getLocalState();
                    List<iprog.group7.agendabuilder.model.Activity> parkedActivities, dayActivities;
                    parkedActivities = model.getParkedActivites();
                    dayActivities = model.getDay(model.getCurrentDayIndex()).getActivities();
                    if (v == boxTasksLayout) {
                        if (!parkedActivities.contains(item)) {
                            int currentPosition = 0;
                            for (iprog.group7.agendabuilder.model.Activity a : dayActivities) {
                                if (a == item) {
                                    break;
                                }
                                currentPosition++;
                            }
                            model.moveActivity(model.getDay(model.getCurrentDayIndex()), currentPosition, null, parkedActivities.size());
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
