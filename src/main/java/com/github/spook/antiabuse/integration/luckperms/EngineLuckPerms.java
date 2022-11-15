package com.github.spook.antiabuse.integration.luckperms;

import com.massivecraft.massivecore.Engine;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.node.Node;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.Objects;

public class EngineLuckPerms extends Engine {

  // -------------------------------------------- //
  // INSTANCE & CONSTRUCT
  // -------------------------------------------- //

  private static final EngineLuckPerms i = new EngineLuckPerms();
  private LuckPerms luckPerms;

  public static EngineLuckPerms get() {
    return i;
  }

  private EngineLuckPerms() {}

  // -------------------------------------------- //
  // OVERRIDE
  // -------------------------------------------- //

  @Override
  public void setActive(boolean active) {
    active = Bukkit.getPluginManager().isPluginEnabled("LuckPerms");
    if(active) {
      RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
      if(provider != null) luckPerms = provider.getProvider();
    }
    super.setActive(active);
  }

  /**
   * Remove a permission from a player
   *
   * @param player The player to remove the permission from
   * @param permission The permission to remove
   * @return true if the permission was removed, false if not
   */
  private boolean removePermission(Player player, String permission) {
    return Objects.requireNonNull(this.luckPerms.getUserManager().getUser(player.getUniqueId()))
        .data()
        .remove(Node.builder(permission).build())
        .wasSuccessful();
  }
}
