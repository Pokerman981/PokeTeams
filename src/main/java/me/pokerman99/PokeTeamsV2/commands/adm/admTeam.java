package me.pokerman99.PokeTeamsV2.commands.adm;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;

import me.pokerman99.PokeTeamsV2.PokeTeamsV2;

public class admTeam implements CommandExecutor{

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		String prefix = "&f[&bPoke&cTeams&f] ";
		PokeTeamsV2.sendMessage(src, prefix + "Admin Commands");
		PokeTeamsV2.sendMessage(src, prefix + "/admteam points");
		PokeTeamsV2.sendMessage(src, prefix + "/admteam team");
		return CommandResult.success();
	}

}
