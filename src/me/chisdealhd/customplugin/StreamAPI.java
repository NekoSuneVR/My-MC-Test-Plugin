package me.chisdealhd.customplugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class StreamAPI
{
  private String channel;
  //private String twitchapi;
  //private URL twitchapiurl;
  private URL url;
  private BufferedReader reader;
  private boolean online = false;
  //private String channel2;

  public StreamAPI(String channel, String[] args) {
    this.channel = channel;
    //this.channel2 = channel2;
    refresh();
  }
  public void refresh() {
    try {
      //this.twitchapiurl = new URL("");
      this.url = new URL("https://api.twitch.tv/kraken/streams?channel=" + this.channel+"&client_id=uhfsjnruyn6vsu01yhk346jhccv5v7");
      this.reader = new BufferedReader(new InputStreamReader(this.url.openStream()));
      if (!this.reader.readLine().contains("[]"))
        this.online = true;
      else
        this.online = false;
    }
    catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public URL getUrl() {
    return this.url;
  }

  public boolean isOnline() {
    return this.online;
  }
}
