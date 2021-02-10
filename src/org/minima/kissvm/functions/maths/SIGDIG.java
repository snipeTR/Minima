package org.minima.kissvm.functions.maths;

import org.minima.kissvm.Contract;
import org.minima.kissvm.exceptions.ExecutionException;
import org.minima.kissvm.functions.MinimaFunction;
import org.minima.kissvm.values.NumberValue;
import org.minima.kissvm.values.Value;
import org.minima.objects.base.MiniNumber;

public class SIGDIG extends MinimaFunction {

	public SIGDIG() {
		super("SIGDIG");
	}
	
	@Override
	public Value runFunction(Contract zContract) throws ExecutionException {
		checkExactParamNumber(2);
		
		NumberValue significantdigits 	= zContract.getNumberParam(0, this);
		NumberValue number 				= zContract.getNumberParam(1, this);
		
		MiniNumber actnum = significantdigits.getNumber();
		if(!actnum.floor().isEqual(actnum)) {
			throw new ExecutionException("SIGDIG precision must be to a whole Number");
		}
		
		return new NumberValue(number.getNumber().setSignificantDigits(significantdigits.getNumber().getAsInt()));
	}
	
	@Override
	public MinimaFunction getNewFunction() {
		return new SIGDIG();
	}
}