/**
 * 
 */
package com;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wildbean.wastools.core.WasTools;

/**
* @Description: 
* @author Miao
* @date 2016��1��6�� ����11:35:45
*
*/
public class ConversionFile {
	public static void main(String[] args) throws IOException {
		String path = "F:\\games\\�½��ļ���";
		startOne(path);
	}
	
	public static void startOne(String path)  {
		long time = System.currentTimeMillis();
		// �����߳�ת������was�ļ�
		WasTools.main(new String[] { path });
		System.out.println("ת�������ʱ" + (System.currentTimeMillis() - time));
//		Runtime.getRuntime().exec("shutdown -s -t 600");
		System.exit(0);
	}
	
	public static void startThreadRun(String path){
		List<Thread> list = new ArrayList<>();
		Thread toolThread;
		long time = System.currentTimeMillis();
		// �����߳�ת������was�ļ�
		File[] files = new File(path).listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				toolThread = new Thread(new Runnable() {
					public void run() {
						WasTools.main(new String[] { file.getAbsolutePath() });
					}
				}, file.getName());
				toolThread.start();
				list.add(toolThread);
			}
		}

		// �ȴ������߳̽���
		if (list.size() > 0) {
			for (Thread t : list) {
				while (t.isAlive()) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		System.out.println("ת�������ʱ" +formatSecond(((System.currentTimeMillis() - time))));
		System.exit(0);
	}
	
    public static String formatSecond(Object second){  
        String  html="0��";  
        if(second!=null){  
            Double s=(Double) second;  
            String format;  
            Object[] array;  
            Integer hours =(int) (s/(60*60));  
            Integer minutes = (int) (s/60-hours*60);  
            Integer seconds = (int) (s-minutes*60-hours*60*60);  
            if(hours>0){  
                format="%1$,dʱ%2$,d��%3$,d��";  
                array=new Object[]{hours,minutes,seconds};  
            }else if(minutes>0){  
                format="%1$,d��%2$,d��";  
                array=new Object[]{minutes,seconds};  
            }else{  
                format="%1$,d��";  
                array=new Object[]{seconds};  
            }  
            html= String.format(format, array);  
        }  
         
       return html;  
         
   }  
    
    
    public static void rejij(){
    	
    }
	
}
