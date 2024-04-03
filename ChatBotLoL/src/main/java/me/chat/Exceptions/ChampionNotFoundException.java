package me.chat.Exceptions;

public class ChampionNotFoundException extends RuntimeException {
    public ChampionNotFoundException(Long champid) {
        super("Champion %d not found".formatted(champid));
    }
}
