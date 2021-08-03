package Select;

import TSPMain.Individual;
import TSPMain.Population;

public interface selectOperator {
	public Individual select(Population list);
}
