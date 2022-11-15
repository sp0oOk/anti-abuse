package com.github.spook.antiabuse.nbt;

import net.minecraft.server.v1_8_R3.*;

@SuppressWarnings("all")
public class NBTTagListBuilder {
  private NBTTagList tagList;

  public NBTTagListBuilder(final NBTTagList list) {
    this.tagList = list;
  }

  public NBTTagListBuilder(final NBTBase comp) {
    if (comp instanceof NBTTagList) {
      this.tagList = (NBTTagList) comp;
    }
  }

  public NBTTagListBuilder() {
    this.tagList = new NBTTagList();
  }

  public NBTBase get(final int index) {
    if (this.tagList == null) {
      return null;
    }
    return this.tagList.get(index);
  }

  public Integer getIntAt(final int index) {
    if (this.tagList == null) {
      return null;
    }
    return ((NBTTagInt) this.get(index)).d();
  }

  public Double getDoubleAt(final int index) {
    if (this.tagList == null) {
      return null;
    }
    return ((NBTTagDouble) this.get(index)).g();
  }

  public Float getFloatAt(final int index) {
    if (this.tagList == null) {
      return null;
    }
    return ((NBTTagFloat) this.get(index)).h();
  }

  public String getStringAt(final int index) {
    if (this.tagList == null) {
      return null;
    }
    return this.tagList.getString(index);
  }

  public boolean contains(final String value) {
    if (this.tagList == null) {
      return false;
    }
    for (int i = 0; i < this.tagList.size(); ++i) {
      final String val = this.tagList.getString(i);
      if (val != null && val.equals(value)) {
        return true;
      }
    }
    return false;
  }

  public NBTTagListBuilder addInt(final int value) {
    return this.add(new NBTTagInt(value));
  }

  public NBTTagListBuilder addDouble(final double value) {
    return this.add(new NBTTagDouble(value));
  }

  public NBTTagListBuilder addFloat(final float value) {
    return this.add(new NBTTagFloat(value));
  }

  public NBTTagListBuilder addString(final String value) {
    return this.add(new NBTTagString(value));
  }

  public NBTTagListBuilder add(final NBTBase base) {
    if (this.tagList == null) {
      return this;
    }
    this.tagList.add(base);
    return this;
  }

  public int size() {
    return (this.tagList != null) ? this.tagList.size() : 0;
  }

  public NBTTagList build() {
    return this.tagList;
  }
}
