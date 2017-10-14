package me.pokerman99.PokeTeamsV2.events;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;

import com.google.common.reflect.TypeToken;
import com.ibm.icu.util.Calendar;

import me.pokerman99.PokeTeamsV2.PokeTeamsV2;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

public class ConnectionListener {
	public PokeTeamsV2 plugin;

	public ConnectionListener(PokeTeamsV2 pluginInstance) {
		this.plugin = pluginInstance;
	}

	@SuppressWarnings("static-access")
	@Listener
	public void onLogin(ClientConnectionEvent.Join e) throws InterruptedException, IOException, ObjectMappingException {
		Player player = e.getTargetEntity();
		UUID uuid = e.getTargetEntity().getUniqueId();

		// User Accounts
		if (plugin.config().getNode("players", player.getIdentifier().toString(), "name").isVirtual() == false) {
			if (!plugin.config().getNode("players", player.getIdentifier().toString(), "name").getValue()
					.equals(player.getName())) {
				MessageChannel.TO_CONSOLE.send(Text.of(
						plugin.config().getNode("players", player.getIdentifier().toString(), "name").getValue(),
						" changed to ", player.getName()));
				plugin.config().getNode("players", player.getIdentifier().toString(), "name")
						.setValue(player.getName());
				plugin.save();
			}
		}
		if (plugin.config().getNode("players", player.getIdentifier().toString(), "name").isVirtual() == true) {
			plugin.config().getNode("players", player.getIdentifier().toString(), "name").setValue(player.getName());
			plugin.config().getNode("players", player.getIdentifier().toString(), "muted").setValue(false);
			MessageChannel.TO_CONSOLE.send(Text.of("Saved " + player.getIdentifier().toString() + " to config"));
			plugin.save();
		}

		if (plugin.config().getNode("players", player.getIdentifier().toString(), "team").isVirtual() == false) {
			String team = plugin.config().getNode("players", player.getIdentifier().toString(), "team").getValue()
					.toString();

			if (team.equals("plasma")) {
				PokeTeamsV2.plasma.add(uuid);
				PokeTeamsV2.team.put(uuid, PokeTeamsV2.TeamTypes.plasma);
			}
			if (team.equals("aqua")) {
				PokeTeamsV2.aqua.add(uuid);
				PokeTeamsV2.team.put(uuid, PokeTeamsV2.TeamTypes.aqua);
			}
			if (team.equals("magma")) {
				PokeTeamsV2.magma.add(uuid);
				PokeTeamsV2.team.put(uuid, PokeTeamsV2.TeamTypes.magma);
			}
			if (team.equals("rocket")) {
				PokeTeamsV2.rocket.add(uuid);
				PokeTeamsV2.team.put(uuid, PokeTeamsV2.TeamTypes.rocket);
			}
			if (team.equals("banned")) {
				PokeTeamsV2.banned.add(uuid);
				PokeTeamsV2.team.put(uuid, PokeTeamsV2.TeamTypes.banned);
			}
			if (plugin.config().getNode("this-months-winner").isVirtual() == false) {
				String suuid = uuid.toString();
				String winner = plugin.config().getNode("this-months-winner").getString();
				int month = plugin.calendar().get(Calendar.MONTH) + 1;
				String node = month + "-rewarded";
				if (team.equals(winner)) {
					List<String> list = plugin.config().getNode(node).getList(TypeToken.of(String.class));
					if (list.contains(suuid)) {
						plugin.getLogger().info(suuid + " has already been rewarded!");
						return;
					} else {
						List<String> copy = new ArrayList<String>();
						copy.addAll(list);
						copy.add(suuid);
						plugin.config().getNode(node).setValue(copy);
						plugin.save();
						plugin.getLogger().info("Adding " + suuid + " to rewarded list!");
						reward(player);
					}
				}
			}
		}
	}

	public void reward(Player player) {
		Random rnd = new Random();
		Task.builder().delay(1, TimeUnit.SECONDS).execute(new Runnable() {
			public void run() {
				String m = plugin.config().getNode("messages", "playermessage").getString();
				List<String> rewardcommands = new ArrayList<String>();
				try {rewardcommands = plugin.config.getNode("messages", "rewardcommands").getList(TypeToken.of(String.class));} catch (ObjectMappingException e) {e.printStackTrace();}
				//
				List<String> rewardmessage = new ArrayList<String>();
				try {rewardmessage = plugin.config.getNode("messages", "rewardmessages").getList(TypeToken.of(String.class));} catch (ObjectMappingException e) {e.printStackTrace();}
				//
				int r = rnd.nextInt(rewardcommands.size());
				//
				int n = rnd.nextInt(2) + 1;
				String number3 = Integer.toString(n);
				String message = m.replace("%reward%", rewardmessage.get(r).replace("%number%", number3));
				Sponge.getCommandManager().process(Sponge.getServer().getConsole(), rewardcommands.get(r).replace("%player%", player.getName()).replace("%number%", number3));
				PokeTeamsV2.sendMessage(player, message);
				
				
			}
		}).submit(plugin);

	}

	public void onLogout(ClientConnectionEvent.Disconnect e) {
		UUID uuid = e.getTargetEntity().getUniqueId();
		if (PokeTeamsV2.plasma.contains(uuid)) {
			PokeTeamsV2.plasma.remove(uuid);
		}
		if (PokeTeamsV2.rocket.contains(uuid)) {
			PokeTeamsV2.rocket.remove(uuid);
		}
		if (PokeTeamsV2.aqua.contains(uuid)) {
			PokeTeamsV2.aqua.remove(uuid);
		}
		if (PokeTeamsV2.magma.contains(uuid)) {
			PokeTeamsV2.magma.remove(uuid);
		}
		if (PokeTeamsV2.banned.contains(uuid)) {
			PokeTeamsV2.banned.remove(uuid);
		}
	}
}
