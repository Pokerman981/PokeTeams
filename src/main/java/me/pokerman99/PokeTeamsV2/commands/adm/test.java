package me.pokerman99.PokeTeamsV2.commands.adm;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;

import me.pokerman99.PokeTeamsV2.PokeTeamsV2;
import me.pokerman99.PokeTeamsV2.events.ConnectionListener;

public class test implements CommandExecutor{
	
	public PokeTeamsV2 plugin;
	public test(PokeTeamsV2 pluginInstance){
	     this.plugin = pluginInstance;
	}
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		
		
		
		
        
		return CommandResult.success();
	}

}
