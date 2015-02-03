package com.hp.java.core.classloader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FooClassLoader extends ClassLoader{
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		System.out.println("FooClassLoader: "+FooClassLoader.class.getClassLoader());
		// Now custom class loading 
        try {
            String url = "file:/home/ixsamar/DE/"
            		+ "CoreJavaProj/bin/com/hp/java/core/classloader/FooClass.class";
            URL myUrl = new URL(url);
            URLConnection connection = myUrl.openConnection();
            InputStream input = connection.getInputStream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int data = input.read();

            while(data != -1){
                buffer.write(data);
                data = input.read();
            }

            input.close();

            byte[] classData = buffer.toByteArray();

            return defineClass("com.hp.java.core.classloader.FooClass",classData, 0, 
            					classData.length);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
	}
	
}
