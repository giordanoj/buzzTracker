package com.cs2340.team.buzztracker.model;



import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * a class to contain utility functions that have nowhere else to go
 */
public class Util {
    /**
     *
     * @param inputPass the password to hash
     * @return the hash of the password
     */
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

    /**
     *
     * @param itemString the string from the database containing item information
     * @return the item object of the information
     */
    public static Item parseItemString(String itemString) {
        if (itemString == null || "".equals(itemString.trim())) {
            return Model.getInstance().theNullItem;
        }

        boolean nullItem = true;

        int id = 0;
        if (itemString.contains("Id:")) {
            int idStartInd = itemString.indexOf("Id:") + 3;
            if (itemString.substring(idStartInd).contains("~")) {
                id = Integer.parseInt(itemString.substring(idStartInd, idStartInd +
                        itemString.substring(idStartInd).indexOf("~")));
            } else {
                id = Integer.parseInt(itemString.substring(idStartInd, idStartInd +
                        itemString.substring(idStartInd).indexOf("|")));
            }
            nullItem = false;
        }

        String name = "";
        if (itemString.contains("Name:")) {
            int nameStartInd = itemString.indexOf("Name:") + 5;
            if (itemString.substring(nameStartInd).contains("~")) {
                name = itemString.substring(nameStartInd, nameStartInd +
                        itemString.substring(nameStartInd).indexOf("~"));
            } else {
                name = itemString.substring(nameStartInd, nameStartInd +
                        itemString.substring(nameStartInd).indexOf("|"));
            }
            nullItem = false;
        }

        String description = "";
        if (itemString.contains("Description:")) {
            int descStartInd = itemString.indexOf("Description:") + 12;
            if (itemString.substring(descStartInd).contains("~")) {
                description = itemString.substring(descStartInd, descStartInd +
                        itemString.substring(descStartInd).indexOf("~"));
            } else {
                description = itemString.substring(descStartInd, descStartInd +
                        itemString.substring(descStartInd).indexOf("|"));
            }
            nullItem = false;
        }

        String value = "";
        if (itemString.contains("Value:")) {
            int valStartInd = itemString.indexOf("Value:") + 6;
            if (itemString.substring(valStartInd).contains("~")) {
                value = itemString.substring(valStartInd, valStartInd +
                        itemString.substring(valStartInd).indexOf("~"));
            } else {
                value = itemString.substring(valStartInd, valStartInd +
                        itemString.substring(valStartInd).indexOf("|"));
            }
            nullItem = false;
        }

        String category = "";
        if (itemString.contains("Category:")) {
            int catStartInd = itemString.indexOf("Category:") + 9;
            if (itemString.substring(catStartInd).contains("~")) {
                category = itemString.substring(catStartInd, catStartInd +
                        itemString.substring(catStartInd).indexOf("~"));
            } else {
                category = itemString.substring(catStartInd, catStartInd +
                        itemString.substring(catStartInd).indexOf("|"));
            }
            nullItem = false;
        }

        String donationTime = "";
        if (itemString.contains("Donation Time:")) {
            int donStartInd = itemString.indexOf("Donation Time:") + 14;
            if (itemString.substring(donStartInd).contains("~")) {
                donationTime = itemString.substring(donStartInd, donStartInd +
                        itemString.substring(donStartInd).indexOf("~"));
            } else {
                donationTime = itemString.substring(donStartInd, donStartInd +
                        itemString.substring(donStartInd).indexOf("|"));
            }
            nullItem = false;
        }

        String saleTime = "";
        if (itemString.contains("Sale Time:")) {
            int saleStartInd = itemString.indexOf("Sale Time:") + 10;
            if (itemString.substring(saleStartInd).contains("~")) {
                saleTime = itemString.substring(saleStartInd, saleStartInd +
                        itemString.substring(saleStartInd).indexOf("~"));
            } else {
                saleTime = itemString.substring(saleStartInd, saleStartInd +
                        itemString.substring(saleStartInd).indexOf("|"));
            }
            nullItem = false;
        }

        int enteredBy = 0;
        if (itemString.contains("Entered By:")) {
            int entStartInd = itemString.indexOf("Entered By:") + 11;
            if (itemString.substring(entStartInd).contains("~")) {
                enteredBy = Integer.parseInt(itemString.substring(entStartInd,
                        entStartInd + itemString.substring(entStartInd).indexOf("~")));
            } else {
                enteredBy = Integer.parseInt(itemString.substring(entStartInd,
                        entStartInd + itemString.substring(entStartInd).indexOf("|")));
            }
            nullItem = false;
        }

        int soldBy = 0;
        if (itemString.contains("Sold By:")) {
            int soldStartInd = itemString.indexOf("Sold By:") + 8;
            if (itemString.substring(soldStartInd).contains("~")) {
                soldBy = Integer.parseInt(itemString.substring(soldStartInd,
                        soldStartInd + itemString.substring(soldStartInd).indexOf("~")));
            } else {
                soldBy = Integer.parseInt(itemString.substring(soldStartInd,
                        soldStartInd + itemString.substring(soldStartInd).indexOf("|")));
            }
            nullItem = false;
        }

        int origin = 0;
        if (itemString.contains("Origin:")) {
            int originStartInd = itemString.indexOf("Origin:") + 7;
            if (itemString.substring(originStartInd).contains("~")) {
                origin = Integer.parseInt(itemString.substring(originStartInd,
                        originStartInd + itemString.substring(originStartInd).indexOf("~")));
            } else {
                origin = Integer.parseInt(itemString.substring(originStartInd,
                        originStartInd + itemString.substring(originStartInd).indexOf("|")));
            }
            nullItem = false;
        }

        String picture = "";
        if (itemString.contains("Picture:")) {
            int picStartInd = itemString.indexOf("Picture:") + 8;
            if (itemString.substring(picStartInd).contains("~")) {
                picture = itemString.substring(picStartInd, picStartInd +
                        itemString.substring(picStartInd).indexOf("~"));
            } else {
                picture = itemString.substring(picStartInd, picStartInd +
                        itemString.substring(picStartInd).indexOf("|"));
            }
            nullItem = false;
        }

        String comments = "";
        if (itemString.contains("Comments:")) {
            int commStartInd = itemString.indexOf("Comments:") + 9;
            if (itemString.substring(commStartInd).contains("~")) {
                comments = itemString.substring(commStartInd, commStartInd +
                        itemString.substring(commStartInd).indexOf("~"));
            } else {
                comments = itemString.substring(commStartInd, commStartInd +
                        itemString.substring(commStartInd).indexOf("|"));
            }
            nullItem = false;
        }

        int currentLocation = 0;
        if (itemString.contains("Current Location:")) {
            int currStartInd = itemString.indexOf("Current Location:") + 17;
            if (itemString.substring(currStartInd).contains("~")) {
                currentLocation = Integer.parseInt(itemString.substring(currStartInd,
                        currStartInd + itemString.substring(currStartInd).indexOf("~")));
            } else {
                currentLocation = Integer.parseInt(itemString.substring(currStartInd,
                        currStartInd + itemString.substring(currStartInd).indexOf("|")));
            }
            nullItem = false;
        }

        if (nullItem) {
            return Model.getInstance().theNullItem;
        }
        Item item = new Item(id, category, donationTime, value, picture, comments,
                name, description, origin, saleTime, enteredBy, soldBy, currentLocation);
        return item;
    }

}
