package com.hp.java.core.classloader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FooClassLoader extends ClassLoader{
	
	public FooClassLoader(ClassLoader parentLoader) {
		super(parentLoader);
	}
	
	private byte[] readBytesFromURL(URL url) {
		
        URLConnection connection     = null;
        ByteArrayOutputStream buffer = null;
        byte[]			classData	 = null;
        InputStream 	input		 = null;
		try {
			connection = url.openConnection();
			input = connection.getInputStream();
			buffer = new ByteArrayOutputStream();
			int data = input.read();

			while(data != -1){
				buffer.write(data);
				data = input.read();
			}
			input.close();
			classData = buffer.toByteArray(); 
		}
		catch(IOException ioe){			
			ioe.printStackTrace();
		}finally{
			//nothing
		}

        return classData;
	}
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		
		if(!name.equals("com.hp.java.core.classloader.FooClass")){
			System.out.println("Trying to load class "+name+" with parent loader");
			return super.loadClass(name);			
		}
		else{
			System.out.println("Custom class loading for class "+ name);
			// Now custom class loading 
	        try {
	            String url = "file:/home/ixsamar/DE/CoreJavaProj/bin/com/hp/java/"
	            				+ "core/classloader/FooClass.class";
	            URL myUrl = new URL(url);
	            byte[] classData = readBytesFromURL(myUrl);
	            return defineClass("com.hp.java.core.classloader.FooClass",classData, 0, 
	            					classData.length);
	
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        }
		}
		return null;
	}
	
}
