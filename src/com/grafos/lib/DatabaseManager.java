package com.grafos.lib;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.grafos.model.Configuration;

public class DatabaseManager {
	
	public void createConfig(Configuration config) {
		File fileConfig = new File(System.getProperty("user.home")+"/menorcaminhoconfig.txt");
		
    	BufferedWriter buffWrite;
		try {
			buffWrite = new BufferedWriter(new FileWriter(fileConfig));
			String line = "";
	        
	        line += config.getFolder() + "\n";
	        line += config.getSucess() + "\n";
	        line += config.getError() + "\n";
	        //criar integridade dos arquivos
	        line += createHash(config);
			
	    	buffWrite.append(line + "\n");
	        buffWrite.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	//gera um hash em md5 com as configuracoes
	public String createHash(Configuration config) {
		try {
			String s= config.getFolder()+config.getSucess()+config.getError();
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
