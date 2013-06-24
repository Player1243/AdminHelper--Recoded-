package de.YonasCode.executor;

import org.bukkit.command.CommandExecutor;

import de.YonasCode.AdminHelper.Main;

public abstract class CommandsManager implements CommandExecutor{

	  public static void registerCommands() {
		  CommandGlobalmute globalmute = new CommandGlobalmute();
		  CommandClearchat clearchat = new CommandClearchat();
		  CommandClearchatPrivate clearchatprivate = new CommandClearchatPrivate();
		  CommandRam ram = new CommandRam();
		  CommandGamemodeall gamemodeall = new CommandGamemodeall();
		  CommandGiveall giveall = new CommandGiveall();
		  CommandAdminHelper adminhelper = new CommandAdminHelper();
		  CommandKickall kickall = new CommandKickall();

		  Main.INSTANCE.getCommand("globalmute").setExecutor(globalmute);
		  Main.INSTANCE.getCommand("clearchat").setExecutor(clearchat);
		  Main.INSTANCE.getCommand("pcc").setExecutor(clearchatprivate);
		  Main.INSTANCE.getCommand("ram").setExecutor(ram);
		  Main.INSTANCE.getCommand("gamemodeall").setExecutor(gamemodeall);
		  Main.INSTANCE.getCommand("giveall").setExecutor(giveall);
		  Main.INSTANCE.getCommand("adminhelper").setExecutor(adminhelper);
		  Main.INSTANCE.getCommand("kickall").setExecutor(kickall);
	  }
	
}
