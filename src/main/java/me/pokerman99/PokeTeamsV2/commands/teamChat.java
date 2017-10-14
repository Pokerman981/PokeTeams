package me.pokerman99.PokeTeamsV2.commands;

import java.util.UUID;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;

import me.pokerman99.PokeTeamsV2.PokeTeamsV2;
import me.pokerman99.PokeTeamsV2.PokeTeamsV2.TeamTypes;

public class teamChat implements CommandExecutor {

	public PokeTeamsV2 plugin;

	public teamChat(PokeTeamsV2 pluginInstance) {
		this.plugin = pluginInstance;
	}

	String aquachat = plugin.config().getNode("messages", "teamchats", "aqua", "teamchat").getValue().toString();

	public String aquachat() {
		return aquachat;
	}

	String plasmachat = plugin.config().getNode("messages", "teamchats", "plasma", "teamchat").getValue().toString();

	public String plasmachat() {
		return plasmachat;
	}

	String magmachat = plugin.config().getNode("messages", "teamchats", "magma", "teamchat").getValue().toString();

	public String magmachat() {
		return magmachat;
	}

	String rocketchat = plugin.config().getNode("messages", "teamchats", "rocket", "teamchat").getValue().toString();

	public String rocketchat() {
		return rocketchat;
	}

	String aquachatspy = plugin.config().getNode("messages", "teamchats", "aqua", "teamchatspy").getValue().toString();

	public String aquachatspy() {
		return aquachatspy;
	}

	String plasmachatspy = plugin.config().getNode("messages", "teamchats", "plasma", "teamchatspy").getValue()
			.toString();

	public String plasmachatspy() {
		return plasmachatspy;
	}

	String magmachatspy = plugin.config().getNode("messages", "teamchats", "magma", "teamchatspy").getValue()
			.toString();

	public String magmachatspy() {
		return magmachatspy;
	}

	String rocketchatspy = plugin.config().getNode("messages", "teamchats", "rocket", "teamchatspy").getValue()
			.toString();

	public String rocketchatspy() {
		return rocketchatspy;
	}

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		String message = (String) args.getOne("message").get();
		UUID uuid = UUID.fromString(src.getIdentifier().toString());
		TeamTypes team = PokeTeamsV2.team.get(uuid);
		if (PokeTeamsV2.team.containsKey(uuid)) {
			switch (team) {
			case aqua: {
				for (Player player : Sponge.getGame().getServer().getOnlinePlayers()) {
					UUID uuidloop = player.getUniqueId();
					if (PokeTeamsV2.team.containsKey(uuidloop)) {
						if (PokeTeamsV2.team.get(uuidloop).equals(team)) {
							PokeTeamsV2.sendMessage(player,
									this.aquachat.replace("%player%", src.getName()).replace("%message%", message));
						}
						if (player.hasPermission("miscec.adminchat") && src.getName() != player.getName()) {
							PokeTeamsV2.sendMessage(player, this.aquachatspy().replace("%player%", src.getName())
									.replace("%message%", message));
						}
					}
					if (player.hasPermission("miscec.adminchat") && src.getName() != player.getName()) {
						PokeTeamsV2.sendMessage(player,
								this.aquachatspy().replace("%player%", src.getName()).replace("%message%", message));
					}
				}
				break;
			}
			case plasma: {
				for (Player player : Sponge.getGame().getServer().getOnlinePlayers()) {
					UUID uuidloop = player.getUniqueId();
					if (PokeTeamsV2.team.containsKey(uuidloop)) {
						if (PokeTeamsV2.team.get(uuidloop).equals(team)) {
							PokeTeamsV2.sendMessage(player,
									this.plasmachat().replace("%player%", src.getName()).replace("%message%", message));
						}
					}
					if (player.hasPermission("miscec.adminchat") && src.getName() != player.getName()) {
						PokeTeamsV2.sendMessage(player,
								this.plasmachatspy().replace("%player%", src.getName()).replace("%message%", message));
					}
				}
				break;
			}
			case rocket: {
				for (Player player : Sponge.getGame().getServer().getOnlinePlayers()) {
					UUID uuidloop = player.getUniqueId();
					if (PokeTeamsV2.team.containsKey(uuidloop)) {
						if (PokeTeamsV2.team.get(uuidloop).equals(team)) {
							PokeTeamsV2.sendMessage(player,
									this.rocketchat().replace("%player%", src.getName()).replace("%message%", message));
						}
					}
					if (player.hasPermission("miscec.adminchat") && src.getName() != player.getName()) {
						PokeTeamsV2.sendMessage(player,
								this.rocketchatspy().replace("%player%", src.getName()).replace("%message%", message));
					}
				}
				break;
			}
			case magma: {
				for (Player player : Sponge.getGame().getServer().getOnlinePlayers()) {
					UUID uuidloop = player.getUniqueId();
					if (PokeTeamsV2.team.containsKey(uuidloop)) {
						if (PokeTeamsV2.team.get(uuidloop).equals(team)) {
							PokeTeamsV2.sendMessage(player,
									this.magmachat().replace("%player%", src.getName()).replace("%message%", message));
						}
					}
					if (player.hasPermission("miscec.adminchat") && src.getName() != player.getName()) {
						PokeTeamsV2.sendMessage(player,
								this.magmachatspy().replace("%player%", src.getName()).replace("%message%", message));
					}
				}
				break;
			}
			case banned: {
				PokeTeamsV2.sendMessage(src, "&CYou are banned from using teams!");
				break;
			}
			default: {
				PokeTeamsV2.sendMessage(src, "&cYou are not on a team! Join a team by doing /team select (team)!");
			}
			}
		} else {
			PokeTeamsV2.sendMessage(src, "&cYou are not on a team! Join a team by doing /team select (team)!");
		}
		return CommandResult.success();
	}

	/*
	 * if (PokeTeamsV2.aqua.contains(uuid)){ for (Player player :
	 * Sponge.getServer().getOnlinePlayers()){ UUID uuidloop = (UUID)
	 * player.getUniqueId(); if (PokeTeamsV2.aqua.contains(uuidloop)){
	 * PokeTeamsV2.sendMessage(player, aquastring); } if
	 * (player.hasPermission("miscec.adminchat") && player.getName() !=
	 * src.getName()){ PokeTeamsV2.sendMessage(player, aquastringspy); } } }
	 */
}
