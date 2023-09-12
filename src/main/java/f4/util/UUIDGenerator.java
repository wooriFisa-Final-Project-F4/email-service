package f4.util;

import java.util.UUID;

public class UUIDGenerator {
  public static String generate() {
    UUID uuid = UUID.randomUUID();
    return Long.toHexString(uuid.getMostSignificantBits()).substring(0, 6).toUpperCase();
  }
}
