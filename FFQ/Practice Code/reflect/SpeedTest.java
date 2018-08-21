package reflect;

import java.lang.reflect.*;
import java.util.HashMap;

public class SpeedTest{
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		DummyClass dc = new DummyClass();
		int num;
		
		for (int i = 0 ; i < 10 ; i++) {
		    num = 0;
		    long start1 = System.nanoTime();
		    while(num < 1000000)
		    	num += dc.returnOne();
		    
		    long end1 = System.nanoTime();
		    
		    num = 0;
		    
		    long start2 = System.nanoTime();
		    HashMap<String, Method> hm = new HashMap<>();
		    for(Method m : DummyClass.class.getMethods())
		    	hm.put(m.getName(), m);
		    
		    while(num < 1000000)
		    	num += (int) hm.get("returnOne").invoke(dc);
		    
		    long end2 = System.nanoTime();
		    
		    System.out.println(end1 - start1);
		    System.out.println(end2 - start2);
		    System.out.println("\n");
		    try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
