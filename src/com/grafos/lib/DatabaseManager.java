package com.grafos.lib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.grafos.model.Configuration;

public class DatabaseManager {
	
	File FILECONFIG = new File(System.getProperty("user.home")+"/menorcaminhoconfig.txt");
	
	public void createConfig(Configuration config) {
		
		
    	BufferedWriter buffWrite;
		try {
			buffWrite = new BufferedWriter(new FileWriter(FILECONFIG));
			String line = "";
	        
	        line += config.getFolder() + "\n";
	        line += config.getSucess() + "\n";
	        line += config.getError() + "\n";
	        line += config.getAutomatic() + "\n";

	        //criar integridade dos arquivos
	        line += createHash(config);
			
	    	buffWrite.append(line + "\n");
	        buffWrite.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
        
	}
	
	public Configuration getConfig() throws IOException {
		
    	BufferedReader buffRead;
		try {
			buffRead = new BufferedReader(new FileReader(FILECONFIG));
			String line = "";
			line = buffRead.readLine();
			String[] configs = new String[10];
			int i=0;
			while (line != null) {
				
				if(i > 4) {
			    	throw new Exception("Arquivo de configuração está em formato errado.");
			    }
				configs[i] = line;
			    line = buffRead.readLine();
			    i++;
			    
			}

			
			System.out.println(i);
			if(i != 5) {
				buffRead.close();
				throw new Exception("Arquivo de configuração incompleto.");
			}
			else {
				
				Configuration c = new Configuration(configs[0],configs[1],configs[2],configs[3].charAt(0) == 't');
				
				if(!createHash(c).equals(configs[4])) {
					buffRead.close();
					throw new Exception("Arquivo de configuração com integridade comprometida.");
				}
				else {
					buffRead.close();
					return c;
				}
			}
			
		} catch (Exception e) {
			System.out.println("ERRO: "+e.getMessage());
		}
		
		return null;
	}
	
	//gera um hash em md5 com as configuracoes
	public String createHash(Configuration config) {
		try {
			String s= config.getFolder()
					+ config.getSucess()
					+ config.getError()
					+ config.getAutomatic()
					+ "NERF PROTOSS";
			
	        MessageDigest m;
			m = MessageDigest.getInstance("MD5");
			m.update(s.getBytes(),0,s.length());
	        return new BigInteger(1,m.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
}
