package com.github.spook.antiabuse.integration.luckperms;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.Integration;

public class IntegrationLuckPerms extends Integration {

  // -------------------------------------------- //
  // INSTANCE & CONSTRUCT
  // -------------------------------------------- //

  private static final IntegrationLuckPerms i = new IntegrationLuckPerms();

  public static IntegrationLuckPerms get() {
    return i;
  }

  private IntegrationLuckPerms() {
    setPluginName("LuckPerms");
  }

  // -------------------------------------------- //
  // OVERRIDE
  // -------------------------------------------- //

  @Override
  public Engine getEngine() {
    return EngineLuckPerms.get();
  }
}
