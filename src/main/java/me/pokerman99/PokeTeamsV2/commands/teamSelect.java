package me.pokerman99.PokeTeamsV2.commands;

import java.io.IOException;
import java.util.UUID;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;

import me.pokerman99.PokeTeamsV2.PokeTeamsV2;

public class teamSelect implements CommandExecutor {
	
	public PokeTeamsV2 plugin;
	public teamSelect(PokeTeamsV2 pluginInstance){
	     this.plugin = pluginInstance;
	}

	@SuppressWarnings("static-access")
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		String message = args.<String>getOne("teams").get();
		UUID uuid = UUID.fromString(src.getIdentifier());
			if (plugin.config().getNode("players", src.getIdentifier().toString(), "team").isVirtual() == true) {
					PokeTeamsV2.sendMessage(src, "&aYou have successfully joined the " + message +  " team!");
					plugin.config().getNode("players", src.getIdentifier().toString(), "team").setValue(message);
					plugin.config().getNode("players", src.getIdentifier().toString(), "battleWins").setValue(0);
					try {plugin.save();} catch (IOException e) {e.printStackTrace();}
					switch (message){
					case "aqua":{
						PokeTeamsV2.aqua.add(uuid);
						PokeTeamsV2.team.put(uuid, PokeTeamsV2.TeamTypes.aqua);
						break;
					}
					case "magma":{
						PokeTeamsV2.magma.add(uuid);
						PokeTeamsV2.team.put(uuid, PokeTeamsV2.TeamTypes.magma);
						break;
					}
					case "plasma":{
						PokeTeamsV2.plasma.add(uuid);
						PokeTeamsV2.team.put(uuid, PokeTeamsV2.TeamTypes.plasma);
						break;
					}
					case "rocket":{
						PokeTeamsV2.rocket.add(uuid);
						PokeTeamsV2.team.put(uuid, PokeTeamsV2.TeamTypes.rocket);
						break;
					}
					}
					return CommandResult.success();
			} 
			if (plugin.config().getNode("players", src.getIdentifier().toString(), "team").isVirtual() == false) {
				PokeTeamsV2.sendMessage(src, "&cYou are already in a team!");
				return CommandResult.success();
			}
		
		return CommandResult.empty();
	}

}