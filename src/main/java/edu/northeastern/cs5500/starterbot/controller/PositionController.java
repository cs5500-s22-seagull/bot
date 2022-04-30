package edu.northeastern.cs5500.starterbot.controller;

import edu.northeastern.cs5500.starterbot.model.*;
import edu.northeastern.cs5500.starterbot.repository.*;
import edu.northeastern.cs5500.starterbot.controller.*;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import com.mongodb.client.model.geojson.Position;

//import javax.inject.Inject;
import lombok.Data;
import org.bson.types.ObjectId;


@Data
public class PositionController {
    /*
    @Inject GenericRepository<Player> playerRepository;
    @Inject PlayerController playerController;
    @Inject
    PositionController(){}

//    GenericRepository<PokeMap> positionRepository;
    public List<MapNode> getNeighborForUser(String discordId){
        Player player = playerController.getPlayerFromMemberId(discordId);
        Integer currID = player.getCurrMapNode();
        MapNode currNode = this.getNodeByID(currID);
        List<MapNode> currNeighbor = PokeMap.pokeMap.getAdjNodes().get(currNode);
        return currNeighbor;
    }

    public String getNodeName(MapNode node){
        return node.getLocName();
    }

    public String getNodeAdd(MapNode node){
        return node.getPictureAddress();
    }

    public MapNode getNodeByID(Integer nodeID) {
        switch(nodeID){
            case 1: return MapNode.tsukushiNode;
            case 2: return MapNode.buzenNode;
            case 3: return MapNode.hizenNode;
            case 4: return MapNode.higoNode;
            case 5: return MapNode.bungoNode;
            case 6: return MapNode.satsumaNode;
            case 7: return MapNode.osumiNode;
            case 8: return MapNode.hyugaNode;
        }
//        return null;
        return MapNode.tsukushiNode;
    }

    public void MoveToNode(String discordID, Integer newLoc){
        Player player = playerController.getPlayerFromMemberId(discordID);
        player.setCurrMapNode(newLoc);
    }

    /*
    public void ShowCurrLocNamePic(String discordId){
        Player player = playerController.getPlayerFromMemberId(discordId);
        Integer currID = player.getCurrMapNode();
        MapNode currNode = this.getNodeByID(currID);
        System.out.print("Current Node: "+this.getNodeName(currNode));
        
    }*/
}
