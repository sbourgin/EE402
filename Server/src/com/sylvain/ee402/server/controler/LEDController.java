package com.sylvain.ee402.server.controler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.sylvain.ee402.common.model.Importance;

/**
 * Led controller, allow to blink or stop a Led with generic methods
 * @author sylvain
 *
 */
public class LEDController {

	private static String LED0_PATH = "/sys/class/leds/beaglebone:green:usr0";
	private static String LED1_PATH = "/sys/class/leds/beaglebone:green:usr1";
	private static String LED2_PATH = "/sys/class/leds/beaglebone:green:usr2";
	private static String LED3_PATH = "/sys/class/leds/beaglebone:green:usr3";
	
	
	
	public void startFlashLed(int parLedId, Importance parImportance) throws IOException {
		
		if(parLedId >3) {
			//The device has only 4 leds
			return;
		}
		
		String locLedPath = findLedPath(parLedId);

		
		BufferedWriter bw = new BufferedWriter ( new FileWriter (locLedPath+"/trigger"));
          bw.write("timer");
          bw.close();
          
          BufferedWriter delayOff = new BufferedWriter ( new FileWriter (locLedPath+"/delay_off"));
          BufferedWriter delayOn = new BufferedWriter ( new FileWriter (locLedPath+"/delay_on"));
          
          if(parImportance.equals(Importance.HIGH)) {
        	  delayOff.write(10);
        	  delayOn.write(10);
          } else if(parImportance.equals(Importance.MEDIUM)) {
        	  delayOff.write(25);
        	  delayOn.write(25);
          } else if(parImportance.equals(Importance.LOW)) {
        	  delayOff.write(50);
        	  delayOn.write(50);
          }  
          
          delayOff.close();
          delayOn.close();
	}
	
	public void stopFlashLed(int parLedId) throws IOException {
		
		if(parLedId >3) {
			//The device has only 4 leds
			return;
		}
		String locLedPath = findLedPath(parLedId);
		
		BufferedWriter bw = new BufferedWriter ( new FileWriter (locLedPath+"/trigger"));
        bw.write("none");
        bw.close();
        bw = new BufferedWriter ( new FileWriter (locLedPath+"/brightness"));
        bw.write(0);
        bw.close();
		
	}
	
	private String findLedPath(int parLedId) {
		
		String locLedPath = "";
		
		if(parLedId==0) {
			locLedPath = LED0_PATH;
		} else if (parLedId==1) {
			locLedPath = LED1_PATH;
		} else if (parLedId==2) {
			locLedPath = LED2_PATH;
		} else if (parLedId == 3) {
			locLedPath = LED3_PATH;
		}
		
		return locLedPath;
	}
	
}
