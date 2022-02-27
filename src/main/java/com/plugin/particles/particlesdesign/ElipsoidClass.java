package com.plugin.particles.particlesdesign;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class ElipsoidClass extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void CreateShape(PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_AIR) {
            System.out.println("Debug");
            Location loc = e.getPlayer().getLocation();

            new BukkitRunnable() {
                double u = 0;

                @Override
                public void run() {
                    u += Math.PI/8;
                    for (double v = 0; v <= Math.PI*2; v += Math.PI/8) {
                        double r = 2;
                        double z = 4+2*Math.cos(u);
                        double x = 5*Math.sin(u)*Math.cos(v);
                        double y = 2+Math.sin(u)*Math.sin(v);

                        loc.add(x, y, z);
                        e.getPlayer().spawnParticle(Particle.FLAME, loc, 1, 0, 0, 0, 0);
                        loc.subtract(x, y, z);
                    }
                    if (u >= Math.PI) cancel();
                }
            }.runTaskTimer(this, 0, 1L);




        }

    }

}