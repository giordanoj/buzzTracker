package com.cs2340.team.buzztracker.controllers;

import com.cs2340.team.buzztracker.model.Item;
import com.cs2340.team.buzztracker.model.Model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Util {
    public static String generateHash(String inputPass) {
        final String SALT = "thisIsTheSALT";

        String beforePass = SALT + inputPass;
        StringBuilder hash = new StringBuilder();
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(beforePass.getBytes());
            char[] digits = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
            for (int idx = 0; idx < hashedBytes.length; ++idx) {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

        return hash.toString();
    }

    public static Item parseItemString(String itemString) {
        if (itemString == null || itemString.trim().equals("")) {
            return Model.getInstance().theNullItem;
        }

        int id = 0;
        if (itemString.contains("Id:")) {
            int idStartInd = itemString.indexOf("Id:") + 3;
            id = Integer.parseInt(itemString.substring(idStartInd, idStartInd + itemString.substring(idStartInd).indexOf("~")));
        }

        String name = "";
        if (itemString.contains("Name:")) {
            int nameStartInd = itemString.indexOf("Name:") + 5;
            name = itemString.substring(nameStartInd, nameStartInd + itemString.substring(nameStartInd).indexOf("~"));
        }

        String description = "";
        if (itemString.contains("Description:")) {
            int descStartInd = itemString.indexOf("Description:") + 12;
            description = itemString.substring(descStartInd, descStartInd + itemString.substring(descStartInd).indexOf("~"));
        }

        String value = "";
        if (itemString.contains("Value:")) {
            int valStartInd = itemString.indexOf("Value:") + 6;
            value = itemString.substring(valStartInd, valStartInd + itemString.substring(valStartInd).indexOf("~"));
        }

        String category = "";
        if (itemString.contains("Category:")) {
            int catStartInd = itemString.indexOf("Category:") + 9;
            category = itemString.substring(catStartInd, catStartInd + itemString.substring(catStartInd).indexOf("~"));
        }

        String donationTime = "";
        if (itemString.contains("Dontaion Time:")) {
            int donStartInd = itemString.indexOf("Donation Time:") + 14;
            donationTime = itemString.substring(donStartInd, donStartInd + itemString.substring(donStartInd).indexOf("~"));
        }

        String saleTime = "";
        if (itemString.contains("Sale Time:")) {
            int saleStartInd = itemString.indexOf("Sale Time:") + 10;
            saleTime = itemString.substring(saleStartInd, saleStartInd + itemString.substring(saleStartInd).indexOf("~"));
        }

        int enteredBy = 0;
        if (itemString.contains("Entered By:")) {
            int entStartInd = itemString.indexOf("Entered By:") + 11;
            enteredBy = Integer.parseInt(itemString.substring(entStartInd, entStartInd + itemString.substring(entStartInd).indexOf("~")));
        }

        int soldBy = 0;
        if (itemString.contains("Sold By:")) {
            int soldStartInd = itemString.indexOf("Sold By:") + 8;
            soldBy = Integer.parseInt(itemString.substring(soldStartInd, soldStartInd + itemString.substring(soldStartInd).indexOf("~")));
        }

        int origin = 0;
        if (itemString.contains("Origin:")) {
            int originStartInd = itemString.indexOf("Origin:") + 7;
            origin = Integer.parseInt(itemString.substring(originStartInd, originStartInd + itemString.substring(originStartInd).indexOf("~")));
        }

        String picture = "";
        if (itemString.contains("Picture:")) {
            int picStartInd = itemString.indexOf("Picture:") + 8;
            picture = itemString.substring(picStartInd, picStartInd + itemString.substring(picStartInd).indexOf("~"));
        }

        String comments = "";
        if (itemString.contains("Comments:")) {
            int commStartInd = itemString.indexOf("Comments:") + 9;
            comments = itemString.substring(commStartInd, commStartInd + itemString.substring(commStartInd).indexOf("~"));
        }

        int currentLocation = 0;
        if (itemString.contains("Current Location:")) {
            int currStartInd = itemString.indexOf("Current Location:") + 17;
            currentLocation = Integer.parseInt(itemString.substring(currStartInd, currStartInd + itemString.substring(currStartInd).indexOf("~")));
        }

        Item item = new Item(id, category, donationTime, value, picture, comments, name, description, origin, saleTime, enteredBy, soldBy, currentLocation);
        return item;
    }

}
