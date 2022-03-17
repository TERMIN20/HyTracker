package net.hypixel.api;

import net.hypixel.api.HypixelAPI;
import net.hypixel.api.apache.ApacheHttpClient;
import net.hypixel.api.reply.AbstractReply;

import java.util.UUID;
//import java.util.function.BiConsumer;


public class HypixelCommunication {

    public static final HypixelAPI API;

    static {
        String key = System.getProperty("apiKey", "8ec31ca6-3ece-4ce5-afb1-1bc764ef702a"); // arbitrary key, replace with your own to test or use the property
        API = new HypixelAPI(new ApacheHttpClient(UUID.fromString(key)));
    }

    //public static final UUID HYPIXEL = UUID.fromString("f7c77d99-9f15-4a66-a87d-c4a51ef30d19");

}