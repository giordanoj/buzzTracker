package com.cs2340.team.buzztracker.model;

import java.util.ArrayList;

public class LocationList {
    private static ArrayList<Location> list = new ArrayList<>();

    public static ArrayList<Location> getList() {
        return list;
    }
}
