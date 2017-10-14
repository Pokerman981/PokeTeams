package me.pokerman99.PokeTeamsV2.commands.adm;

import java.io.IOException;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;

import me.pokerman99.PokeTeamsV2.PokeTeamsV2;

public class teamBan implements CommandExecutor {

	public PokeTeamsV2 plugin;

	public teamBan(PokeTeamsV2 pluginInstance) {
		this.plugin = pluginInstance;
	}

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		Player player = args.<Player>getOne("player").get();
		plugin.config().getNode("players", player.getIdentifier().toString(), "team").setValue("banned");
		try {plugin.save();} catch (IOException e) {e.printStackTrace();}
		PokeTeamsV2.sendMessage(src, "&a" + player.getName() + " was banned from teams!\nNew team identifer: " + plugin.config().getNode("players", player.getIdentifier().toString(), "team").getValue());	
		return CommandResult.success();
	}

}
