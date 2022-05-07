package edu.northeastern.cs5500.starterbot.selectionHandler;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.controller.*;
import edu.northeastern.cs5500.starterbot.model.*;
import edu.northeastern.cs5500.starterbot.repository.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddFriendSelectionHandlerTest {
    private PlayerController playerController;
    private AddFriendSelectionHandler addFriendSelectionHandler;
    private Player player1;
    private List<String> fList;

    @BeforeEach
    void setUp() {
        playerController = new PlayerController(new InMemoryRepository<>());
        addFriendSelectionHandler = new AddFriendSelectionHandler();
        player1 = playerController.getPlayerFromUserId("12345");
        playerController.getPlayerFromUserId("54321");
        playerController.getPlayerFromUserId("00003");
        fList = new ArrayList<String>();
        fList.add("54321");
        player1.setFriends(fList);
    }

    @Test
    void testAddCurrFriend() {
        addFriendSelectionHandler.addCurrFriend("12345", "00003", fList, playerController);
        assertThat(fList.size()).isEqualTo(2);
        fList.remove(1);
    }

    @Test
    void testGetName() {
        assertThat(addFriendSelectionHandler.getName()).isEqualTo("menu:friends");
    }

    @Test
    void testIfAlreadyFriend() {
        boolean res = addFriendSelectionHandler.ifAlreadyFriend("00003", fList, playerController);
        assertThat(res).isEqualTo(false);
        boolean res1 = addFriendSelectionHandler.ifAlreadyFriend("54321", fList, playerController);
        assertThat(res1).isEqualTo(true);
    }
}
