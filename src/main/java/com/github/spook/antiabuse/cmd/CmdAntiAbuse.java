package com.github.spook.antiabuse.cmd;

import com.github.spook.antiabuse.Perm;
import com.google.common.collect.ImmutableList;
import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;

import java.util.List;

public class CmdAntiAbuse extends MassiveCommand {

  // -------------------------------------------- //
  // FIELDS
  // -------------------------------------------- //

  private static final CmdAntiAbuse i = new CmdAntiAbuse();

  public CmdAlerts cmdAlerts = new CmdAlerts();
  public CmdViolations cmdViolations = new CmdViolations();
  public CmdTest cmdTest = new CmdTest();

  // -------------------------------------------- //
  // INSTANCE & CONSTRUCT
  // -------------------------------------------- //

  public static CmdAntiAbuse get() {
    return i;
  }

  public CmdAntiAbuse() {
    addChild(cmdAlerts);
    addChild(cmdViolations);
    addChild(cmdTest);
    addRequirements(RequirementHasPerm.get(Perm.USE));
  }

  // -------------------------------------------- //
  // OVERRIDE
  // -------------------------------------------- //

  @Override
  public List<String> getAliases() {
    return ImmutableList.of("antiabuse", "aa");
  }
}
