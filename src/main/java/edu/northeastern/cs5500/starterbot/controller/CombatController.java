package edu.northeastern.cs5500.starterbot.controller;

import edu.northeastern.cs5500.starterbot.model.Combat;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

@Slf4j
@Singleton
public class CombatController {

    @Inject
    CombatController() {}

    @Inject GenericRepository<Combat> combatRepository;

    public Combat getCombatByUserIds(String userA, String userB) {
        for (Combat combat : combatRepository.getAll()) {
            if (combat.getDiscordUserA().equals(userA) && combat.getDiscordUserB().equals(userB)
                    || combat.getDiscordUserA().equals(userB)
                            && combat.getDiscordUserB().equals(userA)) {
                log.info("returned existing");
                return combat;
            }
        }
        log.info("returned new");
        Combat newBat = new Combat();
        newBat.setDiscordUserA(userA);
        newBat.setDiscordUserB(userB);
        newBat.setTurn(userB);
        combatRepository.add(newBat);
        return newBat;
    }

    // accept combat
    public Combat getCombatByUserIds(String userA) {
        for (Combat combat : combatRepository.getAll()) {
            if (combat.getDiscordUserB().equals(userA) || combat.getDiscordUserA().equals(userA)) {
                return combat;
            }
        }
        return null;
    }

    public void setTurn(String user) {
        Combat combat = getCombatByUserIds(user);
        combat.setTurn(user);
        combatRepository.update(combat);
    }

    public void resolveCombat(ObjectId id) {
        combatRepository.delete(id);
    }
}
