package iprog.group7.agendabuilder.android;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import iprog.group7.agendabuilder.android.view.AddDayView;
import iprog.group7.agendabuilder.android.view.DayView;
import iprog.group7.agendabuilder.android.view.TaskView;

/**
 * The activity controlling views AddDayView, DayView and TaskView
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate views
        // AddDayView addDayView = new AddDayView(findViewById(R.id.page_main_view_id));
        DayView dayView = new DayView(findViewById(R.id.page_main_view_id));
        TaskView taskView = new TaskView(findViewById(R.id.page_main_view_id));

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
