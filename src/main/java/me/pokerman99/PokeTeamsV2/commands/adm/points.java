package me.pokerman99.PokeTeamsV2.commands.adm;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;

import me.pokerman99.PokeTeamsV2.PokeTeamsV2;

public class points implements CommandExecutor{

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		String prefix = "&f[&bPoke&cTeams&f] ";
		PokeTeamsV2.sendMessage(src, prefix + "Admin Commands");
		PokeTeamsV2.sendMessage(src, prefix + "/admteam points add (team) (#)");
		PokeTeamsV2.sendMessage(src, prefix + "/admteam points remove (team) (#)");
		return CommandResult.success();
	}

}
