/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
 * SpoutPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * This file is part of Spout (http://wiki.getspout.org/).
 *
 * Spout is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spout is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spout;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.inventory.SpoutEnchantment;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.material.Material;
import org.getspout.spoutapi.material.MaterialData;

public class InventoryListener implements Listener{
	public InventoryListener(Spout plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		@SuppressWarnings("deprecation")
		org.getspout.spoutapi.event.inventory.InventoryCloseEvent spoutEvent = new org.getspout.spoutapi.event.inventory.InventoryCloseEvent((Player) event.getPlayer(), event.getView().getTopInventory(), event.getView().getBottomInventory());
		Bukkit.getServer().getPluginManager().callEvent(spoutEvent);
	}

	@EventHandler
	public void onInventoryOpen(InventoryOpenEvent event) {
		@SuppressWarnings("deprecation")
		org.getspout.spoutapi.event.inventory.InventoryOpenEvent spoutEvent = new org.getspout.spoutapi.event.inventory.InventoryOpenEvent((Player) event.getPlayer(), event.getView().getTopInventory(), event.getView().getBottomInventory());
		spoutEvent.setCancelled(event.isCancelled());
		Bukkit.getServer().getPluginManager().callEvent(spoutEvent);
		if (spoutEvent.isCancelled()) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler(priority=EventPriority.LOWEST)
	public void onItemCraft(CraftItemEvent e) {
		ItemStack res = e.getCurrentItem();
		res.removeEnchantment(SpoutEnchantment.UNSTACKABLE);
		Material m = MaterialData.getMaterial(res.getTypeId(), res.getDurability());
		if(m instanceof CustomItem) if(!((CustomItem) m).isStackable() && e.isShiftClick()) e.setCancelled(true);//shift clicking causes.... issues with unstackable Spout items.
		e.setCurrentItem(new SpoutItemStack(res));//handle enchantments and stuff.
	}
}
