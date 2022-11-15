package com.github.spook.antiabuse.obj;

import com.github.spook.antiabuse.Utils;
import lombok.Data;

import java.text.SimpleDateFormat;

@Data(staticConstructor = "of")
public class ViolationEntry {

  // -------------------------------------------- //
  // FIELDS
  // -------------------------------------------- //

  private final String executor;
  private final String violation;
  private final long time;

  // -------------------------------------------- //
  // GETTER/SETTER/METHOD(S)
  // -------------------------------------------- //

  public String getFormattedViolation() {
    final String pastTime = Utils.formatTime(time - System.currentTimeMillis());
    return String.format(
        "%s - %s - %s (Expires %s)",
        new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(time),
        executor,
        violation,
        pastTime.isEmpty() ? "next check" : pastTime);
  }

  public boolean isExpired() {
    return time - System.currentTimeMillis() < 0;
  }
}
