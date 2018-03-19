package me.chisdealhd.customplugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.simple.JSONObject;

import util.Json_Util;

public class Hypixel {
	  
	public void main(String[] args) throws IOException {
		  String[] array = new String[]{"https://chisdealhd.xyz/radio/np.php","Test"};
		    JSONObject json = Json_Util.Json_Util(array);
		    System.out.println(json.toString());
		    System.out.println(json.get("radioname"));
		  }
}
