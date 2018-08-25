package dna;

//
// FastqRecord contains the defline, sequence, and quality string
// from a record in a fastq file.
//


public class FastqRecord implements DNARecord 
{
	private String			defline;
	private String			sequence;
	private String			quality;
	
	
	//
	// Throws RecordFormatException if defline does not start with @ char.
	//
	FastqRecord(String defline, String sequence, String quality) throws RecordFormatException
	{
		if (defline.charAt(0) != '@')
			throw new RecordFormatException("Wrong 1st char in defline: expected @, saw " + defline);
		
		this.defline = defline;
		this.sequence = sequence;
		this.quality = quality;
	}
	
	
	// 
	// 2 methods that satisfy the interface.
	//

	@Override
	public String getDefline() 
	{
		return defline;
	}


	@Override
	public String getSequence() 
	{
		return sequence;
	}


	//
	// equals() method that checks for deep equality of all 3 instance variables. 
	//
	public boolean equals(Object x)
	{
		FastqRecord that = (FastqRecord)x;
		return this.defline.equals(that.defline)  &&
				this.sequence.equals(that.sequence)  &&
				this.quality.equals(that.quality);
	}
	
	
	//
	// Return true if quality contains at least one '!' char
	// or at least one ‘#’ char.
	//
	public boolean qualityIsLow()
	{
		return quality.indexOf('!') >= 0  ||  quality.indexOf('#') >= 0;
	}
	
	
	//
	// Return the sum of the hash codes of defline, sequence, and quality.
	//
	public int hashCode()
	{
		return defline.hashCode() + sequence.hashCode() + quality.hashCode();
	}
}
