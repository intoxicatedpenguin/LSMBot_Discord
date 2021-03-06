package commands;

import commands.CustomRole.CustomRole;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import utility.Command;
import utility.CommandContainer;
import utility.GuildContainer;
import utility.GuildHashMap;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Owner on 2/23/2017.
 */
public class JailCommand implements Command{
    @Override
    public boolean called(CommandContainer info) {
        return ((GuildMessageReceivedEvent) info.getEvent()).getMessage().getMentionedUsers().size()>=1;
    }

    @Override
    public void action(CommandContainer info) {
        System.out.println("jail trigger");
        List<User> users = ((GuildMessageReceivedEvent) info.getEvent()).getMessage().getMentionedUsers();
        List<Member> members = new ArrayList<>();
        Guild guild = ((GuildMessageReceivedEvent) info.getEvent()).getGuild();
        GuildContainer container = GuildHashMap.get(guild);
        Role role = CustomRole.jailRole(info);
        System.out.println(role.getName());
        for(User user:users){
            System.out.println("adding jail to " + container.getMemberHashMap().get(user).getEffectiveName());
            guild.getController().addRolesToMember(container.getMemberHashMap().get(user),role).queue();
        }
    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}