package edu.northeastern.cs5500.starterbot.selectionHandler;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChallengeFriendSelectionHandlerTest {
    private ChallengeFriendSelectionHandler challengeFriendSelectionHandler;

    @BeforeEach
    void setUp() {
        challengeFriendSelectionHandler = new ChallengeFriendSelectionHandler();
    }

    @Test
    void testGetName() {
        assertThat(challengeFriendSelectionHandler.getName()).isEqualTo("menu:challenge");
    }
}
