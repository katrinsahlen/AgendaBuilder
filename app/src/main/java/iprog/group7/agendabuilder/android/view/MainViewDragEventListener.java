package iprog.group7.agendabuilder.android.view;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.view.DragEvent;
import android.view.View;

public class MainViewDragEventListener implements View.OnDragListener {

    @Override
    public boolean onDrag(View v, DragEvent event) {

        int action = event.getAction();

        switch(action) {

            case DragEvent.ACTION_DRAG_STARTED:
                if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    //v.setColorFilter(Color.BLUE);
                    v.invalidate();
                    return true;
                }
                return false;
            case DragEvent.ACTION_DRAG_ENTERED:
                // v.setColorFilter(Color.GREEN);
                v.invalidate();
                return true;
            case DragEvent.ACTION_DRAG_LOCATION:
                return true;
            case DragEvent.ACTION_DRAG_EXITED:
                // v.setColorFilter(Color.BLUE);
                v.invalidate();
                return true;
            case DragEvent.ACTION_DROP:
                ClipData.Item item = event.getClipData().getItemAt(0);
                // dragData = item.getText();
                // Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_LONG);
                // v.clearColorFilter();
                v.invalidate();
                return true;
            case DragEvent.ACTION_DRAG_ENDED:
                // v.clearColorFilter();
                v.invalidate();
                if (event.getResult()) {
                    // Toast.makeText(this, "The drop was handled.", Toast.LENGTH_LONG);
                } else {
                    // Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_LONG);
                }
                return true;

            // An unknown action type was received.
            default:
                // Log.e("DragDrop Example","Unknown action type received by OnDragListener.");
                break;
        }

        return false;
    }

}
