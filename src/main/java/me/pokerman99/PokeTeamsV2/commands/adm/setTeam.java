package me.pokerman99.PokeTeamsV2.commands.adm;

import java.io.IOException;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;

import me.pokerman99.PokeTeamsV2.PokeTeamsV2;

public class setTeam implements CommandExecutor{
	
	public PokeTeamsV2 plugin;
	public setTeam(PokeTeamsV2 pluginInstance){
	     this.plugin = pluginInstance;
	}

	@SuppressWarnings("static-access")
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		String message = args.<String>getOne("team").get();
		Player player = args.<Player>getOne("player").get();
		plugin.config().getNode("players", player.getIdentifier().toString(), "team").setValue(message);
		plugin.sendMessage(src, "&aSet " + player.getName() + "'s team to " + message);
		try {plugin.save();} catch (IOException e) {e.printStackTrace();}
		return CommandResult.success();
	}

}
