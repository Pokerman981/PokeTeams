package me.pokerman99.PokeTeamsV2.events;

import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Logger;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;

import com.pixelmonmod.pixelmon.api.events.PlayerBattleEndedEvent;

import me.pokerman99.PokeTeamsV2.PokeTeamsV2;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class battleEvent {
	
	public PokeTeamsV2 plugin;
	public battleEvent(PokeTeamsV2 pluginInstance){
	     this.plugin = pluginInstance;
	}


	@SuppressWarnings("static-access")
	@SubscribeEvent
	public void onBattle(PlayerBattleEndedEvent event) throws IOException {
		Player player = (Player) event.player;
		long time = plugin.calendar().getTimeInMillis() / 1000;
		int plasma = plugin.config().getNode("teamplasma").getInt();
		int aqua = plugin.config().getNode("teamaqua").getInt();
		int rocket = plugin.config().getNode("teamrocket").getInt();
		int magma = plugin.config().getNode("teammagma").getInt();
		int battleWins = plugin.config().getNode("players", player.getIdentifier().toString(), "battleWins").getInt();
		if (event.battleController.playerNumber > 1) {
			if (event.result.toString() == "VICTORY") {
				PokeTeamsV2.sendMessage(player, "&aCongratulations on winning your battle, " + player.getName());
				if (plugin.config().getNode("players", player.getIdentifier().toString(), "team").isVirtual()) {
					PokeTeamsV2.sendMessage(player, "&cYou are not on a team! No point awarded!");

				}
				if (plugin.config().getNode("players", player.getIdentifier().toString(), "team").getValue().equals("plasma")) {
					if (plugin.config().getNode("players", player.getIdentifier().toString(), "cool-down").isVirtual()) {
						plugin.config().getNode("players", player.getIdentifier().toString(), "cool-down").setValue(time + 60);
						plasma++;
						PokeTeamsV2.sendMessage(player, "&aYou have won 1 point for your team! Team score is now " + plasma);
						plugin.config().getNode("teamplasma").setValue(plasma);
						battleWins++;
						plugin.config().getNode("players", player.getIdentifier().toString(), "battleWins").setValue(battleWins);
						plugin.save();
						MessageChannel.TO_CONSOLE.send(Text.of(player.getName(), " now has ", battleWins, " battle wins"));
						return;
					}
					if (plugin.config().getNode("players", player.getIdentifier().toString(), "cool-down")
							.isVirtual() == false) {
						if (plugin.config().getNode("players", player.getIdentifier().toString(), "cool-down")
								.getLong() < time) {
							plugin.config().getNode("players", player.getIdentifier().toString(), "cool-down")
									.setValue(time + 60);
							plasma++;
							PokeTeamsV2.sendMessage(player, "&aYou have won 1 point for your team! Team score is now " + plasma);
							plugin.config().getNode("teamplasma").setValue(plasma);
							battleWins++;
							plugin.config().getNode("players", player.getIdentifier().toString(), "battleWins").setValue(battleWins);
							plugin.save();
							MessageChannel.TO_CONSOLE.send(Text.of(player.getName(), " now has ", battleWins, " battle wins"));
							return;
						} else {
							PokeTeamsV2.sendMessage(player, "&cYou recently won a battle! No point awarded!");
							return;
						}
					}
				}
				if (plugin.config().getNode("players", player.getIdentifier().toString(), "team").getValue().equals("rocket")) {
					if (plugin.config().getNode("players", player.getIdentifier().toString(), "cool-down").isVirtual()) {
						plugin.config().getNode("players", player.getIdentifier().toString(), "cool-down").setValue(time + 60);
						rocket++;
						PokeTeamsV2.sendMessage(player, "&aYou have won 1 point for your team! Team score is now " + rocket);
						plugin.config().getNode("teamrocket").setValue(rocket);
						battleWins++;
						plugin.config().getNode("players", player.getIdentifier().toString(), "battleWins").setValue(battleWins);
						plugin.save();
						MessageChannel.TO_CONSOLE.send(Text.of(player.getName(), " now has ", battleWins, " battle wins"));
						return;
					}
					if (plugin.config().getNode("players", player.getIdentifier().toString(), "cool-down")
							.isVirtual() == false) {
						if (plugin.config().getNode("players", player.getIdentifier().toString(), "cool-down")
								.getLong() < time) {
							plugin.config().getNode("players", player.getIdentifier().toString(), "cool-down")
									.setValue(time + 60);
							rocket++;
							PokeTeamsV2.sendMessage(player, "&aYou have won 1 point for your team! Team score is now " + rocket);
							plugin.config().getNode("teamrocket").setValue(rocket);
							battleWins++;
							plugin.config().getNode("players", player.getIdentifier().toString(), "battleWins").setValue(battleWins);
							plugin.save();
							MessageChannel.TO_CONSOLE.send(Text.of(player.getName(), " now has ", battleWins, " battle wins"));
							return;
						} else {
							PokeTeamsV2.sendMessage(player, "&cYou recently won a battle! No point awarded!");
							return;
						}
					}
				}
				if (plugin.config().getNode("players", player.getIdentifier().toString(), "team").getValue().equals("magma")) {
					if (plugin.config().getNode("players", player.getIdentifier().toString(), "cool-down").isVirtual()) {
						plugin.config().getNode("players", player.getIdentifier().toString(), "cool-down").setValue(time + 60);
						magma++;
						PokeTeamsV2.sendMessage(player, "&aYou have won 1 point for your team! Team score is now " + magma);
						plugin.config().getNode("teammagma").setValue(magma);
						battleWins++;
						plugin.config().getNode("players", player.getIdentifier().toString(), "battleWins").setValue(battleWins);
						plugin.save();
						MessageChannel.TO_CONSOLE.send(Text.of(player.getName(), " now has ", battleWins, " battle wins"));
						return;
					}
					if (plugin.config().getNode("players", player.getIdentifier().toString(), "cool-down")
							.isVirtual() == false) {
						if (plugin.config().getNode("players", player.getIdentifier().toString(), "cool-down")
								.getLong() < time) {
							plugin.config().getNode("players", player.getIdentifier().toString(), "cool-down")
									.setValue(time + 60);
							magma++;
							PokeTeamsV2.sendMessage(player, "&aYou have won 1 point for your team! Team score is now " + magma);
							plugin.config().getNode("teammagma").setValue(magma);
							battleWins++;
							plugin.config().getNode("players", player.getIdentifier().toString(), "battleWins").setValue(battleWins);
							plugin.save();
							MessageChannel.TO_CONSOLE.send(Text.of(player.getName(), " now has ", battleWins, " battle wins"));
							return;
						} else {
							PokeTeamsV2.sendMessage(player, "&cYou recently won a battle! No point awarded!");
							return;
						}
					}
				}
				if (plugin.config().getNode("players", player.getIdentifier().toString(), "team").getValue().equals("aqua")) {
					if (plugin.config().getNode("players", player.getIdentifier().toString(), "cool-down").isVirtual()) {
						plugin.config().getNode("players", player.getIdentifier().toString(), "cool-down").setValue(time + 60);
						aqua++;
						PokeTeamsV2.sendMessage(player, "&aYou have won 1 point for your team! Team score is now " + aqua);
						plugin.config().getNode("teamaqua").setValue(aqua);
						battleWins++;
						plugin.config().getNode("players", player.getIdentifier().toString(), "battleWins").setValue(battleWins);
						plugin.save();
						MessageChannel.TO_CONSOLE.send(Text.of(player.getName(), " now has ", battleWins, " battle wins"));
						return;
					}
					if (plugin.config().getNode("players", player.getIdentifier().toString(), "cool-down")
							.isVirtual() == false) {
						if (plugin.config().getNode("players", player.getIdentifier().toString(), "cool-down")
								.getLong() < time) {
							plugin.config().getNode("players", player.getIdentifier().toString(), "cool-down")
									.setValue(time + 60);
							aqua++;
							PokeTeamsV2.sendMessage(player, "&aYou have won 1 point for your team! Team score is now " + aqua);
							plugin.config().getNode("teamaqua").setValue(aqua);
							battleWins++;
							plugin.config().getNode("players", player.getIdentifier().toString(), "battleWins").setValue(battleWins);
							plugin.save();
							MessageChannel.TO_CONSOLE.send(Text.of(player.getName(), " now has ", battleWins, " battle wins"));
							return;
						} else {
							PokeTeamsV2.sendMessage(player, "&cYou recently won a battle! No point awarded!");
							return;
						}
					}
				}
			}
		}
	}

}
