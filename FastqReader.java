package dna;

import java.io.*;


//
// Reads lines from a BufferedReader and builds a FastqRecord.
//


class FastqReader 
{
	private BufferedReader		theBufferedReader;
	
	
	FastqReader(BufferedReader theBufferedReader)
	{
		this.theBufferedReader = theBufferedReader;
	}
	
	
	// Returns next record in the file, or null if EOF (end-of-file).
	public FastqRecord readRecord() throws IOException, RecordFormatException
	{
		// Read the defline from the BufferedReader. Return null if you read null, 
		// indicating end of file.
		String defline = theBufferedReader.readLine();
		if (defline == null)
			return null;
		
		// Read the next 3 lines from the buffered reader. Construct and return
		// a FastqRecord.
		String seq = theBufferedReader.readLine();
		theBufferedReader.readLine();					// Skip + line
		String qual = theBufferedReader.readLine();
		return new FastqRecord(defline, seq, qual);		// throws RecordFormatException if record is weird
	}
}
