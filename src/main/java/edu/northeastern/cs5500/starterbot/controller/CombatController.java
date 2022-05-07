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
    private GenericRepository<Combat> combatRepository;

    @Inject
    public CombatController(GenericRepository<Combat> combatRepository) {
        this.combatRepository = combatRepository;
    }

    /**
     * Start a combat to another player
     *
     * @param userA
     * @param userB
     * @return Combat
     */
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

    /**
     * Accept combat
     *
     * @param userA
     * @return Combat
     */
    public Combat getCombatByUserIds(String userA) {
        for (Combat combat : combatRepository.getAll()) {
            if (combat.getDiscordUserB().equals(userA) || combat.getDiscordUserA().equals(userA)) {
                return combat;
            }
        }
        return null;
    }

    /**
     * Set turn for user
     *
     * @param user
     */
    public void setTurn(String user) {
        Combat combat = getCombatByUserIds(user);
        combat.setTurn(user);
        combatRepository.update(combat);
    }

    /**
     * Resolve combat when combat end
     *
     * @param id
     */
    public void resolveCombat(ObjectId id) {
        combatRepository.delete(id);
    }
}
