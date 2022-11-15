package com.github.spook.antiabuse.nbt;

import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.NBTBase;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;

import java.util.List;

@SuppressWarnings("all")
public class NBTWrapper {
  private final org.bukkit.inventory.ItemStack defaultItem;
  private ItemStack nmsItem;
  private NBTTagCompound compound;

  public NBTWrapper(final org.bukkit.inventory.ItemStack item, final boolean useDefault) {
    this.defaultItem = item;
    if (!useDefault) {
      this.nmsItem = CraftItemStack.asNMSCopy(item);
      this.compound = this.nmsItem.getTag();
    }
  }

  public NBTWrapper(final org.bukkit.inventory.ItemStack item) {
    this.defaultItem = item;
    this.load();
  }

  private void load() {
    this.nmsItem = CraftItemStack.asNMSCopy(this.defaultItem);
    this.compound = this.nmsItem.getTag();
  }

  public NBTWrapper remove(final String key) {
    if (this.compound == null) {
      return this;
    }
    this.compound.remove(key);
    return this;
  }

  public NBTTagCompound getOrCreateTag() {
    if (this.nmsItem == null) {
      if (this.defaultItem == null) {
        return null;
      }
      this.nmsItem = CraftItemStack.asNMSCopy(this.defaultItem);
    }
    if (this.compound == null) {
      this.compound = new NBTTagCompound();
    }
    return this.compound;
  }

  public org.bukkit.inventory.ItemStack getDefault() {
    return this.defaultItem;
  }

  public org.bukkit.inventory.ItemStack build() {
    if (this.nmsItem == null) {
      return null;
    }
    this.nmsItem.setTag(this.compound);
    return CraftItemStack.asCraftMirror(this.nmsItem);
  }

  public String getString(final String key) {
    if (this.nmsItem == null) {
      return "";
    }
    final NBTTagCompound tag = this.getTag();
    if (tag == null) {
      return "";
    }
    return tag.getString(key);
  }

  public Long getLong(final String key) {
    if (this.nmsItem == null) {
      return null;
    }
    final NBTTagCompound tag = this.getTag();
    if (tag == null) {
      return null;
    }
    return tag.getLong(key);
  }

  public Double getDouble(final String key) {
    if (this.nmsItem == null) {
      return null;
    }
    final NBTTagCompound tag = this.getTag();
    if (tag == null) {
      return null;
    }
    return tag.getDouble(key);
  }

  public Float getFloat(final String key) {
    if (this.nmsItem == null) {
      return null;
    }
    final NBTTagCompound tag = this.getTag();
    if (tag == null) {
      return null;
    }
    return tag.getFloat(key);
  }

  public Short getShort(final String key) {
    if (this.nmsItem == null) {
      return null;
    }
    final NBTTagCompound tag = this.getTag();
    if (tag == null) {
      return null;
    }
    return tag.getShort(key);
  }

  public Integer getInt(final String key) {
    if (this.nmsItem == null) {
      return null;
    }
    final NBTTagCompound tag = this.getTag();
    if (tag == null) {
      return null;
    }
    return tag.getInt(key);
  }

  public Boolean getBoolean(final String key) {
    if (this.nmsItem == null) {
      return null;
    }
    final NBTTagCompound tag = this.getTag();
    if (tag == null) {
      return null;
    }
    return tag.getBoolean(key);
  }

  public NBTBase getNBTBase(final String key) {
    if (this.nmsItem == null) {
      return null;
    }
    final NBTTagCompound tag = this.getTag();
    if (tag == null) {
      return null;
    }
    return tag.get(key);
  }

  public NBTTagCompoundBuilder getCompound(final String key) {
    if (this.nmsItem == null) {
      return null;
    }
    final NBTTagCompoundBuilder builder = new NBTTagCompoundBuilder(this.getNBTBase(key));
    if (builder.hasCompound()) {
      return null;
    }
    return builder;
  }

  public NBTTagCompoundBuilder getCompoundOrCreate(final String key) {
    if (this.nmsItem == null) {
      return null;
    }
    final NBTTagCompoundBuilder builder = new NBTTagCompoundBuilder(this.getNBTBase(key));
    if (builder.hasCompound()) {
      return new NBTTagCompoundBuilder();
    }
    return builder;
  }

  public boolean hasKey(final String key) {
    if (this.nmsItem == null) {
      return false;
    }
    final NBTTagCompound comp = this.getTag();
    return comp != null && comp.hasKey(key);
  }

  public boolean hasAnyKeys(List<String> keys) {
    if (this.nmsItem == null) {
      return false;
    }
    final NBTTagCompound comp = this.getTag();
    if (comp == null) {
      return false;
    }
    for (String key : keys) {
      if (comp.hasKey(key)) {
        return true;
      }
    }
    return false;
  }

  public NBTTagCompound getTag() {
    if (this.nmsItem == null) {
      return null;
    }
    return this.nmsItem.getTag();
  }

  public NBTWrapper setString(final String key, final String value) {
    this.getOrCreateTag().setString(key, value);
    return this;
  }

  public NBTWrapper setDouble(final String key, final Double value) {
    this.getOrCreateTag().setDouble(key, value);
    return this;
  }

  public NBTWrapper setFloat(final String key, final Float value) {
    this.getOrCreateTag().setFloat(key, value);
    return this;
  }

  public void setNBTBase(final String key, final NBTBase value) {
    this.getOrCreateTag().set(key, value);
  }

  public NBTWrapper setLong(final String key, final Long value) {
    this.getOrCreateTag().setLong(key, value);
    return this;
  }

  public NBTWrapper setShort(final String key, final Short value) {
    this.getOrCreateTag().setShort(key, value);
    return this;
  }

  public NBTWrapper setInteger(final String key, final Integer value) {
    this.getOrCreateTag().setInt(key, value);
    return this;
  }

  public NBTWrapper setBoolean(final String key, final Boolean value) {
    this.getOrCreateTag().setBoolean(key, value);
    return this;
  }

  public NBTWrapper setGlowing() {
    this.getOrCreateTag().set("enchant", new NBTTagListBuilder().build());
    return this;
  }
}
