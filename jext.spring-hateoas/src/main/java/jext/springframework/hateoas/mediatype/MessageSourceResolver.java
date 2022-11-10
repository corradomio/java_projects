package jext.springframework.hateoas.mediatype;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.hateoas.mediatype.MessageResolver;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * A {@link MessageResolver} based on a {@link MessageSource}.
 *
 * @author Oliver Drotbohm
 */
public class MessageSourceResolver implements MessageResolver {

    private final MessageSourceAccessor accessor;

    /**
     * Creates a new {@link MessageSourceResolver} for the given {@link MessageSource}.
     *
     * @param messageSource must not be {@literal null}.
     */
    MessageSourceResolver(MessageSource messageSource) {

        Assert.notNull(messageSource, "MessageSource must not be null!");

        this.accessor = new MessageSourceAccessor(messageSource);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.hateoas.mediatype.MessageResolver#resolve(org.springframework.context.MessageSourceResolvable)
     */
    @Nullable
    @Override
    public String resolve(MessageSourceResolvable resolvable) {

        String resolved = accessor.getMessage(resolvable);

        return StringUtils.hasText(resolved) ? resolved : null;
    }
}
