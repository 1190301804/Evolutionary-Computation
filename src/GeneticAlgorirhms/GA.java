package GeneticAlgorirhms;

import Representation.Individual;
import Representation.Population;

public interface GA {
	public Individual start(Population citylist);
	public void createBeginningSpecies(Population citylist);
	
}
