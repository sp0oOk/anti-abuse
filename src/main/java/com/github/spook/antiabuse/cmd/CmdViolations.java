package com.github.spook.antiabuse.cmd;

import com.github.spook.antiabuse.Perm;
import com.github.spook.antiabuse.entity.MPlayer;
import com.github.spook.antiabuse.obj.ViolationEntry;
import com.google.common.collect.ImmutableList;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.Parameter;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import com.massivecraft.massivecore.command.type.sender.TypePlayer;
import com.massivecraft.massivecore.pager.Pager;
import com.massivecraft.massivecore.pager.Stringifier;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class CmdViolations extends MassiveCommand {

  // -------------------------------------------- //
  // CONSTRUCT
  // -------------------------------------------- //

  public CmdViolations() {
    addParameter(TypePlayer.get(), "player", "you");
    addParameter(Parameter.getPage());
    addRequirements(RequirementHasPerm.get(Perm.VIOLATIONS), RequirementIsPlayer.get());
  }

  // -------------------------------------------- //
  // OVERRIDE
  // -------------------------------------------- //

  @Override
  public void perform() throws MassiveException {
    final Player player = (Player) readArg(sender);
    final int page = readArg();
    final MPlayer mPlayer = MPlayer.get(player);

    if (mPlayer.getViolations() == null || mPlayer.getViolations().size() == 0) {
      msg("<i>No violations found for <h>%s<i>.", player.getName());
      return;
    }

    final List<String> formattedViolations =
        mPlayer.getViolations().stream()
            .map(ViolationEntry::getFormattedViolation)
            .collect(Collectors.toList());

    final Pager<?> pager =
        new Pager<>(
            this,
            "Abuse Violations for " + player.getName(),
            page,
            formattedViolations,
            (Stringifier<String>) (o, i) -> ChatColor.YELLOW + o);

    pager.message();
  }

  @Override
  public List<String> getAliases() {
    return ImmutableList.of("violations", "violation", "v");
  }
}
