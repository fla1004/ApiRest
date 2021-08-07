package util;

import util.ConfigEnvironment;

public class ConfigAPI {

    public static final String CREATE_PROJECT= util.ConfigEnvironment.host+"/api/items.json";
    public static final String UPDATE_PROJECT= util.ConfigEnvironment.host+"/api/items/ID.json";
    public static final String READ_PROJECT= util.ConfigEnvironment.host+"/api/items/ID.json";
    public static final String DELETE_PROJECT= ConfigEnvironment.host+"/api/items/ID.json";
}
