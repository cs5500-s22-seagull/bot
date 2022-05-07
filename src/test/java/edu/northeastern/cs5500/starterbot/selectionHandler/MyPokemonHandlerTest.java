package edu.northeastern.cs5500.starterbot.selectionHandler;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyPokemonHandlerTest {
    private MyPokemonHandler myPokemonHandler;

    @BeforeEach
    void setUp() {
        myPokemonHandler = new MyPokemonHandler();
    }

    @Test
    void testGetName() {
        assertThat(myPokemonHandler.getName()).isEqualTo("menu:mypoke");
    }
}
