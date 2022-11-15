package com.github.spook.antiabuse;

import com.github.spook.antiabuse.entity.MConf;
import com.github.spook.antiabuse.entity.MPlayer;
import com.github.spook.antiabuse.obj.ViolationEntry;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public class Utils {

  // ALERT_FORMAT Constant
  private static final Lang alertFormat = Lang.ALERT_FORMAT;

  public static void triggerAlert(Player player, Player executor, Lang violation, Object... args) {

    // Get Massive Player
    final MPlayer mPlayer = MPlayer.get(player);

    // Check if alerts are enabled
    if (MConf.get().isAlerts()) {
      // For every player online
      for (Player online : AntiAbuse.get().getServer().getOnlinePlayers()) {
        // If they have the permission to receive alerts, and they have alerts enabled
        if (Perm.ALERTS.has(online) && online.hasMetadata("receiving_aa_alerts")) {
          // Send them the alert
          alertFormat.sendMessage(
              online, player.getName(), violation.getKey(), mPlayer.getViolationLevel());
        }
      }
    }

    // Add player violation entry
    mPlayer.addViolation(
        ViolationEntry.of(
            executor == null ? "CONSOLE" : executor.getName(),
            violation.getKey(),
            System.currentTimeMillis() + MConf.get().getViolationExpireTimeMinutes() * 60 * 1000));

    // Send confirmation of violation to player (Optional)
    violation.sendMessage(player, args);
  }

  public static String formatTime(long time) {
    long days = TimeUnit.MILLISECONDS.toDays(time);
    time -= TimeUnit.DAYS.toMillis(days);
    long hours = TimeUnit.MILLISECONDS.toHours(time);
    time -= TimeUnit.HOURS.toMillis(hours);
    long minutes = TimeUnit.MILLISECONDS.toMinutes(time);
    time -= TimeUnit.MINUTES.toMillis(minutes);
    long seconds = TimeUnit.MILLISECONDS.toSeconds(time);
    return clearWhitespace(
        (days > 0 ? days + "d " : "")
            + (hours > 0 ? hours + "h " : "")
            + (minutes > 0 ? minutes + "m " : "")
            + (seconds > 0 ? seconds + "s " : ""));
  }

  public static String clearWhitespace(String string) {
    return string.replaceAll("\\s", "");
  }
}
