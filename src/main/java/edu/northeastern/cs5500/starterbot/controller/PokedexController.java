package edu.northeastern.cs5500.starterbot.controller;

import edu.northeastern.cs5500.starterbot.model.Pokedex;
import edu.northeastern.cs5500.starterbot.model.PokemonInfo;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.bson.types.ObjectId;

@Singleton
public class PokedexController {

    @Inject
    PokedexController() {}

    @Inject GenericRepository<Pokedex> pokedexRepository;
    @Inject GenericRepository<PokemonInfo> pokemonInfoRepository;

    public int getSeen(ObjectId id) {
        Pokedex pokedex = pokedexRepository.get(id);
        return pokedex.getSeen();
    }

    public int getCaught(ObjectId id) {
        Pokedex pokedex = pokedexRepository.get(id);
        return pokedex.getCaught();
    }

    public ObjectId getPokemonInfo(ObjectId id) {
        Pokedex pokedex = pokedexRepository.get(id);
        PokemonInfo pokemonInfo = pokemonInfoRepository.get(pokedex.getPokemonInfo());
        return pokemonInfo.getId();
    }

    public ArrayList<String> getGeneralInfo(String pokeName) {
        Collection<PokemonInfo> pokemonInfos = pokemonInfoRepository.getAll();
        for (PokemonInfo pokemonInfo : pokemonInfos) {
            if (pokemonInfo.getPokemonName().equals(pokeName)) {
                ArrayList<String> res = new ArrayList<String>();
                res.add(pokemonInfo.getPokemonName());
                res.add(String.valueOf(pokemonInfo.getPokemonNumber()));
                res.add(pokemonInfo.getIntroduction());
                res.add(pokemonInfo.getPictureAddress());
                return res;
            }
        }
        return null;
    }

