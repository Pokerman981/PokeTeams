package me.pokerman99.PokeTeamsV2;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.GameReloadEvent;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.pixelmonmod.pixelmon.Pixelmon;

import me.pokerman99.PokeTeamsV2.commands.team;
import me.pokerman99.PokeTeamsV2.commands.teamChat;
import me.pokerman99.PokeTeamsV2.commands.adm.Teams;
import me.pokerman99.PokeTeamsV2.commands.adm.admAdd;
import me.pokerman99.PokeTeamsV2.commands.adm.admRemove;
import me.pokerman99.PokeTeamsV2.commands.adm.admTeam;
import me.pokerman99.PokeTeamsV2.commands.adm.points;
import me.pokerman99.PokeTeamsV2.commands.adm.reload;
import me.pokerman99.PokeTeamsV2.commands.adm.setTeam;
import me.pokerman99.PokeTeamsV2.commands.adm.teamBan;
import me.pokerman99.PokeTeamsV2.commands.adm.test;
import me.pokerman99.PokeTeamsV2.events.ConnectionListener;
import me.pokerman99.PokeTeamsV2.events.GameStart;
import me.pokerman99.PokeTeamsV2.events.battleEvent;
import me.pokerman99.PokeTeamsV2.events.muteEvent;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

@Plugin(id = "poketeams", name = "poketeams", version = "1.0", description = "Pokemon Teams")

public class PokeTeamsV2 {

	@Inject
	@DefaultConfig(sharedRoot = false)
	private Path defaultConfig;

	@Inject
	@DefaultConfig(sharedRoot = false)
	public ConfigurationLoader<CommentedConfigurationNode> loader;

	@Inject
	@ConfigDir(sharedRoot = false)
	private Path ConfigDir;

	public static CommentedConfigurationNode config;

	public static CommentedConfigurationNode config() {
		return config;
	}

	public void save() throws IOException {
		loader.save(config);
	}

	String prefix = "&f[&bPoke&cTeams&f] ";

	public String prefix() {
		return prefix;
	}

	@Inject
	private Logger logger;

	public Logger getLogger() {
		return logger;
	}

	public static List<UUID> plasma = new ArrayList<>();
	public static List<UUID> magma = new ArrayList<>();
	public static List<UUID> aqua = new ArrayList<>();
	public static List<UUID> rocket = new ArrayList<>();
	public static List<UUID> banned = new ArrayList<>();
	public static Map<UUID, TeamTypes> team = new HashMap<>();

	public static enum TeamTypes {
		plasma, rocket, aqua, magma, banned
	}

	public Calendar calendar() {
		Calendar cal = Calendar.getInstance();
		return cal;
	}

	@Listener
	public void onPreInitialize(GamePreInitializationEvent event) {
		CommandSource console = Sponge.getServer().getConsole();
		try {
			config = loader.load();

			if (!defaultConfig.toFile().exists()) {
				config.getNode("teamplasma").setValue(0);
				config.getNode("teammagma").setValue(0);
				config.getNode("teamaqua").setValue(0);
				config.getNode("teamrocket").setValue(0);
				// Separator
				config.getNode("messages", "teamchats", TeamTypes.aqua, "teamchat")
						.setValue("&f[&3Aqua&f] %player%&3:&9 %message%");
				config.getNode("messages", "teamchats", TeamTypes.plasma, "teamchat")
						.setValue("&f[&2Plasma&f] %player%&2: &a%message%");
				config.getNode("messages", "teamchats", TeamTypes.magma, "teamchat")
						.setValue("&f[&4Magma&f] %player%&4: &c%message%");
				config.getNode("messages", "teamchats", TeamTypes.rocket, "teamchat")
						.setValue("&f[&8Rocket&f] %player%&8: &7%message%");
				// Separator
				config.getNode("messages", "teamchats", TeamTypes.aqua, "teamchatspy")
						.setValue("&f[&dTeamSpy&f] &f[&3Aqua&f] %player%&d: &d%message%");
				config.getNode("messages", "teamchats", TeamTypes.plasma, "teamchatspy")
						.setValue("&f[&dTeamSpy&f] &f[&2Plasma&f] %player%&d: &d%message%");
				config.getNode("messages", "teamchats", TeamTypes.magma, "teamchatspy")
						.setValue("&f[&dTeamSpy&f] &f[&4Magma&f] %player%&d: &d%message%");
				config.getNode("messages", "teamchats", TeamTypes.rocket, "teamchatspy")
						.setValue("&f[&dTeamSpy&f] &f[&8Rocket&f] %player%&d: &d%message%");
				// Separator
				config.getNode("messages", "playermessages").setValue("&7&m-----------------------------------------------------\n\n&a                             &l&nCongratulations!\n\n&a     Your team amassed the most amount of wins last month!\n\n&a                        Your reward is %reward%\n\n&7&m-----------------------------------------------------").setComment("The message that is displayed when a player logs on if there team won");
				//
				List<String> rewardmessage = new ArrayList<String>();
				rewardmessage.add("%number% shiny key(s)");
				rewardmessage.add("%number% master key(s)");
				rewardmessage.add("%number% ultra key(s)");
				rewardmessage.add("%number% great key(s)");
				rewardmessage.add("%number% master ball(s)");
				config.getNode("messages", "rewardmessages").setValue(rewardmessage).setComment("The positioning of these strings correlates to the commands list. %player% returns the players name and %number% is a random number between 1-3 matches with the reward command %number%");
				
				List<String> rewards = new ArrayList<String>();
				rewards.add("cratekey give %player% shiny %number%");
				rewards.add("cratekey give %player% master %number%");
				rewards.add("cratekey give %player% ultra %number%");
				rewards.add("cratekey give %player% great %number%");
				rewards.add("give %player% pixelmon:master_ball %number%");
				config.getNode("messages", "rewardcommands").setValue(rewards).setComment("Put this here so the reward messages and the reward commands are together. %player% returns the players name and %number% returns a number between 1-3 matches with the reward messages %number%");
				//
				config.getNode("players", "Server", "name").setValue(console.getIdentifier().toString());
				loader.save(config);

			}
		} catch (IOException e) {
			this.getLogger().warn("Error loading default configuration!");
		}

	}

