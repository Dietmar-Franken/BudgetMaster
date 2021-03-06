package de.deadlocker8.budgetmasterserver.main;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.ResourceBundle;

import de.deadlocker8.budgetmasterserver.server.SparkServer;
import logger.LogLevel;
import logger.Logger;

public class Main
{
	private static ResourceBundle bundle = ResourceBundle.getBundle("de/deadlocker8/budgetmasterserver/main/", Locale.GERMANY);

	public static void main(String[] args)
	{
		Logger.setLevel(LogLevel.ALL);		
		Logger.appInfo(bundle.getString("app.name"), bundle.getString("version.name"), bundle.getString("version.code"), bundle.getString("version.date"));
		try
		{
			File logFile = new File(Paths.get(SparkServer.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent().toFile() + "/error.log");
			Logger.enableFileOutput(logFile);
		}
		catch(URISyntaxException e1)
		{
			Logger.error(e1);
		}		
		
		if(!Files.exists(Paths.get("settings.json")))
		{
			try
			{
				Files.copy(SparkServer.class.getClassLoader().getResourceAsStream("de/deadlocker8/budgetmasterserver/resources/settings.json"), Paths.get("settings.json"));
			}
			catch(IOException e)
			{
				Logger.error(e);
			}
		}

		Settings settings;
		try
		{
			settings = Utils.loadSettings();
			new SparkServer(settings);
		}
		catch(IOException e)
		{
			Logger.error(e);
		}
	}
}
