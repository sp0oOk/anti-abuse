package com.github.spook.antiabuse.entity;

import com.massivecraft.massivecore.store.SenderColl;

public class MPlayerColl extends SenderColl<MPlayer> {

  // -------------------------------------------- //
  // INSTANCE & CONSTRUCT
  // -------------------------------------------- //

  private static final MPlayerColl i = new MPlayerColl();

  public static MPlayerColl get() {
    return i;
  }

  public void expireViolations() {
    for (MPlayer mPlayer : this.getAll()) {
      mPlayer.expireViolations();
    }
  }
}
