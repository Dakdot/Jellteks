package net.maxipan.guild;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TaxManager {

	private static TaxManager instance = new TaxManager();

	public static TaxManager getInstance() {
		return instance;
	}

	public void tax() {
		new BukkitRunnable() {
			public void run() {
				Calendar cal = Calendar.getInstance();
				cal.getTime();
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				String time = sdf.format(cal.getTime());
				String[] times = time.split(":");

				int hour = Integer.parseInt(times[0]);
				int minute = Integer.parseInt(times[1]);
				int second = Integer.parseInt(times[2]);

				if (hour == Main.plugin.config.getInt("guildTaxes.collection.hour")
						&& minute == Main.plugin.config.getInt("guildTaxes.collection.minute") && second == 00) {
					Main.plugin.log.info("Now collecting money from players as tax.");
					Main.plugin.log.info("The time is: " + time);
					
					collectTaxes();
				}
			}
		}.runTaskTimer(Main.plugin, 0L, 1200L);
	}
	
	int amountPlayersInGuild;
	String[] playersInGuild;
	
	public void collectTaxes() {
		
	}

}
