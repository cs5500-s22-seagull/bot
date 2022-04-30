package edu.northeastern.cs5500.starterbot.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MapNode {
    private Integer pictureId;
    private String pictureAddress;
    private String locName;

    public MapNode(Integer picId, String picAdd, String locName) {
        this.pictureId = picId;
        this.pictureAddress = picAdd;
        this.locName = locName;
    }

    public static MapNode tsukushiNode = new MapNode(1, "https://media.discordapp.net/attachments/955539523620700170/957423623575072908/Tsukushi.png", "Tsukushi");
    public static MapNode buzenNode = new MapNode(2, "https://media.discordapp.net/attachments/955539523620700170/957423623134662757/Buzen.png", "Buzen");
    public static MapNode hizenNode = new MapNode(3, "https://media.discordapp.net/attachments/955539523620700170/957423624057389126/Hizen.png", "Hizen");
    public static MapNode higoNode = new MapNode(4, "https://media.discordapp.net/attachments/955539523620700170/957423622664884284/Higo.png", "Higo");
    public static MapNode bungoNode = new MapNode(5, "https://media.discordapp.net/attachments/955539523620700170/957423622882996245/Bungo.png", "Bungo");
    public static MapNode satsumaNode = new MapNode(6, "https://media.discordapp.net/attachments/955539523620700170/957423622136414268/Satusma.png", "Satsuma");
    public static MapNode osumiNode = new MapNode(7, "https://media.discordapp.net/attachments/955539523620700170/957423621859586069/Osumi.png", "Osumi");
    public static MapNode hyugaNode = new MapNode(8, "https://media.discordapp.net/attachments/955539523620700170/957423622400663562/Hyuga.png", "Hyuga");
}
