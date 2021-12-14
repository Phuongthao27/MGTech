package com.mangotech.edu.security;

import com.mangotech.edu.uitls.HashUtils;
import com.mangotech.edu.security.userdetail.EduUserDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;

public class EduDaoAuthenticationProvider extends DaoAuthenticationProvider {
    private UserDetailsPasswordService userDetailsPasswordService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");

            throw new BadCredentialsException(messages.getMessage(
                "AbstractUserDetailsAuthenticationProvider.badCredentials",
                "Bad credentials"));
        }

        String presentedPassword = authentication.getCredentials().toString();

        if (userDetails instanceof EduUserDetail) {
            var saltPassword = ((EduUserDetail) userDetails).getSalt() + presentedPassword;
            if (!getPasswordEncoder().matches(saltPassword, userDetails.getPassword())) {
                logger.debug("Authentication failed: password does not match stored value");

                throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"));
            }
        }
    }

    @Override
    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
        boolean upgradeEncoding = this.userDetailsPasswordService != null
            && getPasswordEncoder().upgradeEncoding(user.getPassword());
        if (upgradeEncoding) {
            String presentedPassword = HashUtils.generateSalt() + authentication.getCredentials().toString();
            String newPassword = getPasswordEncoder().encode(presentedPassword);
            user = this.userDetailsPasswordService.updatePassword(user, newPassword);
        }
        return super.createSuccessAuthentication(principal, authentication, user);
    }

    @Override
    public void setUserDetailsPasswordService(UserDetailsPasswordService userDetailsPasswordService) {
        this.userDetailsPasswordService = userDetailsPasswordService;
    }
}
