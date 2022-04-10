package edu.northeastern.cs5500.starterbot.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MapNode {
    private Integer pictureId;

    public MapNode(Integer picId) {
        this.pictureId = picId;
    }

    public static MapNode tsukushiNode = new MapNode(1);
    public static MapNode buzenNode = new MapNode(2);
    public static MapNode hizenNode = new MapNode(3);
    public static MapNode higoNode = new MapNode(4);
    public static MapNode bungoNode = new MapNode(5);
    public static MapNode satsumaNode = new MapNode(6);
    public static MapNode osumiNode = new MapNode(7);
    public static MapNode hyugaNode = new MapNode(8);
}
