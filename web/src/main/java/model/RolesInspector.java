package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Freemind on 2016-10-26.
 */
public class RolesInspector {
    private final HashMap<String,List<String>> usersRoles=new HashMap<>();

    public RolesInspector(Map <String,List<String>> usersRoles){
        this.usersRoles.putAll(usersRoles);
    }

    public boolean isUserRequestAccessed(User user, String requestString){
        List<String> userAccessedPaths=usersRoles.get(user.getRole());
        if(userAccessedPaths!=null)
        {
            for(String path:userAccessedPaths){
                if(requestString.matches(path)) return true;
            }


        }
        return false;
    }


    public static RolesInspector getMockRolesInspector(){

        Map<String,List<String>> rules=new HashMap<>();

        rules.put("admin", Arrays.asList(".*"));
        rules.put("user",Arrays.asList("\\/userpages\\/userpage.html"));
        rules.put("guest",Arrays.asList("\\/userpages\\/guestpage.html"));

        return new RolesInspector(rules);




    }


}
