package tr.donsuzturk.distance;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DecimalFormat;
import java.util.Collection;

@SuppressWarnings("StatementWithEmptyBody")
public final class Main extends JavaPlugin {

    public void onEnable() {
        getLogger().info("Plugin enabled!");
    }

    public boolean onCommand(final CommandSender commandSender, final Command command, final String label, final String[] args) {
        if (!commandSender.hasPermission("distance.kullanim")) {
            commandSender.sendMessage(ChatColor.DARK_RED + ChatColor.BOLD + "[HATA]" + ChatColor.RED + " Bunu yapmak için izniniz yok!");
            return true;
        }
        if (!(commandSender instanceof Player)) {
            return true;
        }
        final Collection<? extends Player> players = this.getServer().getOnlinePlayers();
        final Player senderPlayer = (Player)commandSender;
        final Location senderLocation = senderPlayer.getLocation();
        senderPlayer.sendMessage(ChatColor.GRAY + "========= "  + ChatColor.GOLD + ChatColor.BOLD + "Uzaklık Listesi" + ChatColor.GRAY + " =========");
        for (final Player player : players) {
            final Location playerLocation = player.getLocation();
            final String playerName = player.getDisplayName();
            final String senderWorld = senderPlayer.getWorld().getName();
            final String playerWorld = player.getWorld().getName();
            if (!senderWorld.equals(playerWorld)) {
            }
            else {
                if (player == senderPlayer) {
                    continue;
                }
                final String playerSenderDistance = new DecimalFormat("#").format(Math.ceil(senderLocation.distance(playerLocation)));
                senderPlayer.sendMessage(ChatColor.YELLOW + playerName + ChatColor.WHITE + " senden " + ChatColor.AQUA + playerSenderDistance + ChatColor.WHITE + " blok uzaklıkta!");
            }
        }
        return true;
    }
}
