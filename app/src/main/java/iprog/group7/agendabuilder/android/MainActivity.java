package iprog.group7.agendabuilder.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

/**
 * The activity controlling views AddDayView, DayView and TaskView
 */
public class MainActivity extends Activity {

    AgendaModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        model = ((AgendaBuilderApplication) this.getApplication()).getModel();

        // Instantiate views
        DayView dayView = new DayView(findViewById(R.id.page_main_view_id), model);
        TaskView taskView = new TaskView(findViewById(R.id.page_main_view_id), model);

        MainViewClickController mainViewClickController = new MainViewClickController(model, dayView);
        MainViewDragController mainViewDragController = new MainViewDragController(model, dayView, taskView);

        setupActivities();

    }

    private void setupActivities() {
        model.addParkedActivity(new iprog.group7.agendabuilder.model.Activity("Demo", "Demo descr", 30, 1));
        model.addParkedActivity(new iprog.group7.agendabuilder.model.Activity("Brainstorming", "Brainstorming descr", 60, 2));
        model.addParkedActivity(new iprog.group7.agendabuilder.model.Activity("QA session", "QA session descr", 20, 3));
        model.addParkedActivity(new iprog.group7.agendabuilder.model.Activity("Coffee break", "Coffee break descr", 10, 4));

        List<iprog.group7.agendabuilder.model.Activity> parkedActivities = model.getParkedActivites();

        final List<String> taskBoxTasks = new ArrayList<>();
        final List<String> dayBoxTasks = new ArrayList<>();
        for (iprog.group7.agendabuilder.model.Activity a : parkedActivities) {
            taskBoxTasks.add(a.getName());
        }

        final ListView boxTasksLayout = (ListView) findViewById(R.id.box_tasks_layout);
        final ListView boxDayLayout = (ListView) findViewById(R.id.box_day_layout);

        final ArrayAdapter<String> adapterBoxTasksLayout = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, taskBoxTasks);
        final ArrayAdapter<String> adapterBoxDayLayout = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dayBoxTasks);
        boxTasksLayout.setAdapter(adapterBoxTasksLayout);
        boxDayLayout.setAdapter(adapterBoxDayLayout);

        boxTasksLayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = (String) boxTasksLayout.getItemAtPosition(position);
                dayBoxTasks.add(itemValue);
                taskBoxTasks.remove(itemValue);
                adapterBoxDayLayout.notifyDataSetChanged();
                adapterBoxTasksLayout.notifyDataSetChanged();
            }
        });

        boxTasksLayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = (String) boxTasksLayout.getItemAtPosition(position);
                dayBoxTasks.add(itemValue);
                taskBoxTasks.remove(itemValue);
                adapterBoxDayLayout.notifyDataSetChanged();
                adapterBoxTasksLayout.notifyDataSetChanged();
            }
        });

    }


    public void addTask(View view) {
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);
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

}
