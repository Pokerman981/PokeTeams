package me.pokerman99.PokeTeamsV2.commands.adm;

import java.io.IOException;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;

import me.pokerman99.PokeTeamsV2.PokeTeamsV2;

public class admAdd implements CommandExecutor {

	public PokeTeamsV2 plugin;

	public admAdd(PokeTeamsV2 pluginInstance) {
		this.plugin = pluginInstance;
	}

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		String message = args.<String>getOne("team").get();
		int number = args.<Integer>getOne("#").get();
		String team = "team" + message;
		int finale = plugin.config().getNode(team).getInt() + number;
		plugin.config().getNode("team" + message).setValue(finale);
		try {plugin.save();} catch (IOException e) {e.printStackTrace();}
		PokeTeamsV2.sendMessage(src, "&aAdding " + number + " points to team " + message);
		PokeTeamsV2.sendMessage(src, "&aNew point amount: " + plugin.config().getNode("team" + message).getInt());
		return CommandResult.success();
	}

}
