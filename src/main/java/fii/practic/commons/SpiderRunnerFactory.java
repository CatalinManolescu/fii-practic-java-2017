package fii.practic.commons;

import javax.validation.constraints.NotNull;

/**
 * Util class for creating instances of {@code SpiderRunner} .
 */
public final class SpiderRunnerFactory {

    /**
     * Create {@code SpiderRunner} instance based on the spider class.
     *
     * @param spiderDefinition Spider implementation
     * @return {@code SpiderRunner} instance
     * @throws InstantiationException if {@code SpiderRunner} instance could not be created.
     */
    public static SpiderRunner createSpiderRunner(@NotNull Class<? extends Spider> spiderDefinition) throws InstantiationException {
        Spider spiderInstance;
        try {
            spiderInstance = spiderDefinition.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new InstantiationException("Could not create spider instance");
        }

        return SpiderRunnerFactory.createSpiderRunner(spiderInstance);
    }

    /**
     * Create {@code SpiderRunner} instance based on the spider class.
     *
     * @param spiderInstance Spider instance
     * @return {@code SpiderRunner} instance
     */
    public static SpiderRunner createSpiderRunner(@NotNull Spider spiderInstance) {
        return new SpiderRunner(spiderInstance);
    }

    /**
     * Private constructor to prevent instantiation.
     */
    private SpiderRunnerFactory() {}
}
