/** This class is used to store the information of the players */
package edu.northeastern.cs5500.starterbot.model;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeClassFromJacocoGeneratedReport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@ExcludeClassFromJacocoGeneratedReport
public class PokeMap {
    public static final Integer STARTING_LOCATION = 1;
    private HashMap<MapNode, ArrayList<MapNode>> adjacentNodes;

    public List<MapNode> getAdjacentNodes(MapNode node) {
        return adjacentNodes.get(node);
    }

    @Inject
    public PokeMap() {
        HashMap<MapNode, ArrayList<MapNode>> pokeMap = new HashMap<>();
        ArrayList<MapNode> tsukushiVal =
                new ArrayList<>(Arrays.asList(MapNode.BUZEN, MapNode.HIZEN, MapNode.HIGO));
        ArrayList<MapNode> buzenVal =
                new ArrayList<>(Arrays.asList(MapNode.TSUKUSHI, MapNode.BUNGO));
        ArrayList<MapNode> hizenVal =
                new ArrayList<>(Arrays.asList(MapNode.TSUKUSHI, MapNode.HIGO));
        ArrayList<MapNode> higoVal =
                new ArrayList<>(
                        Arrays.asList(
                                MapNode.TSUKUSHI, MapNode.HIZEN, MapNode.BUNGO, MapNode.SATSUMA));
        ArrayList<MapNode> bungoVal =
                new ArrayList<>(
                        Arrays.asList(MapNode.BUZEN, MapNode.HIGO, MapNode.HYUGA, MapNode.SATSUMA));
        ArrayList<MapNode> satsumaVal =
                new ArrayList<>(Arrays.asList(MapNode.HIGO, MapNode.BUNGO, MapNode.OSUMI));
        ArrayList<MapNode> osumiVal =
                new ArrayList<>(Arrays.asList(MapNode.SATSUMA, MapNode.HYUGA));
        ArrayList<MapNode> hyugaVal = new ArrayList<>(Arrays.asList(MapNode.BUNGO, MapNode.OSUMI));
        pokeMap.put(MapNode.TSUKUSHI, tsukushiVal);
        pokeMap.put(MapNode.BUZEN, buzenVal);
        pokeMap.put(MapNode.HIZEN, hizenVal);
        pokeMap.put(MapNode.HIGO, higoVal);
        pokeMap.put(MapNode.BUNGO, bungoVal);
        pokeMap.put(MapNode.SATSUMA, satsumaVal);
        pokeMap.put(MapNode.OSUMI, osumiVal);
        pokeMap.put(MapNode.HYUGA, hyugaVal);

        this.adjacentNodes = pokeMap;
    }
}
