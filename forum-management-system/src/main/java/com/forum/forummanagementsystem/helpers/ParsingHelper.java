package com.forum.forummanagementsystem.helpers;

import com.forum.forummanagementsystem.models.Tag;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ParsingHelper {

    public ParsingHelper() {
    }

    public static Set<String> fromStringToSetStrings(String str) {
        if (str == null || str.trim().isEmpty()) {
            return Set.of();
        } else {
            return Arrays.stream(str.trim().split("\\s+"))
                    .map(String::toLowerCase)
                    .map(s -> s.replaceAll("[^a-z]", ""))
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toSet());
        }
    }

    public static Set<String> fromSetTagToSetStrings(Set<Tag> tagSet) {
        return tagSet.stream()
                .map(Tag::getTagName)
                .collect(Collectors.toSet());
    }
}
