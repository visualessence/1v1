package gg.larp.onevone;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Match {

    private final String nameA;
    private final String nameB;
    private final UUID idA;
    private final UUID idB;
    private int scoreA = 0;
    private int scoreB = 0;
    private final BossBar bar;

    public Match(Player a, Player b) {
        this.nameA = a.getName();
        this.nameB = b.getName();
        this.idA   = a.getUniqueId();
        this.idB   = b.getUniqueId();

        bar = BossBar.bossBar(
            buildTitle(),
            1.0f,
            BossBar.Color.RED,
            BossBar.Overlay.PROGRESS
        );

        a.showBossBar(bar);
        b.showBossBar(bar);
    }

    public boolean hasPlayer(String name) {
        return nameA.equalsIgnoreCase(name) || nameB.equalsIgnoreCase(name);
    }

    public void addPoint(String name) {
        if (nameA.equalsIgnoreCase(name)) scoreA++;
        else if (nameB.equalsIgnoreCase(name)) scoreB++;
        bar.name(buildTitle());
    }

    public void declareWin(String winner) {
        bar.color(BossBar.Color.YELLOW);
        bar.progress(1.0f);
        bar.name(
            Component.text("★ ", NamedTextColor.GOLD)
                .append(Component.text(winner, NamedTextColor.YELLOW)
                    .decorate(TextDecoration.BOLD))
                .append(Component.text(" has won!", NamedTextColor.GOLD))
        );
    }

    public void removeBar() {
        Player a = Bukkit.getPlayer(idA);
        Player b = Bukkit.getPlayer(idB);
        if (a != null) a.hideBossBar(bar);
        if (b != null) b.hideBossBar(bar);
    }

    private Component buildTitle() {
        return Component.text(nameA, NamedTextColor.AQUA)
            .decorate(TextDecoration.BOLD)
            .append(Component.text("  " + scoreA, NamedTextColor.WHITE))
            .append(Component.text("  —  ", NamedTextColor.GRAY))
            .append(Component.text(scoreB + "  ", NamedTextColor.WHITE))
            .append(Component.text(nameB, NamedTextColor.RED)
                .decorate(TextDecoration.BOLD));
    }

    public String getNameA() { return nameA; }
    public String getNameB() { return nameB; }
}
