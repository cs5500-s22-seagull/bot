/** This class is used to store the information of the players */
package edu.northeastern.cs5500.starterbot.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
public class Combat implements Model {
    ObjectId id;
    String discordUserA;
    String discordUserB;
    String turn;
}
