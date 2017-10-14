package me.pokerman99.PokeTeamsV2.commands.adm;

import java.io.IOException;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;

import me.pokerman99.PokeTeamsV2.PokeTeamsV2;

public class reload implements CommandExecutor{
	
	public PokeTeamsV2 plugin;
	public reload(PokeTeamsV2 pluginInstance){
	     this.plugin = pluginInstance;
	}

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		try {

            plugin.config = plugin.loader.load();
			PokeTeamsV2.sendMessage(src, plugin.prefix()+ "&aReloaded");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return CommandResult.success();
	}

}
