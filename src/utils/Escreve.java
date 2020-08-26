package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Ferrolene1
 */
public class Escreve {
    
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static Calendar c = Calendar.getInstance();
    
    
    
     public static String leitor()throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader("Tags_Dint"));
        StringBuilder chave= new StringBuilder();
        String linha="";
        while (true) {
        	
        	
            if (linha != null) {
                chave.append(linha);    
            } else
                break;
           linha =buffRead.readLine();
            
        }
        buffRead.close();
        return chave.toString();
        
    }
 
    public static void escritor(String path,String peso) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path,true));
        Date data= new Date(System.currentTimeMillis());
        c.setTime(data);
        c.add(Calendar.HOUR_OF_DAY,-1);
        data=c.getTime();
        
        String[]matChave=Escreve.leitor().split(" - ");
        
        BigInteger a=null;
        BigInteger b=new BigInteger("1");;  
        if(matChave[0]!=""){
          a=new BigInteger(matChave[0]);
        //x=Integer.parseInt(matChave[0]);
        }
        if(a==null)
            a= new BigInteger("0");
        else
            a=a.add(b);
        
        
        buffWrite.append("\n"+ a + " - "+ peso +" - " + sdf.format(data)   + " - " + "PRFS120");
        buffWrite.newLine();
        buffWrite.close();
    } 
   
}
