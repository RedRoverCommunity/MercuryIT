package community.redrover.mercuryit;

/**
 * Allows for the creation of contextual configs, which can then be used by the rest of the method chain.
 */
public final class MercuryITContext extends MercuryITRequest<MercuryITContext> {

    MercuryITContext(MercuryITConfigHolder configHolder) {
        super(configHolder);
    }

    @Override
    public <Request extends MercuryITRequest<Request>> Request request(Class<Request> clazz) {
        return super.request(clazz);
    }
}
