package edu.northeastern.cs5500.starterbot.controller;

import com.mongodb.lang.Nullable;
import edu.northeastern.cs5500.starterbot.model.UserPreference;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import java.util.Collection;
import javax.annotation.Nonnull;
import javax.inject.Inject;

public class UserPreferenceController {

    @Inject GenericRepository<UserPreference> userPreferenceRepository;

    @Inject
    UserPreferenceController() {}

    public void setPreferredNameForUser(String discordMemberId, String preferredName) {
        UserPreference userPreference = getUserPreferenceForMemberId(discordMemberId);

        userPreference.setPreferredName(preferredName);
        userPreferenceRepository.add(userPreference);
    }

    @Nullable
    public String getPreferredNameForUser(String discordMemberId) {
        return getUserPreferenceForMemberId(discordMemberId).getPreferredName();
    }

    @Nonnull
    public UserPreference getUserPreferenceForMemberId(String discordMemberId) {
        Collection<UserPreference> userPreferences = userPreferenceRepository.getAll();
        for (UserPreference currentUserPreference : userPreferences) {
            if (currentUserPreference.getDiscordUserId().equals(discordMemberId)) {
                return currentUserPreference;
            }
        }

        UserPreference userPreference = new UserPreference();
        userPreference.setDiscordUserId(discordMemberId);
        return userPreference;
    }
}
