package org.ebtic.aco.tsp;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicDouble extends Number implements Comparable<AtomicDouble> {
	private static final long serialVersionUID = 1L;
	private AtomicReference<Double> atomicReference;
	public AtomicDouble(Double doubleValue) {atomicReference = new AtomicReference<Double>(doubleValue);}
	public double doubleValue() {return atomicReference.get().doubleValue();}
	public float floatValue() {return atomicReference.get().floatValue();}
	public int intValue() {return atomicReference.get().intValue();}
	public long longValue() {return atomicReference.get().longValue();}
	public int compareTo(AtomicDouble atomicDouble) {
		return Double.compare(this.doubleValue(), atomicDouble.doubleValue());
	}
	public boolean compareAndSet(double updatedValue) {
		boolean returnFlag = false;
		if (atomicReference.compareAndSet(atomicReference.get(), new Double(updatedValue))) returnFlag = true;
		return returnFlag;
	}
}
