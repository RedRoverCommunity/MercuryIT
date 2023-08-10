package community.redrover.mercuryit;

import org.junit.jupiter.api.Assertions;

import java.util.Collection;

/**
 * A wrapper for JUnit assertions.
 * @param <Self> The class that the value originated from
 * @param <Value> Used as the "Expected" value for JUnit assertions
 */
public final class AssertionValue<Self extends MercuryITResponse<Self>, Value> implements AssertionValueInterface<Self, Value> {

    private final Self self;
    private final Value value;

    AssertionValue(Self self, Value value) {
        this.self = self;
        this.value = value;
    }

    public Self getSelf() {
        return self;
    }

    public Value getValue() {
        return value;
    }

    public Self equalsTo(Object expected, String message) {
        Assertions.assertEquals(expected, this.value, message);
        return this.self;
    }

    public Self equalsTo(Object expected) {
        return equalsTo(expected, null);
    }

    /**
     * For String check if value is an empty string "". For other types check if null
     * @param message error message to show if Assertion is failed
     * @return self
     */
    public Self isEmpty(String message) {
        if (this.value instanceof String) {
            Assertions.assertEquals("", this.value, message);
        } else if (this.value instanceof Collection) {
            Assertions.assertTrue(((Collection<?>)this.value).isEmpty(), message);
        } else {
            Assertions.assertNull(this.value, message);
        }
        return this.self;
    }

    /**
     * Checks if 'null' is considered empty in the current implementation of isEmpty.
     * @return self
     */
    public Self isEmpty() {
        return isEmpty(null);
    }
}
