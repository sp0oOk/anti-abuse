package com.github.spook.antiabuse.cmd;

import com.github.spook.antiabuse.nbt.NBTTagCompoundBuilder;
import com.github.spook.antiabuse.nbt.NBTWrapper;
import com.google.common.collect.ImmutableList;
import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CmdTest extends MassiveCommand {

  // -------------------------------------------- //
  // CONSTRUCT
  // -------------------------------------------- //

  public CmdTest() {
    addRequirements(RequirementIsPlayer.get());
  }

  // -------------------------------------------- //
  // OVERRIDE
  // -------------------------------------------- //

  @Override
  public void perform() {
    final Player player = (Player) sender;

    final NBTWrapper wrapper = new NBTWrapper(new ItemStack(Material.DIAMOND_AXE, 1));
    final NBTTagCompoundBuilder builder = new NBTTagCompoundBuilder();
    builder.setString("someExampleKeyValue", "someExampleValue");
    wrapper.setNBTBase("specialItem", builder.get());

    player.getInventory().addItem(wrapper.build());
    msg("<i>Item added to inventory!");
  }

  @Override
  public List<String> getAliases() {
    return ImmutableList.of("test");
  }
}
