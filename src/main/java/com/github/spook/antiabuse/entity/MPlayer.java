package com.github.spook.antiabuse.entity;

import com.github.spook.antiabuse.obj.ViolationEntry;
import com.massivecraft.massivecore.store.SenderEntity;
import com.massivecraft.massivecore.util.MUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MPlayer extends SenderEntity<MPlayer> {

  // -------------------------------------------- //
  // META
  // -------------------------------------------- //

  private List<ViolationEntry> violations = MUtil.list();

  public static MPlayer get(Object oid) {
    return MPlayerColl.get().get(oid);
  }

  public MPlayer load(MPlayer that) {
    this.violations = that.violations;
    return this;
  }

  // -------------------------------------------- //
  // GETTER/SETTER/METHOD(S)
  // -------------------------------------------- //

  public int getViolationLevel() {
    return this.violations.size() + 1; // Array starts at 0
  }

  public void addViolation(ViolationEntry violationEntry) {
    this.violations.add(violationEntry);
    changed();
  }

  public void expireViolations() {

    if (this.violations == null || this.violations.isEmpty()) {
      return;
    }

    this.violations.removeIf(ViolationEntry::isExpired);

    changed();
  }
}
