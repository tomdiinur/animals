package com.example.tom.tryveg;

import com.example.tom.tryveg.classes.Pet;
import com.example.tom.tryveg.classes.FacebookFriend;
import com.example.tom.tryveg.classes.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 29-Jul-16.
 */
public class Globals {
    public static User currentUser = new User("05/06/2016", "תם דינור", 12);

    public static ArrayList<Pet> pets = new ArrayList<Pet>() {{
        add(new Pet("ant", R.drawable.ant, R.raw.ants, 0));
        add(new Pet("fish", R.drawable.fish, R.raw.fish, 2));
        add(new Pet("crab", R.drawable.crab, 5));
        add(new Pet("snail", R.drawable.snail, 9));
        add(new Pet("mouse", R.drawable.mouse, R.raw.mouse, 15));
        add(new Pet("turtle", R.drawable.turtle, 20));
        add(new Pet("parrot", R.drawable.parrot, R.raw.parrot, 27));
        add(new Pet("rabbit", R.drawable.rabbit, R.raw.rabbit, 36));
        add(new Pet("cat", R.drawable.cat, R.raw.cat,41));
        add(new Pet("penguin", R.drawable.penguin, R.raw.penguin, 50));
        add(new Pet("dog", R.drawable.dog, R.raw.dog,60));
        add(new Pet("flamingo", R.drawable.flamingo,R.raw.flamingo, 70));
        add(new Pet("deer", R.drawable.deer, R.raw.deer, 78));
        add(new Pet("dolphin", R.drawable.dolphin, R.raw.dolphin, 98));
        add(new Pet("sheep", R.drawable.sheep, R.raw.sheep, 112));
        add(new Pet("horse", R.drawable.horse, R.raw.horse, 124));
        add(new Pet("zebra", R.drawable.zebra, R.raw.zebra, 142));
        add(new Pet("kangaroo", R.drawable.kangaroo, R.raw.kangaroo, 180));
        add(new Pet("cow", R.drawable.cow,  R.raw.cow, 201));
        add(new Pet("giraffe", R.drawable.giraffe, R.raw.giraffe, 224));
        add(new Pet("panda,", R.drawable.panda, R.raw.panda, 251));
        add(new Pet("octupus", R.drawable.octupus, 278));
        add(new Pet("rhyno", R.drawable.rhyno, R.raw.rhyno, 305));
        add(new Pet("elephant", R.drawable.elephant, R.raw.elephant, 330));
        add(new Pet("hippo", R.drawable.hippo, R.raw.hippo, 365));
    }};

