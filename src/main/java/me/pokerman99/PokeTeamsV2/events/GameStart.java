package me.pokerman99.PokeTeamsV2.events;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;

import me.pokerman99.PokeTeamsV2.PokeTeamsV2;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

public class GameStart {

	public PokeTeamsV2 plugin;

	public GameStart(PokeTeamsV2 pluginInstance) {
		this.plugin = pluginInstance;
	}


	@Listener
	public void onStart(GameStartedServerEvent e) throws IOException, ObjectMappingException {
		if (plugin.calendar().get(Calendar.DAY_OF_MONTH) == 14) {
			ConfigurationNode root = plugin.config();
			Calendar cal = plugin.calendar();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int m = month - 1;
			int plasma = root.getNode("teamplasma").getInt();
			int aqua = root.getNode("teamaqua").getInt();
			int magma = root.getNode("teammagma").getInt();
			int rocket = root.getNode("teamrocket").getInt();
			plugin.getLogger().info("A new month has come! We are now on month:" + month);
			
			int wins[] = new int[] { aqua, magma, plasma, rocket };
			int winner = wins[0];
			for (int i = 1; i < wins.length; i++) {
				if (wins[i] > winner) {
					winner = wins[i];
				}
			}
			if (root.getNode("last-month-rewarded").isVirtual()) {
				plugin.getLogger().info("Node 'last-month-rewarded' did not exist. Creating...");
				root.getNode("last-month-rewarded").setValue(month);
				plugin.save();
			}
			if (root.getNode("last-month-rewarded").getInt() != month) {
				if (winner == aqua){root.getNode("this-months-winner").setValue("aqua");plugin.save();
				}else if (winner == magma){root.getNode("this-months-winner").setValue("magma");plugin.save();
				}else if (winner == plasma){root.getNode("this-months-winner").setValue("plasma");plugin.save();
				}else if (winner == rocket){root.getNode("this-months-winner").setValue("rocket");plugin.save();}
				plugin.getLogger().info(root.getNode("this-months-winner").getString() + " is the winner for this month!");
				root.getNode("last-month-rewarded").setValue(month);
				plugin.getLogger().info("Resetting team points! ");
				MessageChannel.TO_CONSOLE.send(Text.of("Team aqua points:" + aqua + " (just to have them the log file)"));
				MessageChannel.TO_CONSOLE.send(Text.of("Team magma points:" + magma + " (just to have them the log file)"));
				MessageChannel.TO_CONSOLE.send(Text.of("Team rocket points:" + rocket + " (just to have them the log file)"));
				MessageChannel.TO_CONSOLE.send(Text.of("Team plasma points:" + plasma + " (just to have them the log file)"));
				root.getNode("teammagma").setValue(0);
				root.getNode("teamplasma").setValue(0);
				root.getNode("teamaqua").setValue(0);
				root.getNode("teamrocket").setValue(0);
				plugin.save();
				if (root.getNode(month + "-rewarded").isVirtual()){
					plugin.getLogger().info(month + "-rewarded does not exist. Creating...");
					List<String> list = new ArrayList<String>();
					root.getNode(month + "-rewarded").setValue(list);
					plugin.save();
				}
					if (root.getNode(m + "-rewarded").isVirtual() == false){
						plugin.getLogger().info("Removing old list!");
						root.getNode(m + "-rewarded").setValue(null);
						plugin.save();
					
				}

			}

		}

	}
}
