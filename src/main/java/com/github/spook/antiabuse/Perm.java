package com.github.spook.antiabuse;

import com.massivecraft.massivecore.Identified;
import com.massivecraft.massivecore.util.PermissionUtil;
import org.bukkit.permissions.Permissible;

public enum Perm implements Identified {
  // antiabuse.use (To use the parent command)
  USE,
  // antiabuse.violations (To view violations)
  VIOLATIONS,
  // antiabuse.bypasses (To bypass violations)
  BYPASSES,
  // antiabuse.alerts (To receive alerts)
  ALERTS;

  private final String id = PermissionUtil.createPermissionId(AntiAbuse.get(), this);

  @Override
  public String getId() {
    return id;
  }

  public boolean has(Permissible permissible, boolean verbose) {
    return PermissionUtil.hasPermission(permissible, this, verbose);
  }

  public boolean has(Permissible permissible) {
    return PermissionUtil.hasPermission(permissible, this);
  }
}
