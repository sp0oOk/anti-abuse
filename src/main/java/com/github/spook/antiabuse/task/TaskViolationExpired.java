package com.github.spook.antiabuse.task;

import com.github.spook.antiabuse.entity.MConf;
import com.github.spook.antiabuse.entity.MPlayerColl;
import com.massivecraft.massivecore.ModuloRepeatTask;
import com.massivecraft.massivecore.util.TimeUnit;

public class TaskViolationExpired extends ModuloRepeatTask {

  // -------------------------------------------- //
  // INSTANCE
  // -------------------------------------------- //

  private static final TaskViolationExpired i = new TaskViolationExpired();

  public static TaskViolationExpired get() {
    return i;
  }

  // -------------------------------------------- //
  // OVERRIDE
  // -------------------------------------------- //

  @Override
  public long getDelayMillis() {
    return (MConf.get().getViolationExpireTimeMinutes() * TimeUnit.MILLIS_PER_MINUTE);
  }

  @Override
  public void invoke(long now) {

    getPlugin().log("Removing expired violations, this may take a moment...");
    MPlayerColl.get().expireViolations();
  }
}
