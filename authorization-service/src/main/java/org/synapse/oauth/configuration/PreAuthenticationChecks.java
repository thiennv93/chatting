package org.synapse.oauth.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PreAuthenticationChecks implements UserDetailsChecker {

    public void check(UserDetails user) {

//        if (!user.isAccountNonLocked()) {
//            logger.debug("User account is locked");
//
//            throw new LockedException(messages.getMessage(
//                    "AbstractUserDetailsAuthenticationProvider.locked",
//                    "User account is locked"));
//        }
//
//        if (!user.isEnabled()) {
//            logger.debug("User account is disabled");
//
//            throw new DisabledException(messages.getMessage(
//                    "AbstractUserDetailsAuthenticationProvider.disabled",
//                    "User is disabled"));
//        }
//
//        if (!user.isAccountNonExpired()) {
//            logger.debug("User account is expired");
//
//            throw new AccountExpiredException(messages.getMessage(
//                    "AbstractUserDetailsAuthenticationProvider.expired",
//                    "User account has expired"));
//        }
    }
}
