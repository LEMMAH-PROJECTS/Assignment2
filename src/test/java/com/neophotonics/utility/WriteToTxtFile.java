package com.neophotonics.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToTxtFile {
	public final static boolean WriteFile(String filepath, String message) throws IOException {
		boolean outrep = false;
		BufferedWriter bw = null;
		FileWriter fileWritter = null;
		String data = message;
		try {
			//System.out.println("Write File - FilePath : " + filepath);
			File f1 = new File(filepath);

			if (!f1.exists()) {
				System.out.println("File ... :" + filepath + " not exists creating new file");
				f1.createNewFile();
			}
			fileWritter = new FileWriter(filepath, true);
			//fileWritter = new FileWriter(f1.getName(), true);
			bw = new BufferedWriter(fileWritter);
			bw.write(data);
			bw.write("\r\n");
			bw.flush();
			bw.close();
			outrep = true;
			return outrep;
		}

		/*
		 * java.io.FileWriter SW; try { if (!(new java.io.File(filepath)).isFile()) { SW
		 * = new java.io.FileWriter(filepath); } else { SW = File.AppendText(filepath);
		 * } SW.write(message + System.lineSeparator()); SW.close(); outrep = true; }
		 */
		catch (RuntimeException ex) {
			System.out.println("Cannot open" + filepath + "for writing");
			System.out.println(ex.getMessage());
			return outrep;
		}
		
	}
}
