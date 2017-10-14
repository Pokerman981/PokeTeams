package me.pokerman99.PokeTeamsV2.commands;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;

import me.pokerman99.PokeTeamsV2.PokeTeamsV2;

public class members implements CommandExecutor {

	public PokeTeamsV2 plugin;

	public members(PokeTeamsV2 pluginInstance) {
		this.plugin = pluginInstance;
	}

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if (plugin.config().getNode("players", src.getIdentifier().toString(), "team").isVirtual() == false) {
			if (plugin.config().getNode("players", src.getIdentifier().toString(), "team").getValue()
					.equals("plasma")) {
				PokeTeamsV2.sendMessage(src, plugin.prefix() + "Plasma members online:");
				for (Player player : Sponge.getServer().getOnlinePlayers()) {
					if (plugin.config().getNode("players", player.getIdentifier().toString(), "team")
							.isVirtual() == false) {
						if (plugin.config().getNode("players", player.getIdentifier().toString(), "team").getValue()
								.equals("plasma")) {
							PokeTeamsV2.sendMessage(src, "&f[&cTeams&f] " + player.getName());
						}
					}
				}
			}
			if (plugin.config().getNode("players", src.getIdentifier().toString(), "team").getValue()
					.equals("rocket")) {
				PokeTeamsV2.sendMessage(src, plugin.prefix() + "Rocket members online:");
				for (Player player : Sponge.getServer().getOnlinePlayers()) {
					if (plugin.config().getNode("players", player.getIdentifier().toString(), "team")
							.isVirtual() == false) {
						if (plugin.config().getNode("players", player.getIdentifier().toString(), "team").getValue()
								.equals("rocket")) {
							PokeTeamsV2.sendMessage(src, "&f[&cTeams&f] " + player.getName());
						}
					}
				}
			}
			if (plugin.config().getNode("players", src.getIdentifier().toString(), "team").getValue().equals("aqua")) {
				PokeTeamsV2.sendMessage(src, plugin.prefix() + "Aqua members online:");
				for (Player player : Sponge.getServer().getOnlinePlayers()) {
					if (plugin.config().getNode("players", player.getIdentifier().toString(), "team")
							.isVirtual() == false) {
						if (plugin.config().getNode("players", player.getIdentifier().toString(), "team").getValue()
								.equals("aqua")) {
							PokeTeamsV2.sendMessage(src, "&f[&cTeams&f] " + player.getName());
						}
					}
				}
			}
			if (plugin.config().getNode("players", src.getIdentifier().toString(), "team").getValue().equals("magma")) {
				PokeTeamsV2.sendMessage(src, plugin.prefix() + "Magma members online:");
				for (Player player : Sponge.getServer().getOnlinePlayers()) {
					if (plugin.config().getNode("players", player.getIdentifier().toString(), "team")
							.isVirtual() == false) {
						if (plugin.config().getNode("players", player.getIdentifier().toString(), "team").getValue()
								.equals("magma")) {
							PokeTeamsV2.sendMessage(src, "&f[&cTeams&f] " + player.getName());
						}
					}
				}
			}
			if (plugin.config().getNode("players", src.getIdentifier().toString(), "team").getValue().equals("banned")){
				PokeTeamsV2.sendMessage(src, "&cYou have been banned from teams!");
			}
		}else {
			PokeTeamsV2.sendMessage(src, "&cYou are not on a team! Select a team with /team select");
		}

		return CommandResult.success();
	}

}
