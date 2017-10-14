package me.pokerman99.PokeTeamsV2.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;

import me.pokerman99.PokeTeamsV2.PokeTeamsV2;

public class team implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		String prefix = "&f[&bPoke&cTeams&f] ";
		PokeTeamsV2.sendMessage(src, prefix + "Team Commands:\n" + prefix + "/team leaderboard");
		if (PokeTeamsV2.config().getNode("players", src.getIdentifier().toString(), "leader").isVirtual() == false) {
			PokeTeamsV2.sendMessage(src, prefix + "/team promote (player)");
		}
		if (PokeTeamsV2.config().getNode("players", src.getIdentifier().toString(), "team").isVirtual()){
			PokeTeamsV2.sendMessage(src, prefix + "/team select (team)");
		}
		if (PokeTeamsV2.config().getNode("players", src.getIdentifier().toString(), "team").isVirtual() == false){
			PokeTeamsV2.sendMessage(src, prefix + "/team members");
			PokeTeamsV2.sendMessage(src, prefix + "/tc (message) &7&o'teamchat'");
		}
		return CommandResult.success();
	}

}
