package edu.northeastern.cs5500.starterbot.selectionHandler;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

public class SelectAbilitiesHandlerTest {
    @Test
    void testGetName() {
        SelectAbilitiesHandler selectAbilitiesHandler = new SelectAbilitiesHandler();
        assertThat(selectAbilitiesHandler.getName()).isEqualTo("menu:abilities");
    }
}
