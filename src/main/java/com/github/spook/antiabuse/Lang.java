package com.github.spook.antiabuse;

import com.massivecraft.massivecore.util.Txt;
import org.bukkit.entity.Player;

public enum Lang {
  DID_SOMETHING_WRONG("<rose>You are not allowed to %s!"),
  TRIED_TO_ADD_PERMISSION("<rose>You are not permitted to add this permission (%s) to %s"),
  TRIED_TO_DUPLICATE_ITEMS(
      "<rose>You are not permitted to duplicate items, you have been flagged!"),
  ALERT_FORMAT("<red>[<rose>AA<red>] <rose>Player <under>%s<reset><rose> has been flagged for %s (VL: %d)");

  private final String message;

  Lang(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public String getKey() {
    return this.name();
  }

  public void sendMessage(Player player, Object... args) {
    player.sendMessage(Txt.parse(String.format(message, args)));
  }
}
