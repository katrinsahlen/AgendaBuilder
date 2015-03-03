package iprog.group7.agendabuilder.android;

import android.app.Application;

import iprog.group7.agendabuilder.model.AgendaModel;

public class AgendaBuilderApplication extends Application {

    private AgendaModel model = new AgendaModel();

    public AgendaModel getModel() {
        return model;
    }

    public void setModel(AgendaModel model) {
        this.model = model;
    }

}