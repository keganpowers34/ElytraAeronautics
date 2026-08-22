package com.github.Soulphur0.config.updaters;

import com.github.Soulphur0.config.command.EanCommandHelp;
import com.github.Soulphur0.config.singletons.FlightConfig;
import com.github.Soulphur0.networking.server.EanServerPacketSender;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

/** Updates cloud settings changed via command on the server itself, and sends a custom packet to sync the settings with all clients.<br><br>
 *  Made into a separate class from the config singleton in order to keep things ordered.<br><br>
 * @see FlightConfig
 * @see EanServerPacketSender
 * */
public class FlightConfigUpdater {

    public static void setAltitudeDeterminesSpeed(String value, CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        // _ Print help if the value was -help.
        if (value.equals("-help")){
            context.getSource().sendSystemMessage(EanCommandHelp.setAltitudeDeterminesSpeed());
            return;
        }

        // _ Validate value.
        if(!(value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false"))){
            context.getSource().sendSystemMessage(Component.translatable("command.error.value.boolean").withStyle(ChatFormatting.RED));
            return;
        }

        try{
            boolean altitudeDeterminesSpeed = Boolean.parseBoolean(value);

            // Write new value to current config instance.
            FlightConfig.getOrCreateInstance().setAltitudeDeterminesSpeed(altitudeDeterminesSpeed);

            // Write new value to the server's config file.
            FlightConfig.writeToDisk();

            // Sync settings with all connected clients.
            EanServerPacketSender.syncAllClientsConfigWithServer(context.getSource().getServer());

            // Notify command's source of the changes.
            String message = (altitudeDeterminesSpeed) ? "Altitude now determines elytra flight speed." : "Altitude no longer determines elytra flight speed.";
            context.getSource().sendSystemMessage(Component.nullToEmpty(message));
        } catch (Exception e){
            throw new SimpleCommandExceptionType(Component.translatable("command.error.value")).create();
        }
    }

    public static void setMinSpeed(String value, CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        // _ Print help if the value was -help.
        if (value.equals("-help")){
            context.getSource().sendSystemMessage(EanCommandHelp.setMinSpeed());
            return;
        }

        try{
            double minSpeed = Double.parseDouble(value);

            // Write new value to current config instance.
            FlightConfig.getOrCreateInstance().setMinSpeed(minSpeed);

            // Write new value to the server's config file.
            FlightConfig.writeToDisk();

            // Sync settings with all connected clients.
            EanServerPacketSender.syncAllClientsConfigWithServer(context.getSource().getServer());

            // Notify command's source of the changes.
            String message = "Minimum flight speed is now " + value + "m/s";
            context.getSource().sendSystemMessage(Component.nullToEmpty(message));
        } catch (NumberFormatException e){
            throw new SimpleCommandExceptionType(Component.translatable("command.error.value")).create();
        }
    }

    public static void setMaxSpeed(String value, CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        // _ Print help if the value was -help.
        if (value.equals("-help")){
            context.getSource().sendSystemMessage(EanCommandHelp.setMaxSpeed());
            return;
        }

        try{
            double maxSpeed = Double.parseDouble(value);

            // Write new value to current config instance.
            FlightConfig.getOrCreateInstance().setMaxSpeed(maxSpeed);

            // Write new value to disk, notifying settings change to the server.
            FlightConfig.writeToDisk();

            // Sync settings with all connected clients.
            EanServerPacketSender.syncAllClientsConfigWithServer(context.getSource().getServer());

            // Notify command's source of the changes.
            String message = "Maximum flight speed is now " + value + "m/s";
            context.getSource().sendSystemMessage(Component.nullToEmpty(message));
        } catch (NumberFormatException e){
            throw new SimpleCommandExceptionType(Component.translatable("command.error.value")).create();
        }
    }

    public static void setMinHeight(String value, CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        // _ Print help if the value was -help.
        if (value.equals("-help")){
            context.getSource().sendSystemMessage(EanCommandHelp.setMinHeight());
            return;
        }

        try{
            double minHeight = Double.parseDouble(value);

            // Write new value to current config instance.
            FlightConfig.getOrCreateInstance().setMinHeight(minHeight);

            // Write new value to disk, notifying settings change to the server.
            FlightConfig.writeToDisk();

            // Sync settings with all connected clients.
            EanServerPacketSender.syncAllClientsConfigWithServer(context.getSource().getServer());

            // Notify command's source of the changes.
            String message = "The minimum height at which flight speed increases is now " + value + "m of altitude.";
            context.getSource().sendSystemMessage(Component.nullToEmpty(message));
        } catch (NumberFormatException e){
            throw new SimpleCommandExceptionType(Component.translatable("command.error.value")).create();
        }
    }

    public static void setMaxHeight(String value, CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        // _ Print help if the value was -help.
        if (value.equals("-help")){
            context.getSource().sendSystemMessage(EanCommandHelp.setMaxHeight());
            return;
        }

        try{
            double maxHeight = Double.parseDouble(value);

            // Write new value to current config instance.
            FlightConfig.getOrCreateInstance().setMaxHeight(maxHeight);

            // Write new value to disk, notifying settings change to the server.
            FlightConfig.writeToDisk();

            // Sync settings with all connected clients.
            EanServerPacketSender.syncAllClientsConfigWithServer(context.getSource().getServer());

            // Notify command's source of the changes.
            String message = "The maximum height at which flight speed increases is now " + value + "m of altitude.";
            context.getSource().sendSystemMessage(Component.nullToEmpty(message));
        } catch (NumberFormatException e){
            throw new SimpleCommandExceptionType(Component.translatable("command.error.value")).create();
        }
    }

    public static void setSneakingRealignsPitch(String value, CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        // _ Print help if the value was -help.
        if (value.equals("-help")){
            context.getSource().sendSystemMessage(EanCommandHelp.setSneakingRealignsPitch());
            return;
        }

        // _ Validate value.
        if(!(value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false"))){
            context.getSource().sendSystemMessage(Component.translatable("command.error.value.boolean").withStyle(ChatFormatting.RED));
            return;
        }

        try{
            boolean sneakingRealignsPitch = Boolean.parseBoolean(value);

            // Write new value to current config instance.
            FlightConfig.getOrCreateInstance().setSneakingRealignsPitch(sneakingRealignsPitch);

            // Write new value to disk, notifying settings change to the server.
            FlightConfig.writeToDisk();

            // Sync settings with all connected clients.
            EanServerPacketSender.syncAllClientsConfigWithServer(context.getSource().getServer());

            // Notify command's source of the changes.
            String message = (sneakingRealignsPitch) ? "Sneaking mid flight now realigns flight pitch." : "Sneaking mid flight no longer realigns flight pitch.";
            context.getSource().sendSystemMessage(Component.nullToEmpty(message));
        } catch (Exception e){
            throw new SimpleCommandExceptionType(Component.translatable("command.error.value")).create();
        }
    }

    public static void setRealignAngle(String value, CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        // _ Print help if the value was -help.
        if (value.equals("-help")){
            context.getSource().sendSystemMessage(EanCommandHelp.setRealignAngle());
            return;
        }

        try{
            float realignAngle = Float.parseFloat(value);

            // Write new value to current config instance.
            FlightConfig.getOrCreateInstance().setRealignAngle(realignAngle);

            // Write new value to disk, notifying settings change to the server.
            FlightConfig.writeToDisk();

            // Sync settings with all connected clients.
            EanServerPacketSender.syncAllClientsConfigWithServer(context.getSource().getServer());

            // Notify command's source of the changes.
            String message = "The realign angle is now set to " + value + " degrees.";
            context.getSource().sendSystemMessage(Component.nullToEmpty(message));
        } catch (NumberFormatException e){
            throw new SimpleCommandExceptionType(Component.translatable("command.error.value")).create();
        }
    }

    public static void setRealignRate(String value, CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        // _ Print help if the value was -help.
        if (value.equals("-help")){
            context.getSource().sendSystemMessage(EanCommandHelp.setRealignRate());
            return;
        }

        try{
            float realignRate = Float.parseFloat(value);

            // Write new value to current config instance.
            FlightConfig.getOrCreateInstance().setRealignRate(realignRate);

            // Write new value to disk, notifying settings change to the server.
            FlightConfig.writeToDisk();

            // Sync settings with all connected clients.
            EanServerPacketSender.syncAllClientsConfigWithServer(context.getSource().getServer());

            // Notify command's source of the changes.
            String message =  "The realign rate is now set to " + value + " degrees-per-tick.";
            context.getSource().sendSystemMessage(Component.nullToEmpty(message));
        } catch (NumberFormatException e){
            throw new SimpleCommandExceptionType(Component.translatable("command.error.value")).create();
        }
    }
}
