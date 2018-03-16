package me.chisdealhd.customplugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class Mixer {
	
	private String channel;
	private URL url;
	private BufferedReader reader;
	private boolean online = false;
	//private String channel2;

	  public Mixer(String channel, String[] args) {
	    this.channel = channel;
	    //this.channel2 = channel2;
	    refresh();
	  }
	  public void refresh() {
		try {
	      this.url = new URL("https://mixer.com/api/v1/channels/"+ this.channel);
	      this.reader = new BufferedReader(new InputStreamReader(this.url.openStream()));
	      if (!this.reader.readLine().contains("\"online\":false"))
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