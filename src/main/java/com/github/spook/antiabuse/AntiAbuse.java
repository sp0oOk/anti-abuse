package com.github.spook.antiabuse;

import com.massivecraft.massivecore.MassivePlugin;

public final class AntiAbuse extends MassivePlugin {

  // -------------------------------------------- //
  // INSTANCE & CONSTRUCT
  // -------------------------------------------- //

  private static AntiAbuse instance;

  public static AntiAbuse get() {
    return instance;
  }

  public AntiAbuse() {
    instance = this;
    setVersionSynchronized(false);
  }

  // -------------------------------------------- //
  // OVERRIDE
  // -------------------------------------------- //

  @Override
  public void onEnableInner() {
    activateAuto();
  }
}
