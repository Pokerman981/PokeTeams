package me.pokerman99.PokeTeamsV2.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;

import me.pokerman99.PokeTeamsV2.PokeTeamsV2;

public class leaderboard implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		String prefix = "&f[&bPoke&cTeams&f] ";
		PokeTeamsV2.sendMessage(src,
				prefix + "Leaderboard:\n" + prefix + "Team Aqua: " + PokeTeamsV2.config().getNode("teamaqua").getInt()
						+ "\n" + prefix + "Team Magma: " + PokeTeamsV2.config().getNode("teammagma").getInt() + "\n"
						+ prefix + "Team Rocket: " + PokeTeamsV2.config().getNode("teamrocket").getInt() + "\n" + prefix
						+ "Team Plasma: " + PokeTeamsV2.config().getNode("teamplasma").getInt());
		return CommandResult.success();
	}

}
