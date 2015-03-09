package iprog.group7.agendabuilder.android.view;

import android.content.ClipData;
import android.view.View;

import iprog.group7.agendabuilder.model.AgendaModel;

/**
 * Controller for drag in the main view: DayView and TaskView
 */
public class MainViewDragController implements View.OnLongClickListener {

    AgendaModel model;
    MainViewDragEventListener dragListen;
    DayView dayView;
    TaskView taskView;

    public MainViewDragController(AgendaModel model, DayView dayView, TaskView taskView) {

        this.model = model;
        this.dayView = dayView;
        this.taskView = taskView;

        // dragListen = new MainViewDragEventListener();

        /** taskView.taskBox.setOnLongClickListener(this);
        taskView.taskBox.setOnDragListener(dragListen); */

    }

    @Override
    public boolean onLongClick(View v) {

        /**
        if (v == taskView.taskBox) {
            ClipData clipData = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(taskView.taskBox);
            taskView.taskBox.startDrag(clipData, shadowBuilder, taskView.taskBox, 0);
            taskView.taskBox.setVisibility(View.INVISIBLE);
        } */

        return true;

    }

}