package com.github.spook.antiabuse.cmd;

import com.github.spook.antiabuse.AntiAbuse;
import com.github.spook.antiabuse.Perm;
import com.google.common.collect.ImmutableList;
import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.List;

public class CmdAlerts extends MassiveCommand {

  // -------------------------------------------- //
  // CONSTRUCT
  // -------------------------------------------- //

  public CmdAlerts() {
    addRequirements(RequirementHasPerm.get(Perm.ALERTS), RequirementIsPlayer.get());
  }

  // -------------------------------------------- //
  // OVERRIDE
  // -------------------------------------------- //

  @Override
  public void perform() {
    final Player player = (Player) sender;
    final boolean alerts = !player.hasMetadata("receiving_aa_alerts");

    if (alerts) {
      player.setMetadata("receiving_aa_alerts", new FixedMetadataValue(AntiAbuse.get(), true));
      msg("<i>AntiAbuse alerts enabled.");
      return;
    }

    player.removeMetadata("receiving_aa_alerts", AntiAbuse.get());
    msg("<i>AntiAbuse alerts disabled.");
  }

  @Override
  public List<String> getAliases() {
    return ImmutableList.of("alerts", "alert");
  }
}
