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

            int idStartInd = itemString.indexOf("Id:") + 3;
            int id = Integer.parseInt(itemString.substring(idStartInd, idStartInd + itemString.substring(idStartInd).indexOf("~")));

            int nameStartInd = itemString.indexOf("Name:") + 5;
            String name = itemString.substring(nameStartInd, nameStartInd + itemString.substring(nameStartInd).indexOf("~"));

            int descStartInd = itemString.indexOf("Description:") + 12;
            String description = itemString.substring(descStartInd, descStartInd + itemString.substring(descStartInd).indexOf("~"));

            int valStartInd = itemString.indexOf("Value:") + 6;
            String value = itemString.substring(valStartInd, valStartInd + itemString.substring(valStartInd).indexOf("~"));

            int catStartInd = itemString.indexOf("Category:") + 9;
            String category = itemString.substring(catStartInd, catStartInd + itemString.substring(catStartInd).indexOf("~"));

            int donStartInd = itemString.indexOf("Donation Time:") + 14;
            String donationTime = itemString.substring(donStartInd, donStartInd + itemString.substring(donStartInd).indexOf("~"));

            int saleStartInd = itemString.indexOf("Sale Time:") + 10;
            String saleTime = itemString.substring(saleStartInd, saleStartInd + itemString.substring(saleStartInd).indexOf("~"));

            int entStartInd = itemString.indexOf("Entered By:") + 11;
            int enteredBy = Integer.parseInt(itemString.substring(entStartInd, entStartInd + itemString.substring(entStartInd).indexOf("~")));

            int soldStartInd = itemString.indexOf("Sold By:") + 8;
            int soldBy = Integer.parseInt(itemString.substring(soldStartInd, soldStartInd + itemString.substring(soldStartInd).indexOf("~")));

            int originStartInd = itemString.indexOf("Origin:") + 7;
            int origin = Integer.parseInt(itemString.substring(originStartInd, originStartInd + itemString.substring(originStartInd).indexOf("~")));

            int picStartInd = itemString.indexOf("Picture:") + 8;
            String picture = itemString.substring(picStartInd, picStartInd + itemString.substring(picStartInd).indexOf("~"));

            int commStartInd = itemString.indexOf("Comments:") + 9;
            String comments = itemString.substring(commStartInd, commStartInd + itemString.substring(commStartInd).indexOf("~"));

            int currStartInd = itemString.indexOf("Current Location:") + 17;
            int currentLocation = Integer.parseInt(itemString.substring(currStartInd, currStartInd + itemString.substring(currStartInd).indexOf("~")));

            Item item = new Item(id, category, donationTime, value, picture, comments, name, description, origin, saleTime, enteredBy, soldBy, currentLocation);
            return item;
    }

}
