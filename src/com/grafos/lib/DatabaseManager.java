package com.grafos.lib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.grafos.model.Configuration;

public class DatabaseManager {

	String STANDART_LOCAL_FILE = System.getProperty("user.home");
	File FILECONFIG = new File(STANDART_LOCAL_FILE + "/.menorcaminhoapp");

	public void createConfig(Configuration config) {

		BufferedWriter buffWrite;
		try {
			buffWrite = new BufferedWriter(new FileWriter(FILECONFIG));
			String line = "";

			line += config.getRootFolder() + "\n";
			line += config.getSuccessFolder() + "\n";
			line += config.getErrorFolder() + "\n";
			line += config.getAutomatic() + "\n";

			// criar integridade dos arquivos
			line += createHash(config);

			createConfigurationsFolders(config);
			buffWrite.append(line + "\n");
			buffWrite.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	// create all folders (using other function)
	private void createConfigurationsFolders(Configuration config) {
		createSingleFolder(config.getRootFolder());
		createSingleFolder(config.getRootFolder() + "//" + config.getErrorFolder());
		createSingleFolder(config.getRootFolder() + "//" + config.getSuccessFolder());

	}

	// create a fodler
	private void createSingleFolder(String path) {
		File folder = new File(path);

		if (!folder.exists()) {
			if (folder.mkdirs()) {
				System.out.println("Multiple directories are created!");
			} else {
				System.out.println("Failed to create multiple directories!");
			}
		}

	}

	public Configuration getConfig() {

		BufferedReader buffRead;
		try {
			buffRead = new BufferedReader(new FileReader(FILECONFIG));
			String line = "";
			line = buffRead.readLine();
			String[] configs = new String[10];
			int i = 0;
			while (line != null) {

				if (i > 4) {
					buffRead.close();
					throw new Exception("Arquivo de configuração está em formato errado.");
				}
				configs[i] = line;
				line = buffRead.readLine();
				i++;

			}

			if (i != 5) {
				buffRead.close();
				throw new Exception("Arquivo de configuração incompleto.");
			} else {

				Configuration c = new Configuration(configs[0], configs[1], configs[2], configs[3].charAt(0) == 't');

				if (!createHash(c).equals(configs[4])) {
					buffRead.close();
					throw new Exception("Arquivo de configuração com integridade comprometida.");
				} else {
					buffRead.close();
					return c;
				}
			}

		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}

		return null;
	}

	public boolean hasConfig() {
		return FILECONFIG.exists();
	}

	// gera um hash em md5 com as configuracoes
	public String createHash(Configuration config) {
		try {
			String s = config.getRootFolder() + config.getSuccessFolder() + config.getErrorFolder() + config.getAutomatic()
					+ "NERF PROTOSS";

			MessageDigest m;
			m = MessageDigest.getInstance("MD5");
			m.update(s.getBytes(), 0, s.length());
			return new BigInteger(1, m.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

}
