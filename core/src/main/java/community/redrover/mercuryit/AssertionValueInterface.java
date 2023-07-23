package community.redrover.mercuryit;


import java.util.function.Consumer;
import java.util.function.Function;


public interface AssertionValueInterface<Self extends MercuryITResponse<Self>, Value> {

    Self getSelf();

    Value getValue();

    default <Result> Result apply(Function<Value, Result> apply) {
        return apply.apply(getValue());
    }

    /**
     * Performs the given function using this class' stored value, then returns the calling class.
     * @param actual Function utilizing the stored value
     * @return self
     */
    default Self peek(Consumer<Value> actual) {
        actual.accept(getValue());
        return getSelf();
    }

    /**
     * Applies the provided function to this class' stored value and passes it to a newly-created AssertionValue.
     * @param actual Function utilizing this class' stored value
     * @return A new AssertionValue that originates from this class and contains the processed value
     * @param <Result> The output type of the provided function
     */
    default <Result> AssertionValue<Self, Result> assertion(Function<Value, Result> actual) {
        return new AssertionValue<>(getSelf(), actual.apply(getValue()));
    }

    default Assertion<Self> assertion() {
        return new Assertion<>(getSelf());
    }
}
