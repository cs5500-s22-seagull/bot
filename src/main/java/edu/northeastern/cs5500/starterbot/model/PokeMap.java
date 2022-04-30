/** This class is used to store the information of the players */
package edu.northeastern.cs5500.starterbot.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.inject.Singleton;


import lombok.Data;
import org.bson.types.ObjectId;


@Data
//@NoArgsConstructor
@Singleton
public class PokeMap implements Model {
    private ObjectId id;
    private HashMap<MapNode, ArrayList<MapNode>> adjNodes = new HashMap<>();

    public PokeMap(){
        HashMap<MapNode, ArrayList<MapNode>> pokeMap = new HashMap<>();
        ArrayList<MapNode> tsukushiVal = new ArrayList<>(Arrays.asList(MapNode.buzenNode, MapNode.hizenNode, MapNode.higoNode));
        ArrayList<MapNode> buzenVal = new ArrayList<>(Arrays.asList(MapNode.tsukushiNode, MapNode.bungoNode));
        ArrayList<MapNode> hizenVal = new ArrayList<>(Arrays.asList(MapNode.tsukushiNode, MapNode.higoNode));
        ArrayList<MapNode> higoVal = new ArrayList<>(Arrays.asList(MapNode.tsukushiNode, MapNode.hizenNode, MapNode.bungoNode, MapNode.satsumaNode));
        ArrayList<MapNode> bungoVal = new ArrayList<>(Arrays.asList(MapNode.buzenNode, MapNode.higoNode, MapNode.hyugaNode, MapNode.satsumaNode));
        ArrayList<MapNode> satsumaVal = new ArrayList<>(Arrays.asList(MapNode.higoNode, MapNode.bungoNode, MapNode.osumiNode));
        ArrayList<MapNode> osumiVal = new ArrayList<>(Arrays.asList(MapNode.satsumaNode, MapNode.hyugaNode));
        ArrayList<MapNode> hyugaVal = new ArrayList<>(Arrays.asList(MapNode.bungoNode, MapNode.osumiNode));
        pokeMap.put(MapNode.tsukushiNode, tsukushiVal);
        pokeMap.put(MapNode.buzenNode, buzenVal);
        pokeMap.put(MapNode.hizenNode, hizenVal);
        pokeMap.put(MapNode.higoNode, higoVal);
        pokeMap.put(MapNode.bungoNode, bungoVal);
        pokeMap.put(MapNode.satsumaNode, satsumaVal);
        pokeMap.put(MapNode.osumiNode, osumiVal);
        pokeMap.put(MapNode.hyugaNode, hyugaVal);
        
        this.adjNodes = pokeMap;
    }
    
    // should be load while initializing
    public static PokeMap pokeMap = new PokeMap();
}
