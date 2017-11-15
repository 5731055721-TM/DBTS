package com.wireless.g5.dbtsfragver.content;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StationContent {
    public static final ArrayList<Station> STATIONS_SUKHUMVIT = new ArrayList<Station>();
    public static final ArrayList<Station> STATIONS_SILOM = new ArrayList<Station>();

    public static final String TER_SUKHUMVIT[] = {"หมอชิต","สำโรง"};
    public static final String TER_SILOM[] = {"สนามกีฬาแห่งชาติ","บางหว้า"};

    public static final Map<String, Station> STATION_MAP_SUKHUMVIT = new HashMap<String, Station>();
    public static final Map<String, Station> STATION_MAP_SILOM = new HashMap<String, Station>();

    static {
        addSukhumvit(new Station("1","หมอชิต","N8",false));
        addSukhumvit(new Station("2","สะพานควาย","N7",false));
        addSukhumvit(new Station("3","อารีย์","N5",false));
        addSukhumvit(new Station("4","สนามเป้า","N4",false));
        addSukhumvit(new Station("5","อนุสาวรีย์ชัยสมรภูมิ","N3",false));
        addSukhumvit(new Station("6","พญาไท","N2",false));
        addSukhumvit(new Station("7","ราชเทวี","N1",false));
        addSukhumvit(new Station("8","สยาม","CEN",false));
        addSukhumvit(new Station("9","ชิดลม","E1",false));
        addSukhumvit(new Station("10","เพลินจิต","E2",false));
        addSukhumvit(new Station("11","นานา","E3",false));
        addSukhumvit(new Station("12","อโศก","E4",false));
        addSukhumvit(new Station("13","พร้อมพงษ์","E5",false));
        addSukhumvit(new Station("14","ทองหล่อ","E6",false));
        addSukhumvit(new Station("15","เอกมัย","E7",false));
        addSukhumvit(new Station("16","พระโขนง","E8",false));
        addSukhumvit(new Station("17","อ่อนนุช","E9",false));
        addSukhumvit(new Station("18","บางจาก","E10",false));
        addSukhumvit(new Station("19","ปุณณวิถี","E11",false));
        addSukhumvit(new Station("20","อุดมสุข","E12",false));
        addSukhumvit(new Station("21","บางนา","E13",false));
        addSukhumvit(new Station("22","แบริ่ง","E14",false));
        addSukhumvit(new Station("23","สำโรง","E15",false));

        addSilom(new Station("1","สนามกีฬาแห่งชาติ","W1",false));
        addSilom(new Station("2","สยาม","CEN",false));
        addSilom(new Station("3","ราชดำริ","S1",false));
        addSilom(new Station("4","ศาลาแดง","S2",false));
        addSilom(new Station("5","ช่องนนทรี","S3",false));
        addSilom(new Station("6","สุรศักดิ์ิ","S5",false));
        addSilom(new Station("7","สะพานตากสิน","S6",false));
        addSilom(new Station("8","กรุงธนบุรี","S7",false));
        addSilom(new Station("9","วงเวียนใหญ่","S8",false));
        addSilom(new Station("10","โพธิ์นิมิตร","S9",false));
        addSilom(new Station("11","ตลาดพลู","S10",false));
        addSilom(new Station("12","วุฒากาศ","S11",false));
        addSilom(new Station("13","บางหว้า","S12",false));
    }

    private static void addSukhumvit(Station item) {
        STATIONS_SUKHUMVIT.add(item);
        STATION_MAP_SUKHUMVIT.put(item.details, item);
    }
    private static void addSilom(Station item) {
        STATIONS_SILOM.add(item);
        STATION_MAP_SILOM.put(item.details, item);
    }

    public static class Station {
        public final String id;
        public final String content;
        public final String details;
        public boolean fav;

        public Station (String id, String content, String details, boolean fav) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.fav = fav;
        }
    }
}
