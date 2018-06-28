package streams;
import java.io.*;
 
public class sir_file 
{
	public static void main(String[] args) throws Exception
	{
		File firstFile = new File("D:\\Diamond.mp3");
		File secondFile = new File("D:\\Diamond.mp3");
		
		File firstHalfOutputFile = new File("D:\\firstHalfOutput.mp3");
		File secondHalfOutputFile = new File("D:\\secondHalfOutput.mp3");
		File mergedOutputFile = new File("D:\\mergedOutput.mp3");
		
		FileInputStream firstInputStream = new FileInputStream(firstFile);
		FileInputStream secondInputStream = new FileInputStream(secondFile);
		
		BufferedInputStream firstInputBuffer = new BufferedInputStream(firstInputStream);
		BufferedInputStream secondInputBuffer = new BufferedInputStream(secondInputStream);
		
		FileOutputStream firstOutputStream = new FileOutputStream(firstHalfOutputFile);
		FileOutputStream secondOutputStream = new FileOutputStream(secondHalfOutputFile);
		FileOutputStream mergedOutputStream = new FileOutputStream(mergedOutputFile);
		
		BufferedOutputStream firstOutputBuffer = new BufferedOutputStream(firstOutputStream);
		BufferedOutputStream secondOutputBuffer = new BufferedOutputStream(secondOutputStream);
		
		FileInputStream firstHalfInputStream = new FileInputStream(firstHalfOutputFile);
		FileInputStream secondHalfInputStream = new FileInputStream(secondHalfOutputFile);
		
		SequenceInputStream seqInputStream = new SequenceInputStream(secondHalfInputStream, firstHalfInputStream);
		BufferedOutputStream mergedOutputBuffer = new BufferedOutputStream(mergedOutputStream);
		
		int sizeOfFirstFile = firstInputBuffer.available();
		int sizeOfSecondFile = secondInputBuffer.available();
		
		int counterPosition = sizeOfSecondFile / 2;
		int byteOfFile;
		secondInputBuffer.skip(counterPosition);
		
		while(counterPosition <= sizeOfSecondFile)
		{
			byteOfFile = secondInputBuffer.read();
			secondOutputBuffer.write(byteOfFile);
			counterPosition++;
		}
		secondOutputBuffer.flush();
		counterPosition = 0;
		
		while(counterPosition <= sizeOfFirstFile / 2)
		{
			byteOfFile = firstInputBuffer.read();
			firstOutputBuffer.write(byteOfFile);
			counterPosition++;
		}
		firstOutputBuffer.flush();
		while((byteOfFile = seqInputStream.read()) != -1)
			mergedOutputBuffer.write(byteOfFile);
		
		mergedOutputBuffer.flush();
		System.out.println("Program done");
	}
	
}