package au.com.vincentbai;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App {
	
    public static void main( String[] args ){
    	
    	Pattern ipPattern = Pattern.compile("^([0-9]{1,3}\\.){1,3}[0-9]{1,3}");
    	
    	Pattern urlPattern =  Pattern.compile("(?<=GET ).*?(?= HTTP)");
    	
    	ArrayList<String> ipList = new ArrayList<String>();
    	
    	ArrayList<String> urlList = new ArrayList<String>();
    	
    	HashSet<String> ipSet = new HashSet<String>();
    	
    	try{
		   FileInputStream fstream = new FileInputStream("resources/programming-task-example-data.log");
		   BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		   String strLine;

		   while ((strLine = br.readLine()) != null)   {
		     
		     Matcher ipMatcher = ipPattern.matcher(strLine);
		     
		     while (ipMatcher.find()) {
		    	 ipList.add(ipMatcher.group());
		    	 ipSet.add(ipMatcher.group());
		     }
		     
		     Matcher urlMatcher = urlPattern.matcher(strLine);
		     
		     while (urlMatcher.find()) {
		    	 urlList.add(urlMatcher.group());
		     }
		   }
		   
		   fstream.close();
		   
		   
		} catch (Exception e) {
		     System.err.println("Error: " + e.getMessage());
		}
    	
    	Map<String, Long> ipResult = countAndSort(ipList);
    	Map<String, Long> urlResult = countAndSort(urlList);
    	
    	System.out.println("The number of unique IP addresses: " + ipSet.size());
    	System.out.println("The top 3 most visited URLs: " + urlResult.keySet().toArray()[0] + " " + urlResult.keySet().toArray()[1]+ " " +urlResult.keySet().toArray()[2]);
    	System.out.println("The top 3 most active IP addresses: " + ipResult.keySet().toArray()[0] + " " + ipResult.keySet().toArray()[1]+ " " +ipResult.keySet().toArray()[2]);
    }
    
    public static Map<String, Long> countAndSort(ArrayList<String> rowList){
    	
    	Map<String, Long> countMap = rowList.stream().collect(Collectors.groupingBy(p->p, Collectors.counting()));
    	
    	Map<String, Long> resultMap = new LinkedHashMap<>();
    	
    	countMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(e -> resultMap.put(e.getKey(), e.getValue()));
    	
    	return resultMap;
    }
    
}
