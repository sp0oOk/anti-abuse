package com.github.spook.antiabuse.entity;

import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.MUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MConf extends Entity<MConf> {

  // -------------------------------------------- //
  // INSTANCE & CONSTRUCT
  // -------------------------------------------- //

  protected static MConf i = new MConf();

  public static MConf get() {
    return i;
  }

  // -------------------------------------------- //
  // FIELDS
  // -------------------------------------------- //

  private int maxViolations = 3;
  private boolean alerts = true;
  private boolean worldEditChestCheck = true;
  private boolean nbtItemCheck = true;
  private long taskViolationExpireTimeMinutes = 10L;
  private long violationExpireTimeMinutes = 30L;
  private List<String> nbtKeyList = MUtil.list("specialItem");

  // -------------------------------------------- //
  // OVERRIDE
  // -------------------------------------------- //

  @Override
  public MConf load(MConf that) {
    this.maxViolations = that.maxViolations;
    return this;
  }
}