	@SuppressWarnings({ "serial", "rawtypes" })
	@Listener
	public void onInitialize(GameInitializationEvent event) throws IOException {

		this.getLogger().info("Loading...");
		Sponge.getEventManager().registerListeners(this, new GameStart(this));
		if (config.getNode("messages").isVirtual()){
			CommentedConfigurationNode teamchats = config.getNode("messages", "teamchats");
			CommentedConfigurationNode rewards = config.getNode("messages");
			teamchats.getNode(TeamTypes.aqua, "teamchat").setValue("&f[&3Aqua&f] %player%&3:&9 %message%");
			teamchats.getNode(TeamTypes.plasma, "teamchat").setValue("&f[&2Plasma&f] %player%&2: &a%message%");
			teamchats.getNode(TeamTypes.magma, "teamchat").setValue("&f[&4Magma&f] %player%&4: &c%message%");
			teamchats.getNode(TeamTypes.rocket, "teamchat").setValue("&f[&8Rocket&f] %player%&8: &7%message%");
			//
			teamchats.getNode(TeamTypes.aqua, "teamchatspy").setValue("&f[&dTeamSpy&f] &f[&3Aqua&f] %player%&d: &d%message%");
			teamchats.getNode(TeamTypes.plasma, "teamchatspy").setValue("&f[&dTeamSpy&f] &f[&2Plasma&f] %player%&d: &d%message%");
			teamchats.getNode(TeamTypes.magma, "teamchatspy").setValue("&f[&dTeamSpy&f] &f[&4Magma&f] %player%&d: &d%message%");
			teamchats.getNode(TeamTypes.rocket, "teamchatspy").setValue("&f[&dTeamSpy&f] &f[&8Rocket&f] %player%&d: &d%message%");
			//
			rewards.getNode("playermessage").setValue("&7&m-----------------------------------------------------\n\n&a                             &l&nCongratulations!\n\n&a     Your team amassed the most amount of wins last month!\n\n&a                      Your reward is %reward%\n\n&7&m-----------------------------------------------------").setComment("The message that is displayed when a player logs on if there team won");
			//
			List<String> rewards2 = new ArrayList<String>();
			rewards2.add("cratekey give %player% shiny %number%");
			rewards2.add("cratekey give %player% master %number%");
			rewards2.add("cratekey give %player% ultra %number%");
			rewards2.add("cratekey give %player% great %number%");
			rewards2.add("give %player% pixelmon:master_ball %number%");
			config.getNode("messages", "rewardcommands").setValue(rewards2).setComment("Put this here so the reward messages and the reward commands are together. %player% returns the players name and %number% returns a number between 1-3 matches with the reward messages %number%");
			//
			List<String> rewardmessage = new ArrayList<String>();
			rewardmessage.add("%number% shiny key(s)");
			rewardmessage.add("%number% master key(s)");
			rewardmessage.add("%number% ultra key(s)");
			rewardmessage.add("%number% great key(s)");
			rewardmessage.add("%number% master ball(s)");
			config.getNode("messages", "rewardmessages").setValue(rewardmessage).setComment("The positioning of these strings correlates to the commands list. %player% returns the players name and %number% is a random number between 1-3 matches with the reward command %number%");
			
			this.save();
		}
		
		
		
		
		Map arg1 = new HashMap<String, String>() {
			{
				put("magma", "magma");
				put("plasma", "plasma");
				put("rocket", "rocket");
				put("aqua", "aqua");
			}
		};

		Map arg2 = new HashMap<String, String>() {
			{
				put("magma", "magma");
				put("plasma", "plasma");
				put("rocket", "rocket");
				put("aqua", "aqua");
				put("banned", "banned");
			}
		};
		CommandSpec pointsRemove = CommandSpec.builder().permission("team.admin")
				.arguments(GenericArguments.onlyOne(GenericArguments.choices(Text.of("team"), arg1)),
						GenericArguments.integer(Text.of("#")))
				.executor((CommandExecutor) new admRemove(this)).build();

		CommandSpec pointsAdd = CommandSpec.builder().permission("team.admin")
				.arguments(GenericArguments.onlyOne(GenericArguments.choices(Text.of("team"), arg1)),
						GenericArguments.integer(Text.of("#")))
				.executor((CommandExecutor) new admAdd(this)).build();
		
		CommandSpec reload = CommandSpec.builder().permission("team.admin")
				.executor((CommandExecutor) new reload(this)).build();
		
		CommandSpec test = CommandSpec.builder().permission("team.admin")
				.executor((CommandExecutor) new test(this)).build();

		CommandSpec points = CommandSpec.builder().permission("team.admin").child(pointsAdd, "add")
				.child(pointsRemove, "remove").executor((CommandExecutor) new points()).build();

		CommandSpec teamSet = CommandSpec.builder().permission("team.admin")
				.arguments(GenericArguments.onlyOne(GenericArguments.choices(Text.of("team"), arg2)),
						GenericArguments.player(Text.of("player")))
				.executor((CommandExecutor) new setTeam(this)).build();

		CommandSpec Teams = CommandSpec.builder().child(teamSet, "set").permission("team.admin")
				.executor((CommandExecutor) new Teams()).build();

		CommandSpec admteam = CommandSpec.builder().child(test, "test").child(reload, "reload").child(points, "points").child(Teams, "team")
				.permission("team.admin").executor((CommandExecutor) new admTeam()).build();
		Sponge.getCommandManager().register(this, admteam, Lists.newArrayList("admteam"));

		// Team Commands

		CommandSpec teamSelect = CommandSpec.builder()
				.arguments(GenericArguments.onlyOne(GenericArguments.choices(Text.of("teams"), arg1)))
				.executor((CommandExecutor) new me.pokerman99.PokeTeamsV2.commands.teamSelect(this)).build();

		CommandSpec leaderboard = CommandSpec.builder().arguments(GenericArguments.none())
				.executor((CommandExecutor) new me.pokerman99.PokeTeamsV2.commands.leaderboard()).build();

		CommandSpec teamChat = CommandSpec.builder()
				.arguments(GenericArguments.remainingJoinedStrings(Text.of("message")))
				.executor((CommandExecutor) new teamChat(this)).build();

		Sponge.getCommandManager().register(this, teamChat, Lists.newArrayList("tc"));

		CommandSpec members = CommandSpec.builder().arguments(GenericArguments.none())
				.executor((CommandExecutor) new me.pokerman99.PokeTeamsV2.commands.members(this)).build();

		CommandSpec team = CommandSpec.builder().child(members, "members").child(leaderboard, "leaderboard", "lb")
				.child(teamSelect, "select").executor((CommandExecutor) new team()).build();

		Sponge.getCommandManager().register(this, team, Lists.newArrayList("team"));

		// Team Ban

		CommandSpec teamBan = CommandSpec.builder()
				.arguments(GenericArguments.onlyOne(GenericArguments.player(Text.of("player"))))
				.executor(new teamBan(this)).permission("team.staff").build();
		Sponge.getCommandManager().register(this, teamBan, "teamban");

		Sponge.getEventManager().registerListeners(this, new ConnectionListener(this));
		Sponge.getEventManager().registerListeners(this, new muteEvent(this));

		Pixelmon.EVENT_BUS.register(new battleEvent(this));
		Pixelmon.EVENT_BUS.register(this);

		this.getLogger().info("Enabled.");

	}

