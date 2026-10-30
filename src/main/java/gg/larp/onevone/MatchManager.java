package gg.larp.onevone;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class MatchManager {

    private final Map<String, Match> byPlayer = new HashMap<>();

    public String startMatch(Player a, Player b) {
        if (byPlayer.containsKey(key(a.getName())))
            return a.getName() + " is already in a match.";
        if (byPlayer.containsKey(key(b.getName())))
            return b.getName() + " is already in a match.";
        Match m = new Match(a, b);
        byPlayer.put(key(a.getName()), m);
        byPlayer.put(key(b.getName()), m);
        return null;
    }

    public Match getMatch(String name) {
        return byPlayer.get(key(name));
    }

    public void endMatch(String name) {
        Match m = byPlayer.get(key(name));
        if (m == null) return;
        m.removeBar();
        byPlayer.remove(key(m.getNameA()));
        byPlayer.remove(key(m.getNameB()));
    }

    public void clearAll() {
        byPlayer.values().stream().distinct().forEach(Match::removeBar);
        byPlayer.clear();
    }

    private String key(String n) { return n.toLowerCase(); }
}
