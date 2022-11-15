package com.github.spook.antiabuse.engine;

import com.github.spook.antiabuse.Lang;
import com.github.spook.antiabuse.Perm;
import com.github.spook.antiabuse.Utils;
import com.github.spook.antiabuse.entity.MConf;
import com.github.spook.antiabuse.nbt.NBTWrapper;
import com.massivecraft.massivecore.Engine;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;

public class EngineInteract extends Engine {

  // -------------------------------------------- //
  // INSTANCE & CONSTRUCT
  // -------------------------------------------- //

  private static final EngineInteract i = new EngineInteract();

  public static EngineInteract get() {
    return i;
  }

  // -------------------------------------------- //
  // LISTENER(S)
  // -------------------------------------------- //

  @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
  public void onMiddleClick(InventoryCreativeEvent event) {

    // Check if the player is in creative mode and the action is a creative inventory action
    if (event.getWhoClicked().getGameMode() == GameMode.CREATIVE
        && event.getClick().isCreativeAction()) {

      // If NBT Checks are enabled and, the item on cursor is not null and the item on cursor is not
      // air
      if (MConf.get().isNbtItemCheck()
          && event.getCursor() != null
          && event.getCursor().getType() != Material.AIR
          && !Perm.BYPASSES.has(event.getWhoClicked())) {
        ItemStack item = event.getCursor();
        // Check if the key is blacklisted (item is special)
        if (new NBTWrapper(item).hasAnyKeys(MConf.get().getNbtKeyList())) {
          // Trigger the violation alert
          Utils.triggerAlert((Player) event.getWhoClicked(), null, Lang.TRIED_TO_DUPLICATE_ITEMS);
          // Cancel the event, and erase item on cursor
          event.setCancelled(true);
          event.setResult(Event.Result.DENY);
          event.setCursor(null);
        }
      }
    }
  }
}