	@Listener
	public void onShutDown(GameStoppingServerEvent e) throws IOException {
		save();
	}

	/*
	 * @Listener public void onCommand(final SendCommandEvent e, @Root final
	 * CommandSource sender) throws IOException, InterruptedException,
	 * NumberFormatException {
	 * 
	 * String args = e.getArguments(); String command = e.getCommand(); if
	 * (command.equals("test")) { final Task.Builder taskBuilder =
	 * Sponge.getGame().getScheduler().createTaskBuilder();
	 * taskBuilder.delayTicks(20); taskBuilder.execute(() -> {
	 * MessageChannel.TO_ALL.send(Text.of("Test 1")); }); }
	 * 
	 * }
	 */

	@Listener
	public void onReload(GameReloadEvent e) throws IOException {
		loader.save(config);
	}

	public static String color(String string) {
		return org.spongepowered.api.text.serializer.TextSerializers.FORMATTING_CODE
				.serialize(org.spongepowered.api.text.Text.of(string));
	}

	public static void sendMessage(CommandSource sender, String message) {
		if (sender == null)
			return;
		sender.sendMessage(
				org.spongepowered.api.text.serializer.TextSerializers.FORMATTING_CODE.deserialize(color(message)));
	};

	public Path getConfigDir() {
		return ConfigDir;
	}

}