package simple.ai;

import java.util.Set;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		MarkovChains mc = new MarkovChains();
		
		mc.readParagraphs(new String[] {
					 "NoSilverBullet.txt",
					 "MythicalManMonth.txt",
					 "Piranha.txt",
					 "AnalysisOfBayesian.txt",
					 "PanoramaWeaving.txt",
					 "TinyData.txt",
					 "IdealHashTrees.txt",
					 "ExperimentalAlgorithms.txt",
					 "EfficientSelectivity.txt"
				});
		mc.print();
		mc.writeNewParagraph();
		
//		mc.readParagraphs("C:\\Users\\ADRYEL.ARIZAGA\\eclipse-workspace\\SimpleAI\\src\\simple\\ai\\simpleFile.txt");
//		mc.readDictionary("C:\\Users\\ADRYEL.ARIZAGA\\Documents\\dictionary.txt");
	}
}
