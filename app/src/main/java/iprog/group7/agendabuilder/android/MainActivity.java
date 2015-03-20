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

import java.util.ArrayList;
import java.util.List;

import iprog.group7.agendabuilder.android.view.DayView;
import iprog.group7.agendabuilder.android.view.MainViewClickController;
import iprog.group7.agendabuilder.android.view.MainViewDragController;
import iprog.group7.agendabuilder.android.view.TaskView;
import iprog.group7.agendabuilder.model.AgendaModel;
import iprog.group7.agendabuilder.model.Day;

/**
 * The activity controlling views AddDayView, DayView and TaskView
 */
public class MainActivity extends Activity {

    AgendaModel model;
    Day currentDay;
    ListView boxTasksLayout, boxDayLayout;
    ArrayAdapter<iprog.group7.agendabuilder.model.Activity> adapterBoxTasksLayout, adapterBoxDayLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        model = ((AgendaBuilderApplication) this.getApplication()).getModel();
        currentDay = model.addDay(8, 0);

        // Instantiate views
        DayView dayView = new DayView(findViewById(R.id.page_main_view_id), model, currentDay);
        TaskView taskView = new TaskView(findViewById(R.id.page_main_view_id), model);

        MainViewClickController mainViewClickController = new MainViewClickController(model, dayView);
        MainViewDragController mainViewDragController = new MainViewDragController(model, dayView, taskView);

        setupActivities();

    }

    private void setupActivities() {
        // Activities added for testing
        model.addParkedActivity(new iprog.group7.agendabuilder.model.Activity("Demo", "Demo descr", 30, 1));
        model.addParkedActivity(new iprog.group7.agendabuilder.model.Activity("Brainstorming", "Brainstorming descr", 60, 2));
        model.addParkedActivity(new iprog.group7.agendabuilder.model.Activity("QA session", "QA session descr", 20, 3));
        model.addParkedActivity(new iprog.group7.agendabuilder.model.Activity("Coffee break", "Coffee break descr", 10, 4));

        boxTasksLayout = (ListView) findViewById(R.id.box_tasks_layout);
        boxDayLayout = (ListView) findViewById(R.id.box_day_layout);

        adapterBoxTasksLayout = new TaskArrayAdapter(model, currentDay, this, android.R.layout.simple_list_item_1, model.getParkedActivites());
        adapterBoxDayLayout = new TaskArrayAdapter(model, currentDay, this, android.R.layout.simple_list_item_1, currentDay.getActivities());

        boxTasksLayout.setAdapter(adapterBoxTasksLayout);
        boxDayLayout.setAdapter(adapterBoxDayLayout);

        DragTaskListener dragTaskListener = new DragTaskListener();

        boxTasksLayout.setOnDragListener(dragTaskListener);
        boxDayLayout.setOnDragListener(dragTaskListener);

        AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
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

    public void addTask(View view) {
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);
        // this.onPause();
        finish();
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
                    dayActivities = currentDay.getActivities();
                    if (v == boxTasksLayout) {
                        if (!parkedActivities.contains(item)) {
                            int currentPosition = 1;
                            for (iprog.group7.agendabuilder.model.Activity a : dayActivities) {
                                if (a == item) {
                                    break;
                                }
                                currentPosition++;
                            }
                            model.moveActivity(currentDay, currentPosition, null, parkedActivities.size());
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
                            model.moveActivity(null, currentPosition, currentDay, dayActivities.size());
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
