package com.search;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerLayer {
	
	@PostMapping("/test")
	public void testHandler(@RequestBody List<QuantumData> quantumDatas) throws Exception {
		Map<Integer, Map<Integer, Map<Integer, Float>>> map = new TreeMap<>();
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		for (QuantumData quantumData: quantumDatas) {
			
			if (Util.getMonth(quantumData.getFromDate()) != Util.getMonth(quantumData.getToDate()))
				throw new Exception("Invalid dates");
			
			Date currDate = formatter.parse("24-08-2022");
			
			if (Math.abs(Util.getMonth(currDate) - Util.getMonth(quantumData.getFromDate())) == 1) {
				if (!(Util.getDay(currDate) < 20))
					System.out.println("Can not apply for next month");
			} else if (Math.abs(Util.getMonth(currDate) - Util.getMonth(quantumData.getFromDate())) == 2) {
				if (!(Util.getDay(currDate) < 25))
					System.out.println("Can not apply for second next month");
			} else if (Math.abs(Util.getMonth(currDate) - Util.getMonth(quantumData.getFromDate())) == 3) {
				if (!(Util.getDay(currDate) < 30))
					System.out.println("Can not apply for third next month");
			}
			
			int month = Util.getMonth(quantumData.getFromDate());
			if (!map.containsKey(month))
				map.put(month, new TreeMap<Integer, Map<Integer, Float>>());
			int startDay = Util.getDay(quantumData.getFromDate());
			int endDay = Util.getDay(quantumData.getToDate());
			for (int day = startDay; day <= endDay; day++) {
				if (!map.get(month).containsKey(day))
					map.get(month).put(day, new TreeMap<Integer, Float>());
				int startMinute = Util.getMinute(quantumData.getFromTime());
				int endMinute = Util.getMinute(quantumData.getToTime());
				for (int minute = startMinute; minute <= endMinute; minute++) {
					if (map.get(month).get(day).containsKey(minute)) {
						System.out.println("Data already exist");
						return;
					} else map.get(month).get(day).put(minute, quantumData.getQuantum()); 
				}
			}
		}		
	}
}
