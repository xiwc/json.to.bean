package com.emc.vsi.json2Bean;

import javax.swing.JOptionPane;

public class StringToObjectMain {
	public static void main(String[] args) {
		/**
		<fx:String>12:00 AM</fx:String>
		<fx:String>12:15 AM</fx:String>
		<fx:String>12:30 AM</fx:String>
		<fx:String>12:45 AM</fx:String>
		
		<fx:String>01:00 AM</fx:String>
		<fx:String>01:15 AM</fx:String>
		<fx:String>01:30 AM</fx:String>
		<fx:String>01:45 AM</fx:String>
		
		<fx:String>02:00 AM</fx:String>
		<fx:String>02:15 AM</fx:String>
		<fx:String>02:30 AM</fx:String>
		<fx:String>02:45 AM</fx:String>
		
		<fx:String>03:00 AM</fx:String>
		<fx:String>03:15 AM</fx:String>
		<fx:String>03:30 AM</fx:String>
		<fx:String>03:45 AM</fx:String>
		
		<fx:String>04:00 AM</fx:String>
		<fx:String>04:15 AM</fx:String>
		<fx:String>04:30 AM</fx:String>
		<fx:String>04:45 AM</fx:String>
		
		<fx:String>05:00 AM</fx:String>
		<fx:String>05:15 AM</fx:String>
		<fx:String>05:30 AM</fx:String>
		<fx:String>05:45 AM</fx:String>
		
		<fx:String>06:00 AM</fx:String>
		<fx:String>06:15 AM</fx:String>
		<fx:String>06:30 AM</fx:String>
		<fx:String>06:45 AM</fx:String>
		
		<fx:String>07:00 AM</fx:String>
		<fx:String>07:15 AM</fx:String>
		<fx:String>07:30 AM</fx:String>
		<fx:String>07:45 AM</fx:String>
		
		<fx:String>08:00 AM</fx:String>
		<fx:String>08:15 AM</fx:String>
		<fx:String>08:30 AM</fx:String>
		<fx:String>08:45 AM</fx:String>
		
		<fx:String>09:00 AM</fx:String>
		<fx:String>09:15 AM</fx:String>
		<fx:String>09:30 AM</fx:String>
		<fx:String>09:45 AM</fx:String>
		
		<fx:String>10:00 AM</fx:String>
		<fx:String>10:15 AM</fx:String>
		<fx:String>10:30 AM</fx:String>
		<fx:String>10:45 AM</fx:String>
		
		<fx:String>11:00 AM</fx:String>
		<fx:String>11:15 AM</fx:String>
		<fx:String>11:30 AM</fx:String>
		<fx:String>11:45 AM</fx:String>
		
		<fx:String>12:00 PM</fx:String>
		<fx:String>12:15 PM</fx:String>
		<fx:String>12:30 PM</fx:String>
		<fx:String>12:45 PM</fx:String>
		
		<fx:String>01:00 PM</fx:String>
		<fx:String>01:15 PM</fx:String>
		<fx:String>01:30 PM</fx:String>
		<fx:String>01:45 PM</fx:String>
		
		<fx:String>02:00 PM</fx:String>
		<fx:String>02:15 PM</fx:String>
		<fx:String>02:30 PM</fx:String>
		<fx:String>02:45 PM</fx:String>
		
		<fx:String>03:00 PM</fx:String>
		<fx:String>03:15 PM</fx:String>
		<fx:String>03:30 PM</fx:String>
		<fx:String>03:45 PM</fx:String>
		
		<fx:String>04:00 PM</fx:String>
		<fx:String>04:15 PM</fx:String>
		<fx:String>04:30 PM</fx:String>
		<fx:String>04:45 PM</fx:String>
		
		<fx:String>05:00 PM</fx:String>
		<fx:String>05:15 PM</fx:String>
		<fx:String>05:30 PM</fx:String>
		<fx:String>05:45 PM</fx:String>
		
		<fx:String>06:00 PM</fx:String>
		<fx:String>06:15 PM</fx:String>
		<fx:String>06:30 PM</fx:String>
		<fx:String>06:45 PM</fx:String>
		
		<fx:String>07:00 PM</fx:String>
		<fx:String>07:15 PM</fx:String>
		<fx:String>07:30 PM</fx:String>
		<fx:String>07:45 PM</fx:String>
		
		<fx:String>08:00 PM</fx:String>
		<fx:String>08:15 PM</fx:String>
		<fx:String>08:30 PM</fx:String>
		<fx:String>08:45 PM</fx:String>
		
		<fx:String>09:00 PM</fx:String>
		<fx:String>09:15 PM</fx:String>
		<fx:String>09:30 PM</fx:String>
		<fx:String>09:45 PM</fx:String>
		
		<fx:String>10:00 PM</fx:String>
		<fx:String>10:15 PM</fx:String>
		<fx:String>10:30 PM</fx:String>
		<fx:String>10:45 PM</fx:String>
		
		<fx:String>11:00 PM</fx:String>
		<fx:String>11:15 PM</fx:String>
		<fx:String>11:30 PM</fx:String>
		<fx:String>11:45 PM</fx:String>
		**/

		String s = JOptionPane.showInputDialog("Input:");

		String[] arr = s.split("\t");

		for (String line : arr) {
			int b = line.indexOf(">");
			int e = line.indexOf("</");

			if (b == -1 || e == -1) {
				continue;
			}

			String ss = line.substring(b + 1, e);

			// System.out.println(ss);

			// <fx:Object label='' value=''/>
			String t = "<fx:Object label='{label}' value='{value}'/>";

			if (ss.contains("AM")) {
				if (ss.startsWith("12:")) {
					System.out.println(t.replace("{label}", ss).replace("{value}",
							ss.replace(" AM", "").replace("12:", "0:")));
				} else {
					System.out.println(t.replace("{label}", ss).replace("{value}", ss.replace(" AM", "")));
				}
			} else {
				int h = Integer.parseInt(ss.split(":")[0]) + 12;
				String v = h + ":" + ss.split(":")[1];
				System.out.println(t.replace("{label}", ss).replace("{value}", v.replace(" PM", "")));
			}
		}
	}
}
