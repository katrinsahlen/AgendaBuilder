package iprog.group7.agendabuilder.android;

import android.app.Activity;

import android.content.Intent;

import android.support.v7.app.ActionBarActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import iprog.group7.agendabuilder.android.R;
import iprog.group7.agendabuilder.android.view.AddTaskView;
import iprog.group7.agendabuilder.android.view.AddTaskViewController;
import iprog.group7.agendabuilder.model.AgendaModel;

/**
 * The activity controlling view AddTaskView
 */
public class AddTaskActivity extends Activity {

    AddTaskView mainView;
    public String source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get source msg (new or edit)
        Intent intent = getIntent();
        source = intent.getStringExtra(MainActivity.SOURCE);

        //set model & view
        AgendaModel model = ((AgendaBuilderApplication) this.getApplication()).getModel();
        mainView = new AddTaskView(findViewById(R.id.activity_add_task_id), model);
        //dump source into view
        mainView.setSource(source);
        //set controller
        AddTaskViewController clickController = new AddTaskViewController(model, mainView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_task, menu);
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
    public void returnToMain(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
