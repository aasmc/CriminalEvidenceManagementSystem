package ru.aasmc.cems.rest.sec.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Registers the {@link DelegatingFilterProxy} to use the springSecurityFilterChain before
 * any other registered {@link Filter}. When used with
 * {@link #AbstractSecurityWebApplicationInitializer(Class...)}, it will also register a
 * {@link ContextLoaderListener}. When used with
 * {@link #AbstractSecurityWebApplicationInitializer()}, this class is typically used in
 * addition to a subclass of {@link AbstractContextLoaderInitializer}.
 *
 * <p>
 * By default the {@link DelegatingFilterProxy} is registered without support, but can be
 * enabled by overriding {@link #isAsyncSecuritySupported()} and
 * {@link #getSecurityDispatcherTypes()}.
 * </p>
 *
 * <p>
 * Additional configuration before and after the springSecurityFilterChain can be added by
 * overriding {@link #afterSpringSecurityFilterChain(ServletContext)}.
 * </p>
 *
 *
 * <h2>Caveats</h2>
 * <p>
 * Subclasses of AbstractDispatcherServletInitializer will register their filters before
 * any other {@link Filter}. This means that you will typically want to ensure subclasses
 * of AbstractDispatcherServletInitializer are invoked first. This can be done by ensuring
 * the {@link Order} or {@link Ordered} of AbstractDispatcherServletInitializer are sooner
 * than subclasses of {@link AbstractSecurityWebApplicationInitializer}.
 * </p>
 *
 * @author Rob Winch
 * @author Keesun Baik
 */
public class SecurityInitializer  extends AbstractSecurityWebApplicationInitializer {
}