    public static ArrayList<FacebookFriend> friends = new ArrayList<FacebookFriend>() {{
        // Vegan
        add(new FacebookFriend("Dor Redlich",
                "https://scontent.ftlv1-1.fna.fbcdn.net/v/t1.0-9/1923767_1152974981380153_8989876837042782409_n.jpg?oh=760a1f9a8ce55085d21d05d09743ef92&oe=5825C33E",
                true, new User("22/07/2015", "Dor Redlich", 1)));

        add(new FacebookFriend("Shahar Peiser",
                "https://scontent.ftlv1-1.fna.fbcdn.net/v/t1.0-9/11429962_10206976747201556_7137513052587253960_n.jpg?oh=b40b2911c478fe5f9cf0c9d97f4d927b&oe=581B4AE1",
                true, new User("01/08/2015", "Shahar Peiser", 1)));

        add(new FacebookFriend("Lidor Ben-Itzhak",
                "https://scontent.fhfa1-1.fna.fbcdn.net/v/t1.0-9/13494908_10201868505830971_1435705703325592049_n.jpg?oh=138e35bd973c2af7df9938049838a0bd&oe=582B8C31",
                true, new User("15/09/2015", "Lidor Ben-Itzhak", 1)));

        add(new FacebookFriend("Omer Drukman",
                "https://scontent.ftlv1-1.fna.fbcdn.net/v/t1.0-9/11181700_10204343113089317_5681925959977545992_n.jpg?oh=90c0882635984a72e7adbbf9611fe550&oe=58274EA0",
                true, new User("10/10/2015", "Omer Drukman", 1)));

        add(new FacebookFriend("Gal Boker",
                "https://scontent.ftlv1-1.fna.fbcdn.net/v/t1.0-9/11390371_1136231783058668_3724691348392538681_n.jpg?oh=cab7599ce813ddcb50d9f03ba9f6fb1c&oe=58307B35",
                true, new User("11/11/2015", "Gal Boker", 1)));

        add(new FacebookFriend("Vicky Vatury",
                "https://scontent.ftlv1-1.fna.fbcdn.net/v/t1.0-9/13680813_1192080160813587_8565828456088942021_n.jpg?oh=adc5fc08a3a0bdc49c49642c8ce9be04&oe=58192721",
                true, new User("02/01/2016", "Vicky Vatury", 1)));

        add(new FacebookFriend("Omri Mintz",
                "https://scontent.ftlv1-1.fna.fbcdn.net/v/t1.0-9/1544530_10201720264663456_1727315112_n.jpg?oh=9036b2b75069977433f5c6423b8d7d1e&oe=582F5CDB",
                true, new User("23/01/2016", "Omri Mintz", 1)));

        add(new FacebookFriend("Ron Redlich",
                "https://scontent.ftlv1-1.fna.fbcdn.net/v/t1.0-9/1150209_1214101875271179_8993024924466993795_n.jpg?oh=aa4b0f36c79822ac4eb5b18eb44e412a&oe=58190F55",
                true, new User("03/02/2016", "Ron Redlich", 1)));

        add(new FacebookFriend("Artyom Ladigin",
                "https://scontent.fhfa1-1.fna.fbcdn.net/v/t1.0-9/1453299_10203756165977275_5180031151430780221_n.jpg?oh=8439473cb68f623507ad02ecabf094ee&oe=582EAEE6",
                true, new User("02/03/2016", "Artyom Ladigin", 1)));

        add(new FacebookFriend("Nadav Bahalul",
                "https://scontent.fhfa1-1.fna.fbcdn.net/v/l/t1.0-1/c0.68.200.200/1966938_3912904718349_2130866646_n.jpg?oh=3d4d228b1fca962434c451368fc0a2ec&oe=58203F18",
                true, new User("26/03/2016", "Nadav Bahalul", 1)));

        add(new FacebookFriend("Guy Avrahami",
                "https://scontent.ftlv1-1.fna.fbcdn.net/v/t1.0-9/12011156_10206279591007265_6368813670023858880_n.jpg?oh=3b81afd2c0f1b1d6f1fedf18f08791a4&oe=58112610",
                true, new User("01/04/2016", "Guy Avrahami", 1)));

        add(new FacebookFriend("Inbar Amir",
                "https://scontent.ftlv1-1.fna.fbcdn.net/v/t1.0-9/12744016_10154071244388738_5712503374722469156_n.jpg?oh=c2cdb8a4a830f4ad0b679ab45a2b53fb&oe=5829579F",
                true, new User("22/04/2016", "Inbar Amir", 1)));

        add(new FacebookFriend("Or Mishli",
                "https://scontent.ftlv1-1.fna.fbcdn.net/v/t1.0-9/1482789_10202134737622571_884755055_n.jpg?oh=c492f8e9acbe36494de1de3627a696c5&oe=582ED734",
                true, new User("19/05/2016", "Or Mishli", 1)));

        add(new FacebookFriend("Keren Faigon",
                "https://scontent.ftlv1-1.fna.fbcdn.net/v/l/t1.0-9/13654411_10207205595403081_562065844642957220_n.jpg?oh=e2edce3fbe1d211a9e0e85c0a4496ee3&oe=58320CF2",
                true, new User("26/05/2016", "Keren Faigon", 1)));

        add(new FacebookFriend("Me",
                "https://scontent.fhfa1-1.fna.fbcdn.net/v/t1.0-9/13082710_10208891514186559_6323955819174045392_n.jpg?oh=563185f7e1ec54a3d9131f0c78f02e6c&oe=58338DBD",
                true, Globals.currentUser));

        add(new FacebookFriend("Noam maynfler",
                "https://scontent.fhfa1-1.fna.fbcdn.net/v/t1.0-9/12144767_10206642206110248_1205691425768904859_n.jpg?oh=395a93392c19ceeb11aab7b38a2de069&oe=585EDD42",
                true, new User("16/07/2016", "Noam maynfler", 1)));

        add(new FacebookFriend("Nir Hayon",
                "https://scontent.ftlv1-1.fna.fbcdn.net/v/t1.0-9/10983214_1625326094349277_3210829405126680928_n.jpg?oh=57e4e7e067e8383ad0bd8b4c0f34dd61&oe=581E4F79",
                true, new User("24/07/2016", "Nir Hayon", 1)));

        // Carnivores
        add(new FacebookFriend("Ruth Ashraf",
                "https://scontent.fhfa1-1.fna.fbcdn.net/v/t1.0-9/10553384_10204486755119063_1283892030966111528_n.jpg?oh=c06eb2188d29ab000a91a120c9ba60d8&oe=5818A4DF"
                , false));
        add(new FacebookFriend("Yuval Mund",
                "https://scontent.fhfa1-1.fna.fbcdn.net/v/t1.0-9/12391133_10208515369147388_7583734240589702725_n.jpg?oh=f236059a08374259f771cc80f7f55f2e&oe=582071C1",
                false));
        add(new FacebookFriend("Bar Molot",
                "https://scontent.fhfa1-1.fna.fbcdn.net/v/t1.0-9/12369023_10207930589643700_1749666221649677747_n.jpg?oh=c8f67b6ee8ec0c63b2657d0266a04c90&oe=582DA551",
                false));
        add(new FacebookFriend("Zur Doron",
                "https://scontent.fhfa1-1.fna.fbcdn.net/v/t1.0-9/12193364_1071397119536971_4742944198413404622_n.jpg?oh=853236ef6868d353e4e76580f8df03fb&oe=582ED685",
                false
                ));


        add(new FacebookFriend("Ido Goldenberg",
                "https://scontent.ftlv1-1.fna.fbcdn.net/v/t1.0-9/12004718_10203307610169836_7228702124746396813_n.jpg?oh=68286313ca23e6df914ebef9dcd55410&oe=581BF923",
                false
        ));

    }};


}
