package dna;

import java.io.*;
import java.util.*;


public class FileConverter 
{
	private File		fastq;
	private File		fasta;
	
	
	public FileConverter(File fastq, File fasta)
	{
		this.fastq = fastq;
		this.fasta = fasta;
	}
	
	
	//
	// Writes a fasta file consisting of conversion of all records from the fastq with
	// sufficient quality and unique defline.
	//
	public void convert() throws IOException
	{
		// Build chain of readers.
		FileReader fr = new FileReader(fastq);
		BufferedReader br = new BufferedReader(fr);
		FastqReader fqr = new FastqReader(br);

		// Build chain of writers.
		FileWriter fw = new FileWriter(fasta);
		PrintWriter pw = new PrintWriter(fw);
		FastaWriter faw = new FastaWriter(pw);
		
		// Read, translate, write.
		boolean done = false;
		while (!done)
		{
			try
			{
				FastqRecord qrec = fqr.readRecord();
				if (qrec == null)
					done = true;
				else
					if (!qrec.qualityIsLow())
					{
						FastaRecord arec = new FastaRecord(qrec);
						faw.writeRecord(arec);
					}
			}
			catch (RecordFormatException x)	{ }		// Do nothing => record doesn't get translated/written
		}
		
		// Close fr, br, fw, and pw in reverse order of creation.
		pw.close();
		fw.close();
		br.close();
		fr.close();
	}
	
		
	
	public static void main(String[] args)
	{
		System.out.println("Starting");
		try
		{
			File fastq = new File("data/HW4.fastq");
			if (!fastq.exists())
			{
				System.out.println("Can't find input file " + fastq.getAbsolutePath());
				System.exit(1);
			}
			File fasta = new File("data/HW4.fasta");
			FileConverter converter = new FileConverter(fastq, fasta);
			converter.convert();
		}
		catch (IOException x)
		{
			System.out.println(x.getMessage());
		}
		System.out.println("Done");
	}
}
