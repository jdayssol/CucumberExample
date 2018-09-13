package com.jdayssol.training;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapTraining {

	List<String> claimNum = Arrays.asList("TL-199800009", "TL-199800008", "TL-199800007", "TL-199800006", "TL-199800005", "TL-199800004", "TL-199800003", "TL-199800002", "TL-1998000010", "TL-199800001");
	
	protected List<String> substringClaimNum()
	{
		List<String> subList = null;
		Stream<String> sp = claimNum.stream();
		subList = sp.map(n -> n.substring(n.length()-5,n.length())).collect(Collectors.toList());
		return  subList;
	}
	
	protected List<Integer> transformToIntegerClaimNum()
	{
		List<Integer> subList = null;
		Stream<String> sp = claimNum.stream();
		subList = sp.map(n -> n.substring(n.length()-5,n.length())).map(n -> Integer.parseInt(n)).collect(Collectors.toList());
		return  subList;
	}
	
	protected Integer sortFirstIntegerClaimNum()
	{
		Optional<Integer> firstInteger = null;
		Stream<String> sp = claimNum.stream();
		firstInteger = sp.map(n -> n.substring(n.length()-5,n.length())).map(n -> Integer.parseInt(n)).sorted().findFirst();
		return  firstInteger.get();
	}
	
	protected Integer sortLastIntegerClaimNum()
	{
		Optional<Integer> lastInteger = null;
		Stream<String> sp = claimNum.stream();
		
		Comparator<Integer> comparatorLast = new Comparator<Integer>() {
	        @Override
	        public int compare(Integer int1, Integer int2) {    
	        	if(int1.intValue() == int2) return 0;
	            if (int1 < int2) return 1;
	            return -1;
	        }
	    };
	    
	    lastInteger = sp.map(n -> n.substring(n.length()-5,n.length())).map(n -> Integer.parseInt(n)).sorted(comparatorLast).findFirst();
		return  lastInteger.get();
	}
	
	protected String transformToExtClaimNum(String reinsurerCodeAndYear,Integer nextClaimnum)
	{
		String result;
		DecimalFormat df = new DecimalFormat("00000");
		result = reinsurerCodeAndYear +df.format(nextClaimnum);
		return result;
	}

	protected String getMaxExtClaimNum()
	{
		Optional<Integer> lastInteger = null;
		Stream<String> sp = claimNum.stream();
		
		Comparator<Integer> comparatorLast = new Comparator<Integer>() {
	        @Override
	        public int compare(Integer int1, Integer int2) {    
	        	if(int1.intValue() == int2) return 0;
	            if (int1 < int2) return 1;
	            return -1;
	        }
	    };
	    
	    lastInteger = sp.map(n -> n.substring(n.length()-5,n.length())).map(n -> Integer.parseInt(n)).sorted(comparatorLast).findFirst();
	    
	    Stream<String> sp2 = claimNum.stream();
	    Optional<String> reinsurerCodeAndYearOptimal = sp2.map(n -> n.substring(0,n.length()-5)).findFirst();	    
	    return transformToExtClaimNum(reinsurerCodeAndYearOptimal.get(),lastInteger.get());
	}
	
	protected String getNextExtClaimNum()
	{
		Optional<Integer> lastInteger = null;
		Stream<String> sp = claimNum.stream();
		
		Comparator<Integer> comparatorLast = new Comparator<Integer>() {
	        @Override
	        public int compare(Integer int1, Integer int2) {    
	        	if(int1.intValue() == int2) return 0;
	            if (int1 < int2) return 1;
	            return -1;
	        }
	    };
	    
	    lastInteger = sp.map(n -> n.substring(n.length()-5,n.length())).map(n -> Integer.parseInt(n)).sorted(comparatorLast).findFirst();
	    
	    Stream<String> sp2 = claimNum.stream();
	    Optional<String> reinsurerCodeAndYearOptimal = sp2.map(n -> n.substring(0,n.length()-5)).findFirst();	    
	    return transformToExtClaimNum(reinsurerCodeAndYearOptimal.get(),lastInteger.get()+1);
	}
	
	
	
}