    public void addInitPokeInfo() {

        boolean hasPikachu = false;
        boolean hasPichu = false;
        boolean hasRaichu = false;
        boolean hasJigglypuff = false;
        boolean hasIgglybuff = false;
        boolean hasWigglytuff = false;

        ArrayList<String> pikaEvo = new ArrayList<String>();
        pikaEvo.add("Pichu");
        pikaEvo.add("Pikachu");
        pikaEvo.add("Raichu");

        ArrayList<String> jiggEvo = new ArrayList<String>();
        jiggEvo.add("Igglybuff");
        jiggEvo.add("Jigglypuff");
        jiggEvo.add("Wigglytuff");

        Collection<PokemonInfo> pokemonInfos = pokemonInfoRepository.getAll();
        for (PokemonInfo pokemonInfo : pokemonInfos) {
            if (pokemonInfo.getPokemonName().equals("Pichu")) {
                pokemonInfo.setEvolution(pikaEvo);
                pokemonInfo.setMaxCP(475);
                pokemonInfoRepository.update(pokemonInfo);
                hasPichu = true;
            }

            if (pokemonInfo.getPokemonName().equals("Pikachu")) {
                pokemonInfo.setMaxCP(950);
                pokemonInfo.setEvolution(pikaEvo);
                pokemonInfoRepository.update(pokemonInfo);
                hasPikachu = true;
            }

            if (pokemonInfo.getPokemonName().equals("Raichu")) {
                pokemonInfo.setEvolution(pikaEvo);
                pokemonInfo.setMaxCP(2300);
                pokemonInfoRepository.update(pokemonInfo);
                hasRaichu = true;
            }

            if (pokemonInfo.getPokemonName().equals("Igglybuff")) {
                pokemonInfo.setEvolution(jiggEvo);
                pokemonInfo.setMaxCP(520);
                pokemonInfoRepository.update(pokemonInfo);
                hasIgglybuff = true;
            }

            if (pokemonInfo.getPokemonName().equals("Jigglypuff")) {
                pokemonInfo.setEvolution(jiggEvo);
                pokemonInfo.setMaxCP(690);
                pokemonInfoRepository.update(pokemonInfo);
                hasJigglypuff = true;
            }

            if (pokemonInfo.getPokemonName().equals("Wigglytuff")) {
                pokemonInfo.setEvolution(jiggEvo);
                pokemonInfo.setMaxCP(1900);
                pokemonInfoRepository.update(pokemonInfo);
                hasWigglytuff = true;
            }
        }

        if (hasPichu == false) {
            PokemonInfo pokemonInfoNew = new PokemonInfo();
            pokemonInfoNew.setPokemonName("Pichu");
            pokemonInfoNew.setPokemonNumber(172);
            pokemonInfoNew.setIntroduction(
                    "Pichu charges itself with electricity more easily on days with thunderclouds or when the air is very dry. You can hear the crackling of static electricity coming off this Pokémon.");
            pokemonInfoNew.setMaxCP(475);
            pokemonInfoNew.setPictureAddress(
                    "https://upload.wikimedia.org/wikipedia/en/a/aa/Pichu_artwork.png");
            pokemonInfoNew.setEvolution(pikaEvo);
            HashMap<String, String> newMoves = new HashMap<String, String>();
            newMoves.put("Volt Switch", "70 100");
            newMoves.put("Thunderbolt", "90 100");
            newMoves.put("Thunder", "110 70");
            pokemonInfoNew.setMoves(newMoves);
            pokemonInfoRepository.add(pokemonInfoNew);
        }

        if (hasPikachu == false) {
            PokemonInfo pokemonInfoNew = new PokemonInfo();
            pokemonInfoNew.setPokemonName("Pikachu");
            pokemonInfoNew.setPokemonNumber(25);
            pokemonInfoNew.setIntroduction(
                    "Whenever Pikachu comes across something new, it blasts it with a jolt of electricity. If you come across a blackened berry, it's evidence that this Pokémon mistook the intensity of its charge.");
            pokemonInfoNew.setMaxCP(950);
            pokemonInfoNew.setPictureAddress(
                    "https://i.pinimg.com/564x/5d/59/26/5d592629a27f62ac4107692f6bc452ea.jpg");
            pokemonInfoNew.setEvolution(pikaEvo);
            HashMap<String, String> newMoves = new HashMap<String, String>();
            newMoves.put("Volt Switch", "70 100");
            newMoves.put("Thunderbolt", "90 100");
            newMoves.put("Thunder", "110 70");
            pokemonInfoNew.setMoves(newMoves);
            pokemonInfoRepository.add(pokemonInfoNew);
        }

        if (hasRaichu == false) {
            PokemonInfo pokemonInfoNew = new PokemonInfo();
            pokemonInfoNew.setPokemonName("Raichu");
            pokemonInfoNew.setPokemonNumber(26);
            pokemonInfoNew.setIntroduction(
                    "When you rub its cheeks, a sweet fragrance comes wafting out. However, you'll also get a light shock!");
            pokemonInfoNew.setMaxCP(2300);
            pokemonInfoNew.setPictureAddress(
                    "https://w7.pngwing.com/pngs/351/952/png-transparent-pikachu-pokemon-x-and-y-raichu-pikachu-mammal-cat-like-mammal-carnivoran-thumbnail.png");
            pokemonInfoNew.setEvolution(pikaEvo);

            HashMap<String, String> newMoves = new HashMap<String, String>();
            newMoves.put("Volt Switch", "70 100");
            newMoves.put("Thunderbolt", "90 100");
            newMoves.put("Thunder", "110 70");
            newMoves.put("Hyper Beam", "150 90");
            pokemonInfoNew.setMoves(newMoves);
            pokemonInfoRepository.add(pokemonInfoNew);
        }

        if (hasIgglybuff == false) {
            PokemonInfo pokemonInfoNew = new PokemonInfo();
            pokemonInfoNew.setPokemonName("Igglybuff");
            pokemonInfoNew.setPokemonNumber(174);
            pokemonInfoNew.setIntroduction(
                    "Igglybuff's vocal cords are not sufficiently developed. It would hurt its throat if it were to sing too much. This Pokémon gargles with freshwater from a clean stream.");
            pokemonInfoNew.setMaxCP(520);
            pokemonInfoNew.setPictureAddress(
                    "https://archives.bulbagarden.net/media/upload/4/4d/174Igglybuff.png");
            pokemonInfoNew.setEvolution(jiggEvo);
            HashMap<String, String> newMoves = new HashMap<String, String>();
            newMoves.put("Facade", "70 100");
            newMoves.put("Fire Blast", "110 85");
            newMoves.put("Psychic", "90 100");
            pokemonInfoNew.setMoves(newMoves);
            pokemonInfoRepository.add(pokemonInfoNew);
        }

        if (hasJigglypuff == false) {
            PokemonInfo pokemonInfoNew = new PokemonInfo();
            pokemonInfoNew.setPokemonName("Jigglypuff");
            pokemonInfoNew.setPokemonNumber(39);
            pokemonInfoNew.setIntroduction(
                    "Jigglypuff's vocal cords can freely adjust the wavelength of its voice. This Pokémon uses this ability to sing at precisely the right wavelength to make its foes most drowsy.");
            pokemonInfoNew.setMaxCP(690);
            pokemonInfoNew.setPictureAddress(
                    "https://archives.bulbagarden.net/media/upload/3/3e/039Jigglypuff.png");
            pokemonInfoNew.setEvolution(jiggEvo);
            HashMap<String, String> newMoves = new HashMap<String, String>();
            newMoves.put("Facade", "70 100");
            newMoves.put("Fire Blast", "110 85");
            newMoves.put("Psychic", "90 100");
            pokemonInfoNew.setMoves(newMoves);
            pokemonInfoRepository.add(pokemonInfoNew);
        }

        if (hasWigglytuff == false) {
            PokemonInfo pokemonInfoNew = new PokemonInfo();
            pokemonInfoNew.setPokemonName("Wigglytuff");
            pokemonInfoNew.setPokemonNumber(40);
            pokemonInfoNew.setIntroduction(
                    "Wigglytuff has large, saucerlike eyes. The surfaces of its eyes are always covered with a thin layer of tears. If any dust gets in this Pokémon's eyes, it is quickly washed away.");
            pokemonInfoNew.setMaxCP(1900);
            pokemonInfoNew.setPictureAddress(
                    "https://archives.bulbagarden.net/media/upload/9/92/040Wigglytuff.png");
            pokemonInfoNew.setEvolution(jiggEvo);

            HashMap<String, String> newMoves = new HashMap<String, String>();
            newMoves.put("Facade", "70 100");
            newMoves.put("Fire Blast", "110 85");
            newMoves.put("Psychic", "90 100");
            newMoves.put("Hyper Beam", "150 90");
            pokemonInfoNew.setMoves(newMoves);

            pokemonInfoRepository.add(pokemonInfoNew);
        }
    }
}
