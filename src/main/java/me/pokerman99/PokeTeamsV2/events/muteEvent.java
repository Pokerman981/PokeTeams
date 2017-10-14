package me.pokerman99.PokeTeamsV2.events;

import java.io.IOException;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ClientConnectionEvent;

import io.github.nucleuspowered.nucleus.api.events.NucleusMuteEvent;
import me.pokerman99.PokeTeamsV2.PokeTeamsV2;

public class muteEvent {

	public PokeTeamsV2 plugin;

	public muteEvent(PokeTeamsV2 pluginInstance) {
		this.plugin = pluginInstance;
	}

	@Listener
	public void onMute(NucleusMuteEvent.Muted e) throws IOException {
		if (e.getTargetUser().isOnline()) {
			String command = "chat mute %player%".replace("%player%", e.getTargetUser().getName());
			Sponge.getCommandManager().process(Sponge.getServer().getConsole(), command);
			plugin.config().getNode(e.getTargetUser().getIdentifier().toString(), "muted").setValue(true);
			plugin.save();
		} else {
			plugin.config().getNode(e.getTargetUser().getIdentifier().toString(), "muted").setValue(true);
			plugin.save();
		}

		// plugin.config().getNode("players",
		// e.getTargetUser().getIdentifier().toString(),
		// "muted").setValue(true);
		// plugin.save();
	}

	@Listener
	public void onMute(NucleusMuteEvent.Unmuted e) throws IOException {
		if (plugin.config().getNode(e.getTargetUser().getIdentifier().toString(), "muted").isVirtual() == false) {
			if (e.getTargetUser().isOnline()) {
				String command = "chat mute %player%".replace("%player%", e.getTargetUser().getName());
				Sponge.getCommandManager().process(Sponge.getServer().getConsole(), command);
				plugin.config().getNode(e.getTargetUser().getIdentifier().toString(), "muted").setValue(false);
				plugin.save();
			} else {
				plugin.config().getNode(e.getTargetUser().getIdentifier().toString(), "muted").setValue(false);
				plugin.save();
			}
		}
		// plugin.config().getNode("players",
		// e.getTargetUser().getIdentifier().toString(),
		// "muted").setValue(false);
		// plugin.save();
	}

	public void onJoin(ClientConnectionEvent.Join e) throws IOException {
		if (plugin.config().getNode(e.getTargetEntity().getIdentifier().toString(), "muted").isVirtual() == false) {
			if (plugin.config().getNode(e.getTargetEntity().getIdentifier().toString(), "muted").getValue()
					.equals(false)) {
				String command = "chat mute %player%".replace("%player%", e.getTargetEntity().getName());
				Sponge.getCommandManager().process(Sponge.getServer().getConsole(), command);
				plugin.config().getNode(e.getTargetEntity().getIdentifier().toString(), "muted").setValue(false);
				plugin.save();
			}
		}
	}

}
