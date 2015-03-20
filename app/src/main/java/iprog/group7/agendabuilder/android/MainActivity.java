package iprog.group7.agendabuilder.android;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import iprog.group7.agendabuilder.android.view.DayView;
import iprog.group7.agendabuilder.android.view.MainViewClickController;
import iprog.group7.agendabuilder.android.view.MainViewDragController;
import iprog.group7.agendabuilder.android.view.TaskView;
import iprog.group7.agendabuilder.model.AgendaModel;

/**
 * The activity controlling views AddDayView, DayView and TaskView
 */
public class MainActivity extends Activity {


    public final static String SOURCE = "";

    AgendaModel model;
    // Set<TaskFragment> fragments;
    FragmentTransaction transaction;
    int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = 0;

        model = ((AgendaBuilderApplication) this.getApplication()).getModel();

        // Instantiate views
        DayView dayView = new DayView(findViewById(R.id.page_main_view_id), model);
        TaskView taskView = new TaskView(findViewById(R.id.page_main_view_id), model);

        MainViewClickController mainViewClickController = new MainViewClickController(model, dayView);
        MainViewDragController mainViewDragController = new MainViewDragController(model, dayView, taskView);

        // transaction = getFragmentManager().beginTransaction();
        // transaction.commit();

    }


    public void addTask(View view) {
        Intent intent = new Intent(this, AddTaskActivity.class);
        String source = "new";
        intent.putExtra(SOURCE, source);
        startActivity(intent);

        /**
         // Create new fragment
         // FragmentTransaction transaction = getFragmentManager().beginTransaction();
         transaction = getFragmentManager().beginTransaction();
         TaskFragment fragment = new TaskFragment();
         // fragments.add(fragment);

         // Add new fragment to the transaction and commit
         transaction.add(R.id.box_tasks_layout, fragment);
         // for (TaskFragment f : fragments) {
         // transaction.add(R.id.box_tasks_layout, f);
         // }

         // Commit the transaction
         transaction.commit(); */

        // onResume();
        finish();

        /**
        // Create new fragment
        // FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction = getFragmentManager().beginTransaction();
        TaskFragment fragment = new TaskFragment();
        // fragments.add(fragment);

        // Add new fragment to the transaction and commit
        transaction.add(R.id.box_tasks_layout, fragment);
        // for (TaskFragment f : fragments) {
            // transaction.add(R.id.box_tasks_layout, f);
        // }

        // Commit the transaction
        transaction.commit(); */


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
     * A placeholder fragment containing a simple view.
     */
    public static class TaskFragment extends Fragment {

        public TaskFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_activity_task, container, false);
            return rootView;
        }

    }
}
