package edu.northeastern.cs5500.starterbot.model;

import javax.annotation.Nonnull;
import lombok.Data;

@Data
public class MapNode {
    private final int location;
    @Nonnull private final String imageUrl;
    @Nonnull private final String name;

    public static final MapNode TSUKUSHI =
            new MapNode(
                    1,
                    "https://media.discordapp.net/attachments/955539523620700170/957423623575072908/Tsukushi.png",
                    "Tsukushi");
    public static final MapNode BUZEN =
            new MapNode(
                    2,
                    "https://media.discordapp.net/attachments/955539523620700170/957423623134662757/Buzen.png",
                    "Buzen");
    public static final MapNode HIZEN =
            new MapNode(
                    3,
                    "https://media.discordapp.net/attachments/955539523620700170/957423624057389126/Hizen.png",
                    "Hizen");
    public static final MapNode HIGO =
            new MapNode(
                    4,
                    "https://media.discordapp.net/attachments/955539523620700170/957423622664884284/Higo.png",
                    "Higo");
    public static final MapNode BUNGO =
            new MapNode(
                    5,
                    "https://media.discordapp.net/attachments/955539523620700170/957423622882996245/Bungo.png",
                    "Bungo");
    public static final MapNode SATSUMA =
            new MapNode(
                    6,
                    "https://media.discordapp.net/attachments/955539523620700170/957423622136414268/Satusma.png",
                    "Satsuma");
    public static final MapNode OSUMI =
            new MapNode(
                    7,
                    "https://media.discordapp.net/attachments/955539523620700170/957423621859586069/Osumi.png",
                    "Osumi");
    public static final MapNode HYUGA =
            new MapNode(
                    8,
                    "https://media.discordapp.net/attachments/955539523620700170/957423622400663562/Hyuga.png",
                    "Hyuga");

    public static MapNode getNodeById(Integer nodeId) {
        switch (nodeId) {
            case 1:
                return TSUKUSHI;
            case 2:
                return BUZEN;
            case 3:
                return HIZEN;
            case 4:
                return HIGO;
            case 5:
                return BUNGO;
            case 6:
                return SATSUMA;
            case 7:
                return OSUMI;
            case 8:
                return HYUGA;
            default:
                throw new IllegalArgumentException(String.format("Unknown MapNode %d", nodeId));
        }
    }
}
