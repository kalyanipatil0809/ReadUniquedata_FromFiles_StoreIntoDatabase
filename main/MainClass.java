package in.sts.excelutility.main;

import org.apache.log4j.BasicConfigurator;

import in.sts.excelutilty.reader.ReadFiles;

public class MainClass {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		
		ReadFiles readfiles = new ReadFiles();
		readfiles.readUniqueData();
	}

}
