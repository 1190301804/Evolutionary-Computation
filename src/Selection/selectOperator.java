package Selection;

import Representation.Individual;
import Representation.Population;

public interface selectOperator {
	public Individual select(Population list);
}
