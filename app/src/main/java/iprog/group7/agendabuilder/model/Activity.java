package iprog.group7.agendabuilder.model;

import java.util.Observable;

/**
 * Content copied from provided Java model, original package se.kth.csc.iprog.agendabuilder.model
 */
public class Activity extends Observable {

    // The possible types of the activity
    public static final int PRESENTATION = 1;
    public static final int GROUP_WORK = 2;
    public static final int DISCUSSION = 3;
    public static final int BREAK = 4;


    String name;
    String description;

    int length; //in minutes
    int type;


    public Activity(String name, String description, int length, int type) {
        this.name = name;
        this.description = description;
        this.length = length;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setChanged();
        notifyObservers("NameChanged");
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
        setChanged();
        notifyObservers("DescriptionChanged");
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
        setChanged();
        notifyObservers("LengthChanged");
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
        setChanged();
        notifyObservers("TypeChanged");
    }

}
